package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dataobject.UserDO;
import com.miaoshaproject.dataobject.UserPasswordDO;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDOMapper userDOMapper;
    @Override
    public UserModel getUserById(Integer id) {
        //调用userdomapper获取对应的databoject
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);

    }
    private  UserModel convertFromDtaObject(UserDO userDO, UserPasswordDO userPasswordDO) {
        UserModel userModel = new
    }

}
