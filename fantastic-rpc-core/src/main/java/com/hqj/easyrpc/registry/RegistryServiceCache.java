package com.hqj.easyrpc.registry;

import com.hqj.easyrpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * ClassName: RegistryServiceCache
 * Package: com.hqj.easyrpc.registry
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/15 - 14:55
 * @Version v1.0
 */

/**
 * 6.本地缓存来存储服务信息
 */
public class RegistryServiceCache {

    /**
     * 服务缓存
     */
    List<ServiceMetaInfo> serviceCache;

    /**
     * 写缓存
     * @param newServiceCache
     */
    void writeCache(List<ServiceMetaInfo> newServiceCache) {
        this.serviceCache = newServiceCache;
    }

    /**
     * 读缓存
     * @return
     */

    List<ServiceMetaInfo> readCache(){
        return this.serviceCache;
    }

    /**
     * 清空缓存
     */
    void clearCache() {
        this.serviceCache = null;
    }
}
