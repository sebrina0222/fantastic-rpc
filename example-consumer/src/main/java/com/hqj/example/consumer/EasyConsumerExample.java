package com.hqj.example.consumer;

import com.hqj.easyrpc.proxy.ServiceProxy;
import com.hqj.easyrpc.proxy.ServiceProxyFactory;
import com.hqj.example.common.model.User;
import com.hqj.example.common.service.UserService;

/**
 * ClassName: EasyConsumerExample
 * Package: com.hqj.example.consumer
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 13:06
 * @Version v1.0
 */
public class EasyConsumerExample {
    public static void main(String[] args) {
        //TODO : 需要获得UserService的实现对象(RPC ?) 目前无法获取到
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("Noora123");
        //调用
        User newUser = userService.getUser(user);
        if(newUser != null) {
            System.out.println(newUser.getName());
        }else{
            System.out.println("user == null");
        }
    }
}
