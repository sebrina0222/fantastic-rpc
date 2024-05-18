package com.hqj.easyrpc.loadbalancer;

import com.hqj.easyrpc.spi.SpiLoader;

/**
 * ClassName: LoadBalancerFactory
 * Package: com.hqj.easyrpc.loadbalancer
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/18 - 15:19
 * @Version v1.0
 */
public class LoadBalancerFactory {
    static {
        SpiLoader.load(LoadBalancer.class);
    }

    /**
     * 默认负载均衡器
     */
    private static final LoadBalancer DEFAULT_LOAD_BALANCER = new RoundRobinLoadBalancer();

    /**
     * 获取实例
     *
     */
    public static LoadBalancer getInstance(String key) {
        return SpiLoader.getInstance(LoadBalancer.class, key);
    }



}
