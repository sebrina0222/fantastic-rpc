package com.hqj.easyrpc.proxy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.esotericsoftware.minlog.Log;
import com.hqj.easyrpc.RpcApplication;
import com.hqj.easyrpc.config.RpcConfig;
import com.hqj.easyrpc.constant.RpcConstant;
import com.hqj.easyrpc.fault.retry.RetryStrategy;
import com.hqj.easyrpc.fault.retry.RetryStrategyFactory;
import com.hqj.easyrpc.fault.tolerant.TolerantStrategy;
import com.hqj.easyrpc.fault.tolerant.TolerantStrategyFactory;
import com.hqj.easyrpc.loadbalancer.LoadBalancer;
import com.hqj.easyrpc.loadbalancer.LoadBalancerFactory;
import com.hqj.easyrpc.model.RpcRequest;
import com.hqj.easyrpc.model.RpcResponse;
import com.hqj.easyrpc.model.ServiceMetaInfo;
import com.hqj.easyrpc.protocol.*;
import com.hqj.easyrpc.registry.Registry;
import com.hqj.easyrpc.registry.RegistryFactory;
import com.hqj.easyrpc.serializer.JdkSerializer;
import com.hqj.easyrpc.serializer.Serializer;
import com.hqj.easyrpc.serializer.SerializerFactory;
import com.hqj.easyrpc.server.tcp.VertxTcpClient;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * ClassName: ServiceProxy
 * Package: com.hqj.easyrpc.proxy
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 17:48
 * @Version v1.0
 */
public class ServiceProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());
        String serviceName = method.getDeclaringClass().getName();
        //构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();

        try {
            //序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            //从注册中心获取服务提供者请求地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName) ;
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }//暂时先取第一个
            //负载均衡
            LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());
            // 将调用方法名（请求路径） 作为负载均衡参数
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("methodName", rpcRequest.getMethodName());
            ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
            Log.info("ServiceProxy:服务选择的地址为======================>" + selectedServiceMetaInfo.getServicePort());
            RpcResponse rpcResponse = null;
            //发送TCP请求
            try {
                RetryStrategy retryStrategy = RetryStrategyFactory.getInstance(rpcConfig.getRetryStrategy());
//            RpcResponse rpcResponse = VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo);
                rpcResponse = retryStrategy.doRetry(() ->
                        VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo));
            } catch (Exception e) {
                //容错机制
                TolerantStrategy tolerantStrategy = TolerantStrategyFactory.getInstance(rpcConfig.getTolerantStrategy());
                tolerantStrategy.doTolerant(null, e);
            }
            return rpcResponse.getData();
//            Vertx vertx = Vertx.vertx();
//            NetClient netClient = vertx.createNetClient();
//            CompletableFuture<RpcResponse> responseFuture = new CompletableFuture<>();
//            netClient.connect(selectedServiceMetaInfo.getServicePort(), selectedServiceMetaInfo.getServiceHost(),
//                    results -> {
//                        if(results.succeeded()) {
//                            System.out.println("Connected to TCP server");
//                            NetSocket socket = results.result();
//                            // 发送数据
//                            // 构造消息
//                            ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
//                            ProtocolMessage.Header header = new ProtocolMessage.Header();
//                            header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
//                            header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
//                            header.setSerializer((byte) ProtocolMessageSerializerEnum.getEnumByValue(RpcApplication.getRpcConfig().getSerializer()).getKey());
//                            header.setType((byte)ProtocolMessageTypeEnum.REQUEST.getKey());
//                            header.setRequestId(IdUtil.getSnowflakeNextId());
//                            protocolMessage.setHeader(header);
//                            protocolMessage.setBody(rpcRequest);
//
//                            try {
//                                Buffer encodeBuffer = ProtocolMessageEncoder.encode(protocolMessage);
//                                socket.write(encodeBuffer);
//                            } catch (IOException e) {
//                                throw new RuntimeException("协议消息编码错误");
//                            }
//
//                            socket.handler(buffer -> {
//                                try {
//                                    ProtocolMessage<RpcResponse> rpcResponseProtocolMessage = (ProtocolMessage<RpcResponse>) ProtocolMessageDecoder.decode(buffer);
//                                    responseFuture.complete(rpcResponseProtocolMessage.getBody());
//                                } catch (IOException e) {
//                                    throw new RuntimeException("协议消息解码错误");
//                                }
//                            });
//                        } else {
//                            System.err.println("Failed to connect to RCP server");
//                        }
//                    });
            //以上这一部分都是在做TCP请求
//            RpcResponse rpcResponse = responseFuture.get();
//            netClient.close();
//            return rpcResponse.getData();

//            try (HttpResponse httpResponse = HttpRequest.post(selectedServiceMetaInfo.getServiceAddress())
//                    .body(bodyBytes)
//                    .execute()){
//                result = httpResponse.bodyBytes();
//            }
//            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);

        } catch (IOException e) {
            throw new RuntimeException("调用失败");
        }

    }
}
