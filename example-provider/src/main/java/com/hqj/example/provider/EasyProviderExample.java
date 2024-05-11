package com.hqj.example.provider;

/**
 * ClassName: EasyProviderExample
 * Package: com.hqj.example.provider
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 13:03
 * @Version v1.0
 */

import com.hqj.easyrpc.RpcApplication;
import com.hqj.easyrpc.registry.LocalRegistry;
import com.hqj.easyrpc.server.HttpServer;
import com.hqj.easyrpc.server.VertxHttpServer;
import com.hqj.example.common.service.UserService;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        //RPC框架初始化
        RpcApplication.init();
        //注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        //提供服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
