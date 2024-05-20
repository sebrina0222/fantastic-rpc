package com.hqj.fantasticrpcspringbootstarter.starter.annotation;

/**
 * ClassName: EnableRpc
 * Package: com.hqj.fantasticrpcspringbootstarter.starter.annotation
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/20 - 15:47
 * @Version v1.0
 */

import com.hqj.fantasticrpcspringbootstarter.starter.bootstrap.RpcConsumerBootstrap;
import com.hqj.fantasticrpcspringbootstarter.starter.bootstrap.RpcInitBootstrap;
import com.hqj.fantasticrpcspringbootstarter.starter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于全局标识项目需要引入RPC框架，执行初始化方法
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//@Import 仅在用户使用@EnableRpc注解时，才启动RPC框架。
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {

    /**
     * 需要启动server
     */
    boolean needServer() default true;
}
