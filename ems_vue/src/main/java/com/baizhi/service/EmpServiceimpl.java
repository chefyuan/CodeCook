package com.baizhi.service;

import com.baizhi.dao.EmpDao;
import com.baizhi.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpServiceimpl implements EmpService{
    @Autowired
    private EmpDao empDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Emp> findAll() {
        return empDao.findAll();
    }
    @Override
    public void save(Emp emp) {
        empDao.save(emp);
    }
    @Override
    public void delete(String id) {
        empDao.delete(id);
    }

    @Override
    public Emp findOne(String id) {

        return empDao.findOne(id);
    }
    @Override
    public void update(Emp emp) {
        empDao.update(emp);
    }
}
