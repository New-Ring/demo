package com.mengma.ioc.impl;

import com.mengma.annotation.OperationLog;
import com.mengma.annotation.OperationType;
import com.mengma.ioc.CustomerDao;
import org.springframework.stereotype.Repository;

@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {
    @Override
    @OperationLog(detail = "注解aop案例",operationType = OperationType.UPDATE)
    public void add() {
        System.out.println("添加客户...");
    }
    @Override
    public void update() {
        System.out.println("修改客户...");
    }
    @Override
    public void delete() {
        System.out.println("删除客户...");
    }
    @Override
    public void find() {
        System.out.println("修改客户...");
    }
}
