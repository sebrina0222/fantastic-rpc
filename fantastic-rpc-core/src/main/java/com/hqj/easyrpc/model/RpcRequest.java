package com.hqj.easyrpc.model;

import com.hqj.easyrpc.constant.RpcConstant;
import com.hqj.easyrpc.serializer.Serializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: RpcRequest
 * Package: com.hqj.easyrpc.model
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 14:18
 * @Version v1.0
 */
@Data //是会生成无参数构造方法。
@Builder //发现生成了全属性的构造方法。
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 第五章 服务版本
     */
    private String serviceVersion = RpcConstant.DEFAULT_SERVICE_VERSION;
    /**
     * 参数类型列表
     */
    private Class<?>[] parameterTypes;
    /**
     * 参数列表
     */
    private Object[] args;
}
