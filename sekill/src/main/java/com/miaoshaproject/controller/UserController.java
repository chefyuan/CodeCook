package com.miaoshaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("user")
@RequestMapping("user")
public class UserController {
   public void getUser(@RequestParam(name = "id") Integer id) {
       //调用service服务获取对应 id 并返回给前端
   }
}
