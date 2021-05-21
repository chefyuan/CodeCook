package com.baizhi.controller;

import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("emp")
@CrossOrigin
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;
    //获取员工列表的方法
    @Value("${photo.dir}")
    private String realPath;
    //删除员工实现
    @GetMapping("delete")
    public Map<String,Object> delete(String id) {
        //log.info("删除员工id[{}]",id);
        Map<String,Object> map = new HashMap<>();
        try {
            //删除头像
            Emp emp = empService.findOne(id);
            File file = new File(realPath, emp.getPath());
            if (file.exists()) {
                file.delete();//删除头像
            }
            //删除员工信息
            empService.delete(id);
            map.put("state",true);
            map.put("msg",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","删除员工失败");
        }
        return map;
    }
    //查询一个员工信息,根据id
    @GetMapping("findOne")
    public Emp findOne(String id) {
        log.info("查询员工id:[{}]",id);
        return empService.findOne(id);

    }
    //修改员工信息
    @PostMapping("update")
    public Map<String,Object> update(Emp emp, MultipartFile photo) {
        //这里需要注意格式
        log.info("员工信息: [{}]", emp.toString());
        if (photo != null && photo.getSize() != 0) {
            log.info("头像信息: [{}]", photo.getOriginalFilename());
        }

        Map<String,Object> map = new HashMap<>();
        //头像保存
        //获取原始文件的拓展名
        try {
            if (photo != null && photo.getSize() != 0) {
                log.info("头像信息: [{}]", photo.getOriginalFilename());
                String newFilename = UUID.randomUUID().toString() + "."+ FilenameUtils.getExtension(photo.getOriginalFilename());
                photo.transferTo(new File(realPath,newFilename));
                //设置头像地址
                emp.setPath(newFilename);
            }

            //保存员工信息
            empService.update(emp);
            map.put("state",true);
            map.put("msg","员工信息保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","保存失败");
        }

        return map;
    }
    @PostMapping("save")
    public Map<String,Object> save(Emp emp, MultipartFile photo) {
        //这里需要注意格式
        log.info("员工信息: [{}]", emp.toString());
        log.info("头像信息: [{}]", photo.getOriginalFilename());
        Map<String,Object> map = new HashMap<>();
        //头像保存
        //获取原始文件的拓展名
        try {
            String newFilename = UUID.randomUUID().toString() + "."+ FilenameUtils.getExtension(photo.getOriginalFilename());
            photo.transferTo(new File(realPath,newFilename));
            //设置头像地址
            emp.setPath(newFilename);
            //保存员工信息
            empService.save(emp);
            map.put("state",true);
            map.put("msg","员工信息保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","保存失败");
        }
        return map;
    }
    @GetMapping("findAll")
    public List<Emp> findAll() {
        return empService.findAll();
    }
    //保存员工信息
}
