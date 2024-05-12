package com.hqj.easyrpc.proxy;

import com.hqj.easyrpc.RpcApplication;

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


    /**
     * 根据服务类获取代理对象
     * @param serviceClass
     * @return
     * @param <T>
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        if(RpcApplication.getRpcConfig().isMock()) {
            return getMockProxy(serviceClass);
        }
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy()
        );
    }

    /**
     * 根据服务类获取 Mock 代理对象
     * @param serviceClass
     * @return
     * @param <T>
     */
    private static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new MockServiceProxy()
        );
    }


}
