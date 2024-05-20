package com.hqj.easyrpc.bootstrap;

import com.hqj.easyrpc.RpcApplication;
import com.hqj.easyrpc.config.RegistryConfig;
import com.hqj.easyrpc.config.RpcConfig;
import com.hqj.easyrpc.model.ServiceMetaInfo;
import com.hqj.easyrpc.model.ServiceRegisterInfo;
import com.hqj.easyrpc.registry.LocalRegistry;
import com.hqj.easyrpc.registry.Registry;
import com.hqj.easyrpc.registry.RegistryFactory;
import com.hqj.easyrpc.server.tcp.VertxTcpServer;

import java.util.List;

/**
 * ClassName: ProviderBootstrap
 * Package: com.hqj.easyrpc.bootstrap
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/20 - 14:44
 * @Version v1.0
 */
public class ProviderBootstrap {

    /**
     * 初始化
     */
    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList) {
        // RPC 框架初始化
        RpcApplication.init();
        // 全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        //注册服务
        for(ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList) {
            //注册服务
            String serviceName = serviceRegisterInfo.getServiceName();
            LocalRegistry.register(serviceName, serviceRegisterInfo.getImplClass());
            //注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + " 服务注册失败", e);
            }
        }
        //启动服务器
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(rpcConfig.getServerPort());
    }

}
