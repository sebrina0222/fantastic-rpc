package com.hqj.easyrpc.spi;

/**
 * ClassName: SpiLoader
 * Package: com.hqj.easyrpc.spi
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/13 - 23:27
 * @Version v1.0
 */

import cn.hutool.core.io.resource.ResourceUtil;
import com.hqj.easyrpc.serializer.Serializer;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SPI 加载器
 * 自定义实现，支持键值对映射
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @learn <a href="https://codefather.cn">程序员鱼皮的编程宝典</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Slf4j
public class SpiLoader {

    /**
     * 存储已加载的类：接口名 =>（key => 实现类） Serializer -> (key(jdk,hessian...) -> 实现类(JdkSerizlizer))
     */
    private static final Map<String, Map<String, Class<?>>> loaderMap = new ConcurrentHashMap<>();

    /**
     * 对象实例缓存（避免重复 new），类路径 => 对象实例，单例模式
     */
    private static final Map<String, Object> instanceCache = new ConcurrentHashMap<>();

    /**
     * 系统 SPI 目录
     */
    private static final String RPC_SYSTEM_SPI_DIR = "META-INF/rpc/system/";

    /**
     * 用户自定义 SPI 目录
     */
    private static final String RPC_CUSTOM_SPI_DIR = "META-INF/rpc/custom/";

    /**
     * 扫描路径
     */
    private static final String[] SCAN_DIRS = new String[]{RPC_SYSTEM_SPI_DIR, RPC_CUSTOM_SPI_DIR};

    /**
     * 动态加载的类列表
     */
    private static final List<Class<?>> LOAD_CLASS_LIST = Arrays.asList(Serializer.class);

    /**
     * 加载所有接口类型，但现在只有Serializer.class一个 因此loadAll和load(Serializer.class) 没有区别
     */
    public static void loadAll() {
        log.info("加载所有 SPI");
        for (Class<?> aClass : LOAD_CLASS_LIST) {
            load(aClass);
        }
    }

    /**
     * 获取某个接口的实例
     *
     * @param tClass
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getInstance(Class<?> tClass, String key) {
        String tClassName = tClass.getName();
        Map<String, Class<?>> keyClassMap = loaderMap.get(tClassName); //获得Serializer的实现类map
        if (keyClassMap == null) {
            throw new RuntimeException(String.format("SpiLoader 未加载 %s 类型", tClassName));
        }
        if (!keyClassMap.containsKey(key)) {
            throw new RuntimeException(String.format("SpiLoader 的 %s 不存在 key=%s 的类型", tClassName, key));
        }
        // 获取到要加载的实现类型
        Class<?> implClass = keyClassMap.get(key);
        // 从实例缓存中加载指定类型的实例
        String implClassName = implClass.getName();
        if (!instanceCache.containsKey(implClassName)) {
            try {
                instanceCache.put(implClassName, implClass.newInstance()); //懒加载？ 如果实例缓存中没有 创建一个并返回
            } catch (InstantiationException | IllegalAccessException e) {
                String errorMsg = String.format("%s 类实例化失败", implClassName);
                throw new RuntimeException(errorMsg, e);
            }
        }
        return (T) instanceCache.get(implClassName);
    }

    /**
     * 加载某个类型
     *
     * @param loadClass
     * @throws IOException
     */
    public static Map<String, Class<?>> load(Class<?> loadClass) {
        log.info("加载类型为 {} 的 SPI", loadClass.getName()); //loadClass是Serializer接口
        // 扫描路径，用户自定义的 SPI 优先级高于系统 SPI
        Map<String, Class<?>> keyClassMap = new HashMap<>();
        for (String scanDir : SCAN_DIRS) {
            List<URL> resources = ResourceUtil.getResources(scanDir + loadClass.getName());
            // 读取每个资源文件
            for (URL resource : resources) {
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(resource.openStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] strArray = line.split("="); //例子 ： jdk=com.hqj.easyrpc.serializer.JdkSerializer 对这句话进行左右拆分
                        if (strArray.length > 1) {
                            String key = strArray[0];
                            String className = strArray[1];
                            keyClassMap.put(key, Class.forName(className)); //实现类的map 也就是loaderMap的value
                        }
                    }
                } catch (Exception e) {
                    log.error("spi resource load error", e);
                }
            }
        }
        loaderMap.put(loadClass.getName(), keyClassMap); //对于一个接口 比如 Serizlizer 已经扫描完配置文件中设置的实现类
        return keyClassMap;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        loadAll();
//        System.out.println(loaderMap);
//        Serializer serializer = getInstance(Serializer.class, "jdk");
//        System.out.println(serializer);
        /**
         * 测试ServiceLoader
         */
        Serializer serializer = null;
        ServiceLoader<Serializer> serializers = ServiceLoader.load(Serializer.class);
        for(Serializer serializer1 : serializers) {
            System.out.println("serializer1 = " + serializer1);
        }
    }
}