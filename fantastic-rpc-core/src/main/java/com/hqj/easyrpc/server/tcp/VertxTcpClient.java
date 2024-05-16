package com.hqj.easyrpc.server.tcp;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;

/**
 * ClassName: VertxTcpClient
 * Package: com.hqj.easyrpc.server.tcp
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/16 - 21:15
 * @Version v1.0
 */
public class VertxTcpClient {
    public void start(){
        //创建 Vertx 实例
        Vertx vertx = Vertx.vertx();
        NetClient netClient = vertx.createNetClient();
        netClient.connect(8888,"localhost", result -> {
           if(result.succeeded()) {
               System.out.println("Connected to TCP server");
               io.vertx.core.net.NetSocket socket = result.result();
               //发送数据
               socket.write("Hello, server!");
               //接收数据
               socket.handler(buffer -> {
                   System.out.println("Received response from server: " + buffer.toString());
               });
           } else {
               System.out.println("Failed to connect to TCP server");
           }
        });
    }

    public static void main(String[] args) {
        new VertxTcpClient().start();
    }


}
