package com.baizhi.entity;

import com.baizhi.dao.EmpDao;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
@Data//可以省去 get，set等
@Accessors(chain = true)//如果设置为true生成的setter方法返回this（当前对象）。
public class Emp  {
    private String id;
    private String name;
    private String path;
    private Double salary;
    private Integer age;
}
