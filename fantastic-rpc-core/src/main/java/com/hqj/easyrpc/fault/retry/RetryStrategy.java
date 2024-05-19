package com.hqj.easyrpc.fault.retry;

import com.hqj.easyrpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * ClassName: RetryStrategy
 * Package: com.hqj.easyrpc.fault.retry
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/19 - 14:30
 * @Version v1.0
 */
public interface RetryStrategy {
    /**
     * 重试
     * @param callable
     * @return
     * @throws Exception
     */

    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}
