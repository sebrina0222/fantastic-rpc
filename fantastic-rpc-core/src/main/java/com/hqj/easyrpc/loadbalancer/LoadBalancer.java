package com.hqj.easyrpc.loadbalancer;

/**
 * ClassName: LoadBalancer
 * Package: com.hqj.easyrpc.loadbalancer
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/18 - 14:43
 * @Version v1.0
 */

import com.hqj.easyrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

/**
 * 第八章内容：负载均衡
 */
public interface LoadBalancer {
    /**
     * 选择服务调用
     * @param requestParams
     * @param serviceMetaInfoList
     * @return
     */
    ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList);
}
