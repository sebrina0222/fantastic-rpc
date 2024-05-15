package com.hqj.easyrpc;

import com.hqj.easyrpc.config.RegistryConfig;
import com.hqj.easyrpc.config.RpcConfig;
import com.hqj.easyrpc.constant.RpcConstant;
import com.hqj.easyrpc.registry.Registry;
import com.hqj.easyrpc.registry.RegistryFactory;
import com.hqj.easyrpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: RpcApplication
 * Package: com.hqj.easyrpc
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/11 - 20:53
 * @Version v1.0
 */

/**
 * 第二章内容 RPC 框架应用
 * 相当于 holder，存放了项目全局用到的变量，双检锁单例模式实现
 *
 * 以下代码时双检锁单例模式的经典实现，支持再获取配置时才调用init方法实现懒加载
 * 为了便于扩展 还支持传入配置对象；如果不传入，则默认调用前面写好的 ConfigUtils来加载配置
 */
@Slf4j
public class RpcApplication {
    private static volatile RpcConfig rpcConfig;

    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
        //5.注册中心初始化
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("registry init, config = {}", registryConfig);

        // [测试成功] 6.创建并注册 Shutdown Hook JVM退出时执行destroy操作
        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy));
    }


    public static void init(){
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            // 配置加载失败,使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    public static RpcConfig getRpcConfig(){
        if(rpcConfig == null) { //先判断是否为null，再加锁，以免所有线程一进来就加锁，加锁这个动作是比较消耗资源的000
            synchronized (RpcApplication.class) {
                if(rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
