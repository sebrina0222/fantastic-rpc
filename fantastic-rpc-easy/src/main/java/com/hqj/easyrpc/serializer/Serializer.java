package com.hqj.easyrpc.serializer;

/**
 * ClassName: serializer
 * Package: com.hqj.easyrpc
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 14:02
 * @Version v1.0
 */

import java.io.IOException;

/**
 * 使用java原生序列化
 */
public interface Serializer {

    /**
     * 序列化
     * 将java对象转化为可传输的字节数组
     * @param object
     * @return
     * @param <T>
     * @throws IOException
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     * 将字节数组转换为Java对象
     * @param bytes
     * @param type
     * @return
     * @param <T>
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}
