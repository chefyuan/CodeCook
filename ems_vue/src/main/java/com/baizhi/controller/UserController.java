package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.utills.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin //允许跨域
@RequestMapping("user")
@Slf4j

public class UserController {

    @Autowired
    public UserService userService;

    //用来处理用户登录
    @PostMapping("login")
    //获取到前端传过来之值
    public Map<String,Object> login(@RequestBody User user) {
        log.info("当前用户登录信息：[{}]",user.toString());
        Map<String,Object> map = new HashMap<>();
        try{
            User userDB = userService.login(user);
            map.put("state",true);
            map.put("msg","登录成功");
            map.put("user",userDB);
        } catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }

        return map;
    }

    @PostMapping("regiest")
    public Map<String,Object> register(@RequestBody User user , String code, HttpServletRequest request) {
        log.info("用户信息:[{}]",user.toString());
        log.info("用户验证码消息:[{}]",code);
        Map<String,Object> map = new HashMap<>();

        try {
            String key = (String)request.getServletContext().getAttribute("code");
            if (key.equalsIgnoreCase(code)) {
                userService.register(user);
                map.put("state",true);
                map.put("massege","注册成功");
            } else {
                throw new RuntimeException("密码出现错误");
            }

        } catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("massege","提示+" + e.getMessage());
        }
        return map;

    }
    //生成验证码
    @GetMapping("getImage")
    public String getImageCode(HttpServletRequest request) throws IOException {

        //生成验证码

       String code =  VerifyCodeUtils.generateVerifyCode(4);
       // 64 位处理，前后端没有 session 的概念，放入servletContext作用域
        request.getServletContext().setAttribute("code",code);
        // 将图片转为 base 64
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(150,40,byteArrayOutputStream,code);
        String s = "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
        return  s;


    }
}
