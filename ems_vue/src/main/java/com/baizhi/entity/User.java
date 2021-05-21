package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class User {
    private  String id;
    private  String username;
    private  String realname;
    private  String password;
    private  String sex;
    private  String status;
    private  Date regeistTime;
}
