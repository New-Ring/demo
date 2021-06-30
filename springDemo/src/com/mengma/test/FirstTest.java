package com.mengma.test;

import com.mengma.entity.Person;
import com.mengma.ioc.CustomerDao;
import com.mengma.ioc.PersonDao;
import com.mengma.service.PersonService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FirstTest {
    @Test
    public void test() {
        // 初始化Spring容器，加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 通过容器获取personDao实例
        PersonDao personDao = (PersonDao) applicationContext.getBean("personDao");
        // 调用 personDao 的 add ()方法
        personDao.add();

        PersonService personService = (PersonService) applicationContext.getBean("personService");
        // 调用personService的addPerson()方法
        personService.addPerson();

        System.out.println(applicationContext.getBean("personDao1"));
        System.out.println(applicationContext.getBean("personDao1"));

        /*Person person = (Person)applicationContext.getBean("person");
        System.out.println(person.getName()+":"+person.getAge());*/

        Person person2 = (Person)applicationContext.getBean("person2");
        System.out.println(person2.getName()+":"+person2.getAge());
    }

    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationAop.xml");
        // 从spring容器获取实例
        CustomerDao customerDao = (CustomerDao) applicationContext.getBean("customerDao");
        // 执行方法
        customerDao.add();
    }


    public static void main(String[] args) {
        /*List<String> list = new ArrayList<>();
        if(list.contains("111")){
            System.out.println("1213");
        }
        System.out.println("3212");*/

        System.out.println("输入：");
        Scanner in = new Scanner(System.in);
        //获取String值
        String a = in.nextLine();
        String b = a.trim();
        Integer.valueOf("111");
        System.out.println("输出："+b);
    }
}
