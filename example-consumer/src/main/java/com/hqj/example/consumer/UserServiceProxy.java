package com.hqj.example.consumer;

/**
 * ClassName: UserServiceProxy
 * Package: com.hqj.example.consumer
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 15:09
 * @Version v1.0
 */

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.hqj.easyrpc.RpcApplication;
import com.hqj.easyrpc.model.RpcRequest;
import com.hqj.easyrpc.model.RpcResponse;
import com.hqj.easyrpc.serializer.JdkSerializer;
import com.hqj.easyrpc.serializer.Serializer;
import com.hqj.easyrpc.serializer.SerializerFactory;
import com.hqj.example.common.model.User;
import com.hqj.example.common.service.UserService;

import java.io.IOException;

/**
 * 通过生成代理对象来简化消费方的调用
 */
public class UserServiceProxy implements UserService{
    /**
     * 去获取配置文件中配置的序列器
     */
    final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());
    public User getUser(User user) {
//        Serializer serializer = new JdkSerializer();


        //发请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();


        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8081")
                    .body(bodyBytes)
                    .execute()){
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
