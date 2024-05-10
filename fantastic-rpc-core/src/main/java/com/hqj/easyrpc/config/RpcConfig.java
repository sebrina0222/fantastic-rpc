package com.hqj.easyrpc.config;

/**
 * ClassName: RpcConfig
 * Package: com.hqj.easyrpc.config
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 22:08
 * @Version v1.0
 */

import lombok.Data;

/**
 *  RPC 框架配置
 */
@Data
public class RpcConfig {

    /**
     * 名称
     */
    private String name = "frpc";
    /**
     * 版本号
     */
    private String version = "1.0";
    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";
    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;
}
