package com.hqj.fantasticrpcspringbootstarter.starter.annotation;

import com.hqj.easyrpc.constant.RpcConstant;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: RpcService
 * Package: com.hqj.fantasticrpcspringbootstarter.starter.annotation
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/20 - 15:47
 * @Version v1.0
 */

/**
 * RpcService 服务提供者注解，在需要注册和提供的服务类上使用
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {

    /**
     * 服务接口类
     * @return
     */
    Class<?> interfaceClass() default void.class;

    /**
     * 版本
     * @return
     */
    String serviceVersion() default RpcConstant.DEFAULT_SERVICE_VERSION;
}
