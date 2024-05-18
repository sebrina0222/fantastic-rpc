package com.hqj.easyrpc.loadbalancer;

import com.hqj.easyrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * ClassName: RandomLoadBalancer
 * Package: com.hqj.easyrpc.loadbalancer
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/18 - 15:06
 * @Version v1.0
 */

/**
 * 随机负载均衡器
 */
public class RandomLoadBalancer implements LoadBalancer{
    private final Random random = new Random();

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        int size = serviceMetaInfoList.size();
        if(size == 0) {
            return null;
        }
        if(size == 1) {
            return serviceMetaInfoList.get(0);
        }
        return serviceMetaInfoList.get(random.nextInt(size));
    }
}
