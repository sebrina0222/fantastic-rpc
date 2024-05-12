package com.hqj.example.common.service;

import com.hqj.example.common.model.User;

/**
 * ClassName: UserService
 * Package: com.hqj.example.common.service
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 12:55
 * @Version v1.0
 */
public interface UserService {
    /**
     * 获取用户方法
     * @param user
     * @return
     */
    User getUser(User user);


    /**
     * 第三章 新方法 - 获取数字
     */
    default short getNumber() {
        return 1;
    }
}
