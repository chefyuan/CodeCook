package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Service
@Transactional
public class UserServiceImpl implements  UserService{
    //注入
    @Autowired
    private UserDAO userDAO;

    @Override
    public User login(User user) {
        //根据用户输入的用户名进行查询
        //大致含义如下，先获取对象 user 的名字,然后查询其是否存在，如果存在则比较密码
        User userDB = userDAO.findByUsername(user.getUsername());
        if (userDB != null) {
            //比较密码
           if (userDB.getPassword().equals(user.getPassword())){
                return userDB;
            } else {
               throw new RuntimeException("用户密码输入不正确");
           }
        } else {
            throw  new RuntimeException("用户名输入错误");
        }
    }

    @Override
    public void register(User user) {
        //生成用户状态
        User byUserName = userDAO.findByUsername(user.getUsername());
        if (byUserName == null) {
            user.setStatus("已激活");
            user.setRegeistTime(new Date());
            ///调用 dao 这些是自动生成的
            //调用 dao
            userDAO.save(user);
        } else {
            throw new RuntimeException("用户名已经存在");
        }


    }


}
