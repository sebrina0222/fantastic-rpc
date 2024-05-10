package com.hqj.easyrpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * ClassName: RpcResponse
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
public class RpcResponse implements Serializable {

    /**
     * 响应数据
     */
    private Object data;
    /**
     * 响应数据类型（预留）
     */
    private Class<?> dataType;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 异常信息
     */
    private Exception exception;
}
