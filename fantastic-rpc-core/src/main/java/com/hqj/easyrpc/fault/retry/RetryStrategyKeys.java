package com.hqj.easyrpc.fault.retry;

/**
 * ClassName: RetryStrategyKeys
 * Package: com.hqj.easyrpc.fault.retry
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/19 - 14:48
 * @Version v1.0
 */
public interface RetryStrategyKeys {
    /**
     * 不重试
     */
    String NO = "no";
    /**
     * 固定时间间隔
     */
    String FIXED_INTERVAL = "fixedInterval";
}
