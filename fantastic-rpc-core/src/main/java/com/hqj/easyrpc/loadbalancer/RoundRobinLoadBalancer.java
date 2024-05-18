package com.hqj.easyrpc.loadbalancer;

import com.hqj.easyrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: RoundRobinLoadBalancer
 * Package: com.hqj.easyrpc.loadbalancer
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/18 - 14:46
 * @Version v1.0
 */
public class RoundRobinLoadBalancer implements LoadBalancer {
    /**
     * 当前轮询的下标
     * AtomicInteger 是JUC包的原子计数器 防止并发冲突问题
     */
    private final AtomicInteger currentIndex = new AtomicInteger(0);
    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if(serviceMetaInfoList.isEmpty()) {
            return null;
        }
        //只有一个服务 不需要轮询
        int size = serviceMetaInfoList.size();
        if(size == 1) {
            return serviceMetaInfoList.get(0);
        }
        //取模算法轮询
        int index = currentIndex.getAndIncrement() % size;
        return serviceMetaInfoList.get(index);
    }
}
