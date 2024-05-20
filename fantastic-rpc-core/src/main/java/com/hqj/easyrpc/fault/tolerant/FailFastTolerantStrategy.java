package com.hqj.easyrpc.fault.tolerant;

import com.hqj.easyrpc.model.RpcResponse;

import java.util.Map;

/**
 * ClassName: FailFastTolerantStrategy
 * Package: com.hqj.easyrpc.fault.tolerant
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/20 - 14:18
 * @Version v1.0
 */

/**
 * 快速失败 —— 容错策略 （立即通知外层调用方）
 */
public class FailFastTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw  new RuntimeException("服务报错", e);
    }
}
