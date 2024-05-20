package com.hqj.easyrpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: ServiceRegisterInfo
 * Package: com.hqj.easyrpc.model
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/20 - 14:59
 * @Version v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRegisterInfo<T> {
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 实现类
     */
    private Class<? extends T> implClass;
}
