package com.hqj.easyrpc.server.tcp;

import com.hqj.easyrpc.server.HttpServer;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.parsetools.RecordParser;

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
            //构造 parser
            RecordParser parser = RecordParser.newFixed(8);
            parser.setOutput(new Handler<Buffer>() {
                int size = -1;
                Buffer resultBuffer = Buffer.buffer();
                @Override
                public void handle(Buffer buffer) {
                    if(-1 == size) {
                        //读取消息体长度
                        size = buffer.getInt(4);
                        parser.fixedSizeMode(size);
                        //写入头信息到结果
                        resultBuffer.appendBuffer(buffer);
                    } else {
                        resultBuffer.appendBuffer(buffer);
                        System.out.println(resultBuffer.toString());
                        //重置一轮
                        parser.fixedSizeMode(8);
                        size = -1;
                        resultBuffer = Buffer.buffer();
                    }
                }
            });
//            //处理连接
//            socket.handler(buffer -> {
//                byte[] requestData = buffer.getBytes();
//                byte[] responseData = handleRequest(requestData);
//                //发送响应
//                socket.write(Buffer.buffer(responseData));
//            });
            socket.handler(parser);
        });

        server.listen(port, result -> {
            if(result.succeeded()) {
                System.out.println(" TCP server started on port " + port);
            } else {
                System.out.println("Falied to start RCP server :" + result.cause());
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
