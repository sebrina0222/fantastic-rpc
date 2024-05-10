package com.hqj.example.provider;

import com.hqj.example.common.model.User;
import com.hqj.example.common.service.UserService;

/**
 * ClassName: UserServiceImpl
 * Package: com.hqj.example.provider
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 13:01
 * @Version v1.0
 */

/**
 * 用户接口实现类
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名" + user.getName());
        return user;
    }
}
