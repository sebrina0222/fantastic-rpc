package com.hqj.examplespringbootconsumer;

import com.hqj.example.common.model.User;
import com.hqj.example.common.service.UserService;
import com.hqj.fantasticrpcspringbootstarter.starter.annotation.RpcReference;
import org.springframework.stereotype.Service;

/**
 * ClassName: ExampleServiceImpl
 * Package: com.hqj.examplespringbootconsumer
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/20 - 17:00
 * @Version v1.0
 */
@Service
public class ExampleServiceImpl {
    @RpcReference
    private UserService userService;

    public void test() {
        User user = new User();
        user.setName("huqijian");
        User user1 = userService.getUser(user);
        System.out.println(user1.getName());
    }
}
