package com.hqj.easyrpc.protocol;

/**
 * ClassName: ProtocolMessage
 * Package: com.hqj.easyrpc.protocol
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/16 - 17:15
 * @Version v1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 协议消息体结构
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtocolMessage<T> {

    /**
     * 消息头
     */
    private Header header;

    /**
     * 消息体
     */
    private T body;


    /**
     * 协议消息头
     */
    @Data
    public static class Header {
        /**
         * 魔数，保证安全性
         */
        private byte magic;

        /**
         * 版本号
         */
        private byte version;

        /**
         * 序列化器
         */
        private byte serializer;

        /**
         * 消息类型
         */
        private byte type;

        /**
         * 状态
         */
        private byte status;

        /**
         * 请求 id
         */
        private long requestId;

        /**
         * 消息体长度
         */
        private int bodyLength;
    }
}
