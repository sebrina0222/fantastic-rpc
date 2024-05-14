package com.hqj.easyrpc.registry;

/**
 * ClassName: Registry
 * Package: com.hqj.easyrpc.registry
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/14 - 15:18
 * @Version v1.0
 */

import com.hqj.easyrpc.config.RegistryConfig;
import com.hqj.easyrpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * 为什么实现Registry接口？为什么是接口
 * 遵循可拓展设计，我们先写一个注册中心接口，后续可以实现多种不同的注册中心，并且和序列化器一样，可以使用SPI机制动态加载
 */
public interface Registry {

    /**
     * 注册服务
     * @param registryConfig
     */
    void init(RegistryConfig registryConfig);

    /**
     * 注册服务
     * @param serviceMetaInfo
     * @throws Exception
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * 注销服务 服务端
     * @param serviceMetaInfo
     */
    void unRegister(ServiceMetaInfo serviceMetaInfo);

    /**
     * 服务发现（获取某服务的所有节点，消费端）
     * @param serviceKey
     * @return
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * 服务销毁
     */
    void destroy();
}
