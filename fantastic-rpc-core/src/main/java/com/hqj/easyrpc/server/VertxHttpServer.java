package com.hqj.easyrpc.server;

import io.vertx.core.Vertx;

/**
 * ClassName: VertxHttpServer
 * Package: com.hqj.easyrpc
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 13:15
 * @Version v1.0
 */
public class VertxHttpServer implements HttpServer{
    @Override
    public void doStart(int port) {
        //创建Vert.x实例
        Vertx vertx = Vertx.vertx();
        //创建HTTP服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();
        //监听端口并且处理请求
//        server.requestHandler(request -> {
//            System.out.println("Received request = " + request.method() + " " + request.uri());
//            request.response()
//                    .putHeader("content-type", "text/plain")
//                    .end("Hello from Vert.x HTTP server!"); // 访问localhost:8080可以看到这一行数字
//        });
        server.requestHandler(new HttpServerHandler()); //这里绑定了请求处理器 用的是刚创建的 HttpServerHandler

        //启动HTTP服务器并监听指定端口
        server.listen(port, result -> {
            if(result.succeeded()) {
                System.out.println("Server is now listenting on port " + port);
            } else {
                System.out.println("Failed to start server: " + result.cause());
            }
        });
    }
}
