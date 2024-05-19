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
import com.hqj.easyrpc.config.RegistryConfig;
import com.hqj.easyrpc.config.RpcConfig;
import com.hqj.easyrpc.model.ServiceMetaInfo;
import com.hqj.easyrpc.registry.LocalRegistry;
import com.hqj.easyrpc.registry.Registry;
import com.hqj.easyrpc.registry.RegistryFactory;
import com.hqj.easyrpc.server.HttpServer;
import com.hqj.easyrpc.server.VertxHttpServer;
import com.hqj.easyrpc.server.tcp.VertxTcpServer;
import com.hqj.example.common.service.UserService;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        //RPC框架初始化
        RpcApplication.init();
        //注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        //注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());

        ServiceMetaInfo serviceMetaInfo1 = new ServiceMetaInfo();
        serviceMetaInfo1.setServiceName(serviceName);
        serviceMetaInfo1.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo1.setServicePort(9090);

        ServiceMetaInfo serviceMetaInfo2 = new ServiceMetaInfo();
        serviceMetaInfo2.setServiceName(serviceName);
        serviceMetaInfo2.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo2.setServicePort(9091);
        try{
            //记得开启注册中心
            registry.register(serviceMetaInfo);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
//        //提供Http服务
//        HttpServer httpServer = new VertxHttpServer();
//        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
        //启动TCP服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(8081);
    }
}
