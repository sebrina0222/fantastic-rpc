package com.hqj.easyrpc.protocol;

/**
 * ClassName: ProtocolConstant
 * Package: com.hqj.easyrpc.protocol
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/16 - 17:20
 * @Version v1.0
 */
public interface ProtocolConstant {
    /**
     * 消息头长度
     */

    int MESSAGE_HEADER_LENGTH = 17;

    /**
     * 协议魔术
     */
    byte PROTOCOL_MAGIC = 0x1;

    /**
     * 协议版本号
     */
    byte PROTOCOL_VERSION = 0x1;
}

