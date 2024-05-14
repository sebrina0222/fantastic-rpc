package com.hqj.easyrpc.registry;

import com.hqj.easyrpc.spi.SpiLoader;

/**
 * ClassName: RegistryFactory
 * Package: com.hqj.easyrpc.registry
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/14 - 16:31
 * @Version v1.0
 */
public class RegistryFactory {

    static {
        SpiLoader.load(Registry.class);
    }

    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }
}
