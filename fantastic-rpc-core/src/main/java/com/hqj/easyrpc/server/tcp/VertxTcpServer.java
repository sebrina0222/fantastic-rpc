package com.hqj.easyrpc.server.tcp;

import com.hqj.easyrpc.server.HttpServer;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.parsetools.RecordParser;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: VertxTcpServer
 * Package: com.hqj.easyrpc.server.tcp
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/16 - 21:09
 * @Version v1.0
 */
@Slf4j
public class VertxTcpServer implements HttpServer {
    @Override
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 TCP 服务器
        NetServer server = vertx.createNetServer();

        // 处理请求
        server.connectHandler(new TcpServerHandler());

        // 启动 TCP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                log.info("TCP server started on port " + port);
            } else {
                log.info("Failed to start TCP server: " + result.cause());
            }
        });
    }
    /**
     * 逻辑请求处理
     * @param requestData
     * @return
     */
    private byte[] handleRequest(byte[] requestData) {

        //在这里编写处理请求的逻辑 根据requesetData 构造响应数据并返回
        return "Hello, client!".getBytes();
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
