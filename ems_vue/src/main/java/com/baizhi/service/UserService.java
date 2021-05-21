package com.baizhi.service;

import com.baizhi.entity.User;

public interface UserService {
    //用户注册
    void  register(User user);
    User login(User user);
}
