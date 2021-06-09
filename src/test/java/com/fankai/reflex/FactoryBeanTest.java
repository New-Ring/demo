package com.fankai.reflex;

import com.fankai.entity.User;
import com.fankai.factorybean.CustomerDao;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanTest {

    @Test
    public void test() throws IllegalAccessException, InstantiationException {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        CustomerDao customerDao = (CustomerDao) applicationContext.getBean("customerDao");
//        customerDao.add();
        /*customerDao.update();
        customerDao.delete();
        customerDao.find();*/
        /*final User user  = User.class.newInstance();
        user.setName("111");
        change(user);
        System.out.println(user.getName());*/

        String str = "hello";
        String ss = changeStr(str);
        System.out.println(str);
        System.out.println(ss);
    }

    private String changeStr(String str) {
        String ss = str+"1111";
        System.out.println(ss);
        return ss;
    }

    private static void change(User user) {
        user.setName("2222");
        System.out.println(user.getName());
    }
}
