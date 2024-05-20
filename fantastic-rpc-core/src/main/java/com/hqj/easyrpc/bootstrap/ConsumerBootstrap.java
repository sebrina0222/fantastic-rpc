package com.hqj.easyrpc.bootstrap;

import com.hqj.easyrpc.RpcApplication;

/**
 * ClassName: ConsumerBootstrap
 * Package: com.hqj.easyrpc.bootstrap
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/20 - 15:21
 * @Version v1.0
 */
public class ConsumerBootstrap {
    /**
     * 初始化
     */
    public static void init() {
        // RPC 框架初始化（配置和注册中心）
        RpcApplication.init();
    }
}
