package com.fankai.reflex;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式案例
 */
public class DynamicProxyTest01 {
    @Test
    public void test(){
        User user = new User("范凯",12);
        /**
         * jdk动态代理必须有接口实现才能使用
         */
        DynamicFactory factory = new DynamicFactory(new UserImpl());
        UserInterface face = (UserInterface)factory.getBean();
        face.say(user);

        /**
         * CGLIB代理,可以不用嵌入接口
         */
        CGLIBFactory factory1 = new CGLIBFactory(new UserImpl2());
        UserImpl2 user2 = (UserImpl2)factory1.getBean();
        user2.say(user);
    }
}

class UserImpl2{
    public void say(User user){
        System.out.println(user.getName()+":"+user.getAge());
    }
}

/**
 * 增强日志类
 */
class LogTest{
    public void before(Object obj) {
        System.out.println(obj);
    }
}

interface UserInterface{
    void say(User user);
}

class UserImpl implements UserInterface{
    @Override
    public void say(User user){
        System.out.println(user.getName()+":"+user.getAge());
    }
}

/**
 * jdk动态工厂
 */
class DynamicFactory implements InvocationHandler {
    private Object proxy;
    public DynamicFactory(Object proxy){
        this.proxy = proxy;
    }
    LogTest log = new LogTest();
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(args != null){
            log.before(args[0]);
        }
        Object obj = method.invoke(this.proxy,args);
        return obj;
    }

    //生成代理类
    public Object getBean(){
        return Proxy.newProxyInstance(proxy.getClass().getClassLoader(),proxy.getClass().getInterfaces(),this);
    }

}

/**
 * CGLIB动态工厂
 */
class CGLIBFactory implements MethodInterceptor {
    private Object proxy;

    public CGLIBFactory(Object proxy){
        this.proxy = proxy;
    }

    LogTest log = new LogTest();

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if(args != null){
            log.before(args[0]);
        }
        Object obj = method.invoke(this.proxy, args); // 目标方法执行
        return obj;
    }

    //生成代理类
    public Object getBean(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(proxy.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
}

class User{
    private String name;
    private int age;
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
