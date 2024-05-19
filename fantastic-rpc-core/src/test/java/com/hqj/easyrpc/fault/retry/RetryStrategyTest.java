package com.hqj.easyrpc.fault.retry;

import com.hqj.easyrpc.model.RpcResponse;
import org.junit.Test;

import java.util.concurrent.Callable;

/**
 * ClassName: RetryStrategyTest
 * Package: com.hqj.easyrpc.fault.retry
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/19 - 14:40
 * @Version v1.0
 */
public class RetryStrategyTest {
    RetryStrategy retryStrategy = new FixedIntervalRetryStrategy();

    @Test
    public void doRetry() {
        RpcResponse rpcResponse = null;
        try {
            rpcResponse = retryStrategy.doRetry(() -> {
                System.out.println("测试重试");
                //报错 来模拟重试失败
                throw new RuntimeException("模拟重试失败");
            });
            System.out.println(rpcResponse);
        } catch (Exception e) {
            System.out.println("重试多次失败");
            e.printStackTrace();
        }

    }
}
