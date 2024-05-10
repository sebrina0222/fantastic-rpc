package com.hqj.easyrpc.server;

/**
 * ClassName: HttpServerHandler
 * Package: com.hqj.easyrpc.server
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 14:23
 * @Version v1.0
 */

import com.hqj.easyrpc.model.RpcRequest;
import com.hqj.easyrpc.model.RpcResponse;
import com.hqj.easyrpc.registry.LocalRegistry;
import com.hqj.easyrpc.serializer.JdkSerializer;
import com.hqj.easyrpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 请求处理器（当我接收到一个请求 该如何处理？）
 * 1. 反序列化请求为对象，并从请求对象中获取参数，
 * 2. 根据服务名称从本地注册器中获取到对应的服务实现类
 * 3．通过反射机制调用方法，得到返回结果。
 * 4。对返回结果进行封装和序列化，并写入到响应中。
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest httpServerRequest) {
        //指定一个序列化器 这里用的时原生的JdkSerializer
        final Serializer serializer = new JdkSerializer();
        //记录日志
        System.out.println("Received request: " + httpServerRequest.method() + " " + httpServerRequest.uri());

        //异步处理HTTP请求 不同的服务器对应的请求处理器实现方式不同， Vert.x通过实现 Handler<HttpServerRequest> 接口来自定义请求处理器的。通过 bodyHandler 异步处理请求
        httpServerRequest.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try{
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (Exception e) {
                e.printStackTrace();
            }


            //构造响应结果对象
            RpcResponse rpcResponse = new RpcResponse();
            if(rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(httpServerRequest, rpcResponse, serializer);
                return;
            }


            try {
                //使用反射调用方法
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(),rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
                //获得结果 返回结果
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            doResponse(httpServerRequest, rpcResponse, serializer);
        });



    }

    /**
     * 响应
     * @param request
     * @param rpcResponse
     * @param serializer
     */
    void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = request.response()
                .putHeader("content-type", "application/json");
        byte[] serialized = new byte[0];
        try {
            serialized = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }
}
