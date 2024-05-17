package com.hqj.easyrpc.tcp;

import com.hqj.easyrpc.server.HttpServer;
import com.hqj.easyrpc.server.tcp.TcpServerHandler;
import com.hqj.easyrpc.server.tcp.VertxTcpServer;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.ServerOptionsBase;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: VertxTcpServer
 * Package: com.hqj.easyrpc.tcp
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/17 - 22:05
 * @Version v1.0
 */
@Slf4j
public class VertxTcpServer1 implements HttpServer {
    @Override
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 TCP 服务器
        NetServer server = vertx.createNetServer();

        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                String testMessage = "hello, server!hello, server!hello, server!hello, server!";
                int messageLength = testMessage.getBytes().length;
                if(buffer.getBytes().length < messageLength) {
                    System.out.println("半包，length = " + buffer.getBytes().length);
                    return;
                }
                if(buffer.getBytes().length > messageLength) {
                    System.out.println("粘包，length = " + buffer.getBytes().length);
                    return;
                }
                String str = new String(buffer.getBytes(0, messageLength));
                System.out.println(str);
                if(testMessage.equals(str)) {
                    System.out.println("good");
                }
            });
        });

        // 启动 TCP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                log.info("TCP server started on port " + port);
            } else {
                log.info("Failed to start TCP server: " + result.cause());
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpServer1().doStart(8888);
    }
}
