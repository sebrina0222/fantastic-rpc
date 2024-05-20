package com.hqj.fantasticrpcspringbootstarter.starter.annotation;

/**
 * ClassName: RpcReference
 * Package: com.hqj.fantasticrpcspringbootstarter.starter.annotation
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/20 - 15:47
 * @Version v1.0
 */

import com.hqj.easyrpc.constant.RpcConstant;
import com.hqj.easyrpc.fault.retry.RetryStrategyKeys;
import com.hqj.easyrpc.fault.tolerant.TolerantStrategyKeys;
import com.hqj.easyrpc.loadbalancer.LoadBalancerKeys;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务消费者注解，在需要注入服务代理对象的属性上使用，类似Spring中的 @Resource
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RpcReference {

    /**
     * 服务接口类
     */
    Class<?> interfaceClass() default void.class;

    /**
     * 版本
     */
    String serviceVersion() default RpcConstant.DEFAULT_SERVICE_VERSION;

    /**
     * 负载均衡器
     */
    String loadBalancer() default LoadBalancerKeys.ROUND_ROBIN;

    /**
     * 重试策略
     */
    String retryStrategy() default RetryStrategyKeys.NO;

    /**
     * 容错策略
     */
    String tolerantStrategy() default TolerantStrategyKeys.FAIL_FAST;

    /**
     * 模拟调用
     */
    boolean mock() default false;

}