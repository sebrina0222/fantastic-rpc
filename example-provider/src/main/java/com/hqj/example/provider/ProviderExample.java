package com.hqj.example.provider;

import com.hqj.easyrpc.bootstrap.ProviderBootstrap;
import com.hqj.easyrpc.model.ServiceRegisterInfo;
import com.hqj.example.common.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ProviderExample
 * Package: com.hqj.example.provider
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/20 - 15:20
 * @Version v1.0
 */
public class ProviderExample {
    public static void main(String[] args) {
        //要注册的服务
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo serviceRegisterInfo = new ServiceRegisterInfo(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);

        //服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}
