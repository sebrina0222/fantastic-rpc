package com.hqj.examplespringbootprovider;

import com.hqj.example.common.model.User;
import com.hqj.example.common.service.UserService;
import com.hqj.fantasticrpcspringbootstarter.starter.annotation.RpcService;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl
 * Package: com.hqj.examplespringbootconsumer
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/20 - 16:58
 * @Version v1.0
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("注释测试===> 用户名：" + user.getName());
        return user;
    }
}
