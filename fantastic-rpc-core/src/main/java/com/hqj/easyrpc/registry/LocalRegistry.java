package com.hqj.easyrpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * ClassName: LocalRegistry
 * Package: com.hqj.easyrpc.registry
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 13:41
 * @Version v1.0
 */

/**
 * 用 ConcurrentHashMap 实现注册中心
 */
public class LocalRegistry {

    /**
     * 注册信息存储
     */

    private static final Map<String, Class<?>> map = new ConcurrentHashMap<>();

    /**
     * 注册服务
     * @param serviceName
     * @param implClass
     */
    public static void register(String serviceName, Class<?> implClass) {
        map.put(serviceName, implClass);
    }

    /**
     * 获取服务
     * @param serviceName
     * @return
     */
    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }

    /**
     * 删除服务
     * @param serviceName
     */
    public static void remove(String serviceName) {
        map.remove(serviceName);
    }
}
