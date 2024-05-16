package com.hqj.easyrpc.server.tcp;

import com.hqj.easyrpc.server.HttpServer;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

/**
 * ClassName: VertxTcpServer
 * Package: com.hqj.easyrpc.server.tcp
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/16 - 21:09
 * @Version v1.0
 */
public class VertxTcpServer implements HttpServer {
    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();

        // 创建 TCP 服务器
        NetServer server = vertx.createNetServer();

        //处理请求
        server.connectHandler(socket -> {
            //处理连接
            socket.handler(buffer -> {
                byte[] requestData = buffer.getBytes();
                byte[] responseData = handleRequest(requestData);
                //发送响应
                socket.write(Buffer.buffer(responseData));
            });
        });

        server.listen(port, result -> {
            if(result.succeeded()) {
                System.out.println(" TCP server started on port " + port);
            } else {
                System.out.println("Falied to start RCP server :" + result.cause());
            }
        });
    }

    private byte[] handleRequest(byte[] requestData) {

        //在这里编写处理请求的逻辑 根据requesetData 构造响应数据并返回
        return "Hello, client!".getBytes();
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
