package com.hqj.easyrpc.fault.tolerant;

import com.hqj.easyrpc.spi.SpiLoader;

/**
 * ClassName: TolerantStrategyFactory
 * Package: com.hqj.easyrpc.fault.tolerant
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/20 - 14:29
 * @Version v1.0
 */
public class TolerantStrategyFactory {

    static {
        SpiLoader.load(TolerantStrategy.class);
    }

    /**
     * 默认容错策略
     */
    private static final TolerantStrategy DEFAULT_RETRY_STRATEGY = new FailFastTolerantStrategy();

    /**
     * 获取实例
     */
    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }
}
