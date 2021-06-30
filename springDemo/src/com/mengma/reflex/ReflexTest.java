package com.mengma.reflex;

import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射测试
 */
public class ReflexTest {

    @Test
    public void test() throws Exception{
       /* Class<?> aClass = Class.forName("com.mengma.reflex.User");
        System.out.println("会初始化静态域："+aClass);
        Class<User> userClass = User.class;
        System.out.println("不会初始化静态域："+userClass);*/
        /*Class<?> aClass = Class.forName("com.mengma.reflex.User");
        System.out.println(aClass);
        User u = (User)aClass.newInstance();
        System.out.println(u);*/

        User user = new User("范凯",18);
        Class<? extends User> clazz = user.getClass();

        ClassLoader classLoader = clazz.getClassLoader();//获取类加载器
        System.out.println(classLoader);
        Field[] fields = clazz.getDeclaredFields(); //获取类的所有字段
        for(Field field:fields){
            String key = field.getName();
            PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
            Method method = descriptor.getReadMethod();
            Object value = method.invoke(user);

            System.out.println(key + ":" + value);
        }
    }
}

class User{
    private String name;
    private int age;
    /*static int num = 1; //静态会在类初始化过程中被执行
    static{
        System.out.println("num:"+num);
    }*/

    public User(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
