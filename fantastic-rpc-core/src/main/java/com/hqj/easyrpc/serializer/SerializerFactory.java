package com.hqj.easyrpc.serializer;

/**
 * ClassName: SerializerFactory
 * Package: com.hqj.easyrpc.serializer
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/12 - 21:33
 * @Version v1.0
 */

import com.hqj.easyrpc.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列化器工厂（用于获取序列化器对象）
 */
public class SerializerFactory {
    /**
     * 序列化映射（用于实现单例）
     */

//    private static final Map<String, Serializer> KEY_SERIALIZER_MAP = new HashMap<>(){
//        {
            //前三章内容
//            put(SerializerKeys.JDK,new JdkSerializer());
//            put(SerializerKeys.JSON, new JsonSerializer());
//            put(SerializerKeys.KRYO, new KryoSerializer());
//            put(SerializerKeys.HESSIAN, new HessianSerializer());

//        }
//    };
    //第四章 实现spi 加载指定的序列化器
    static {
        SpiLoader.load(Serializer.class);
    }


    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例 如果没找到 就返回默认序列化器
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }
}
