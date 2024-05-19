package com.hqj.easyrpc.fault.retry;

import com.hqj.easyrpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * ClassName: NoRetryStrategy
 * Package: com.hqj.easyrpc.fault.retry
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/19 - 14:32
 * @Version v1.0
 */
public class NoRetryStrategy implements RetryStrategy {
    /**
     * 重试
     *
     * @param callable
     * @return
     * @throws Exception
     */
    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }
}
