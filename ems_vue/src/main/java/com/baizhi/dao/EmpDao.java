package com.baizhi.dao;

import com.baizhi.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpDao{
    List<Emp> findAll();
    void save(Emp emp);
    void  delete(String id);
    Emp findOne(String id);
    void update(Emp emp);
}
