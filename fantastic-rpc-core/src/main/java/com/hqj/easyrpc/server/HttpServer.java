package com.hqj.easyrpc.server;

/**
 * ClassName: HttpServer
 * Package: com.hqj.easyrpc
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 13:14
 * @Version v1.0
 */

/**
 * HTTP 服务器接口
 */
public interface HttpServer {
    /**
     * 启动服务器
     * @param port
     */
    void doStart(int port);
}
