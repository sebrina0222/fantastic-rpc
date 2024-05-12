package com.hqj.easyrpc.proxy;

/**
 * ClassName: MockServiceProxy
 * Package: com.hqj.easyrpc.proxy
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/12 - 13:32
 * @Version v1.0
 */

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Mock 服务代理 （JDK动态代理）
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {
    /**
     * 调用代理
     * @param proxy the proxy instance that the method was invoked on
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //根据方法的返回值类型，生成特定的默认值对象
        Class<?> methodReturnType = method.getReturnType();
        log.info("mock invoke {}", method.getName());
        return getDefaultValue(methodReturnType);
    }

    /**
     * 生成指定类型的默认值对象（可自行完善）
     * @param methodReturnType
     * @return
     */
    private Object getDefaultValue(Class<?> methodReturnType) {
        System.out.println("调用了MockServiceProxy的getDefaultValue");
        if(methodReturnType.isPrimitive()) {
            if(methodReturnType == boolean.class) {
                return false;
            } else if( methodReturnType == short.class) {
                return (short) 0;
            } else if(methodReturnType == int.class) {
                return 0;
            } else if(methodReturnType == long.class) {
                return 0L;
            }
        }
        return null;
    }


}
