package com.hqj.easyrpc.fault.tolerant;

import com.esotericsoftware.minlog.Log;
import com.hqj.easyrpc.model.RpcResponse;

import java.util.Map;

/**
 * ClassName: Fail
 * Package: com.hqj.easyrpc.fault.tolerant
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/20 - 14:20
 * @Version v1.0
 */

/**
 * 静默处理异常，遇到异常之后，记录一条日志，正常返回一个响应对象
 */
public class FailSafeTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        Log.info("静默处理异常", e);
        return new RpcResponse();
    }
}
