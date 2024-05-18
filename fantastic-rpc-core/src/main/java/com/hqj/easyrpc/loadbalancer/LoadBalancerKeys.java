package com.hqj.easyrpc.loadbalancer;

/**
 * ClassName: LoadBalancerKeys
 * Package: com.hqj.easyrpc.loadbalancer
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/18 - 15:18
 * @Version v1.0
 */
public interface LoadBalancerKeys {
    /**
     * 轮询
     */
    String ROUND_ROBIN = "roundRobin";
    /**
     * 随机负载均衡算法
     */
    String RANDOM = "random";
    /**
     * 一致hash算法
     */
    String CONSISTENT_HASH = "consistentHash";
}
