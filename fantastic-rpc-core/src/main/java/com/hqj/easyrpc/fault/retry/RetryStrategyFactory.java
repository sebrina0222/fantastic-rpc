package com.hqj.easyrpc.fault.retry;

import com.hqj.easyrpc.spi.SpiLoader;

/**
 * ClassName: RetryStrategyFactory
 * Package: com.hqj.easyrpc.fault.retry
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/19 - 14:49
 * @Version v1.0
 */
public class RetryStrategyFactory {

    static {
        SpiLoader.load(RetryStrategy.class);
    }

    /**
     * 默认重试器
     */

    private static final RetryStrategy DEFAULT_RETRY_STRATEGY = new NoRetryStrategy();

    /**
     * 获取实例
     */
    public static RetryStrategy getInstance(String key) {
        return SpiLoader.getInstance(RetryStrategy.class, key);
    }
}
