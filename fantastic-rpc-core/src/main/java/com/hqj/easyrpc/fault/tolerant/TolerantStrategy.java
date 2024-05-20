package com.hqj.easyrpc.fault.tolerant;

import com.hqj.easyrpc.model.RpcResponse;

import java.util.Map;

/**
 * ClassName: TolerantStrategy
 * Package: com.hqj.easyrpc.fault.tolerant
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/19 - 17:26
 * @Version v1.0
 */
public interface TolerantStrategy {
    /**
     * 容错
     * 
     * @param context
     * @param e
     * @return
     */
    RpcResponse doTolerant(Map<String, Object> context, Exception e);
}
