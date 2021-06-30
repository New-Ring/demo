package com.mengma.reflex;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理测试类
 */
public class DynamicProxyTest {
    @Test
    public void test(){
        UserImpl user = new UserImpl();
        UserInterface face = (UserInterface)Proxy.newProxyInstance(
                UserInterface.class.getClassLoader(),
                new Class[]{UserInterface.class},
                new DynamicProxyHandler(user));
        face.eat();
        face.sleep();
    }
}

interface UserInterface{
    void eat();
    void sleep();
}
class UserImpl implements UserInterface{
    @Override
    public void eat() {
        System.out.println("人要吃饭");
    }
    @Override
    public void sleep() {
        System.out.println("人要睡觉");
    }
}
/**
 * 动态代理处理器
 */
class DynamicProxyHandler implements InvocationHandler {
    private Object proxyed;
    public DynamicProxyHandler(Object proxyed){
        this.proxyed = proxyed;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理工作了.");
        return method.invoke(proxyed, args);
    }
}
