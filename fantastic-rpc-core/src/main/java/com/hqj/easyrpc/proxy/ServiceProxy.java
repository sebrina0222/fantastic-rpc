package com.hqj.easyrpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.hqj.easyrpc.model.RpcRequest;
import com.hqj.easyrpc.model.RpcResponse;
import com.hqj.easyrpc.serializer.JdkSerializer;
import com.hqj.easyrpc.serializer.Serializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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
        Serializer serializer = new JdkSerializer();

        //构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();

        try {
            //序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            //TODO: 这里地址被硬编码了 需要使用注册中心和服务发现机制解决
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()){
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
