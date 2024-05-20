package com.hqj.easyrpc.fault.tolerant;

/**
 * ClassName: TolerantStrategyKeys
 * Package: com.hqj.easyrpc.fault.tolerant
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/20 - 14:25
 * @Version v1.0
 */
public interface TolerantStrategyKeys {
    /**
     * 快速失败
     */
    String FAIL_FAST = "failFast";

    /**
     * 静默处理
     */
    String FAIL_SAFE = "failSafe";
}
