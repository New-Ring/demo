package com.mengma.test;

import com.mengma.ioc.CustomerDao;
import com.mengma.jdk.MyBeanFactory;
import org.junit.Test;

/**
 *动态代理和CGLIB代理测试
 */
public class JDKProxyTest {
    @Test
    public void test() {
        // 从工厂获得指定的内容（相当于spring获得，但此内容时代理对象）
        CustomerDao customerDao = MyBeanFactory.getBean();
        // 执行方法
        customerDao.add();
        customerDao.update();
        customerDao.delete();
        customerDao.find();
    }

    @Test
    public void test1() {
        // 从工厂获得指定的内容（相当于spring获得，但此内容时代理对象）
        CustomerDao goodsDao = MyBeanFactory.getBeanCG();
        // 执行方法
        goodsDao.add();
        goodsDao.update();
        goodsDao.delete();
        goodsDao.find();
    }
}
