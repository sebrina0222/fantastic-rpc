package com.hqj.easyrpc.config;

import lombok.Data;

/**
 * ClassName: RegisryConfig
 * Package: com.hqj.easyrpc.config
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/14 - 15:15
 * @Version v1.0
 */
@Data
public class RegistryConfig {
    /**
     * 注册中心类别
     */
    private String registry = "etcd";
    /**
     * 注册中心地址
     */
    private String address = "http://114.55.90.45:2379";
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 超时时间 单位毫秒
     */
    private Long timeout = 10000L;
}
