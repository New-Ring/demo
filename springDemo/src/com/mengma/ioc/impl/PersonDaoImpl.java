package com.mengma.ioc.impl;

import com.mengma.ioc.PersonDao;

public class PersonDaoImpl implements PersonDao {
    @Override
    public void add() {
        System.out.println("save()执行了...");
    }
}
