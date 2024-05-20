package com.hqj.example.consumer;

import com.hqj.easyrpc.RpcApplication;
import com.hqj.easyrpc.bootstrap.ConsumerBootstrap;
import com.hqj.easyrpc.config.RpcConfig;
import com.hqj.easyrpc.constant.RpcConstant;
import com.hqj.easyrpc.proxy.ServiceProxyFactory;
import com.hqj.easyrpc.utils.ConfigUtils;
import com.hqj.example.common.model.User;
import com.hqj.example.common.service.UserService;

/**
 * ClassName: ConsumerExample
 * Package: com.hqj.example.consumer
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/11 - 21:25
 * @Version v1.0
 */
public class ConsumerExample {

    public static void main(String[] args) {
        //pcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        ConsumerBootstrap.init();
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
        long number = userService.getNumber();
        System.out.println("number = " + number);
    }
}
