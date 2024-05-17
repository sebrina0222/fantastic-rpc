package com.hqj.easyrpc.tcp;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;

/**
 * ClassName: VertxTcpClient
 * Package: com.hqj.easyrpc.tcp
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/17 - 22:01
 * @Version v1.0
 */
public class VertxTcpClient1 {

    public void start() {
        Vertx vertx = Vertx.vertx();

        vertx.createNetClient().connect(8888,"localhost", result -> {
           if(result.succeeded()) {
               System.out.println("Connect to RCP server");
               NetSocket socket = result.result();
               for(int i = 0; i < 1000; i++) {
                   socket.write("hello, server!hello, server!hello, server!hello, server!");
               }
               socket.handler(buffer -> {
                   System.out.println("Received response from server: " + buffer.toString());
               });
           } else {
               System.out.println("Failed to connect to TCP server");
           }
        });
    }

    public static void main(String[] args) {
        new VertxTcpClient1().start();
    }
}
