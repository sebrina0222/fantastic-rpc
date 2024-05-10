package com.hqj.easyrpc.proxy;

import java.lang.reflect.Proxy;

/**
 * ClassName: ServiceProxyFactory
 * Package: com.hqj.easyrpc.proxy
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 17:58
 * @Version v1.0
 */
public class ServiceProxyFactory {


    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),new Class[]{serviceClass},new ServiceProxy()
        );
    }
}
