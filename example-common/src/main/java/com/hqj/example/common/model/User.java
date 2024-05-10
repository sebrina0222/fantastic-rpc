package com.hqj.example.common.model;

import java.io.Serializable;

/**
 * ClassName: User
 * Package: com.hqj.example.common.model
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/10 - 12:55
 * @Version v1.0
 */
public class User implements Serializable {

    private String name;

    //TODO: 设立的set和get方法可以用注释简化
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
