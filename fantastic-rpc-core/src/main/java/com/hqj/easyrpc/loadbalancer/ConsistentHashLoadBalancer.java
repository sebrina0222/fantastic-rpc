package com.hqj.easyrpc.loadbalancer;

import com.hqj.easyrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * ClassName: ConsistentHashLoadBalancer
 * Package: com.hqj.easyrpc.loadbalancer
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/18 - 15:08
 * @Version v1.0
 */
public class ConsistentHashLoadBalancer implements LoadBalancer{
    /**
     * 一致性 Hash 环， 存放虚拟节点，为什么用TreeMap？
     */
    private final TreeMap<Integer, ServiceMetaInfo> virtualNodes = new TreeMap<>();
    /**
     * 虚拟节点数
     */
    private static final int VIRTUAL_NODE_NUM = 100;
    /**
     * 实现一致性哈希负载均衡器
     * @param requestParams
     * @param serviceMetaInfoList
     * @return
     */
    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if(serviceMetaInfoList.isEmpty()) {
            return null;
        }

        //构建虚拟节点环
        for(ServiceMetaInfo serviceMetaInfo : serviceMetaInfoList) {
            for(int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                int hash = getHash(serviceMetaInfo.getServiceAddress() + "#" + i);
                virtualNodes.put(hash, serviceMetaInfo);
            }
        }
        int hash = getHash(requestParams);
        Map.Entry<Integer, ServiceMetaInfo> integerServiceMetaInfoEntry = virtualNodes.ceilingEntry(hash);
        if(integerServiceMetaInfoEntry == null) {
            //如果没有大于等于调用请求 hash值的虚拟节点，则返回首部的节点
            integerServiceMetaInfoEntry = virtualNodes.firstEntry();
        }
        return integerServiceMetaInfoEntry.getValue();
    }

    /**
     * hash算法，可自行实现
     * @param key
     * @return
     */
    private int getHash(Object key) {
        return key.hashCode();
    }
}
