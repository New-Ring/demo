package com.mengma.service.impl;

import com.mengma.ioc.PersonDao;
import com.mengma.ioc.impl.PersonDaoImpl;
import com.mengma.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class PersonServiceImpl implements PersonService {
    // 定义接口声明
//    @Resource
    private PersonDao personDao;
    // 提供set()方法，用于依赖注入
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
    //构造注入
//    private PersonDao personDao = new PersonDaoImpl();
    // 实现PersonService接口的方法
    @Override
    public void addPerson() {
        personDao.add(); // 调用PersonDao中的add()方法
        System.out.println("addPerson()执行了...");
    }
}
