package com.mengma.reflex;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest01 {
    @Test
    public void test(){
        User user = new User("范凯",12);
        /**
         * jdk动态代理必须有接口实现才能使用
         */
        DynamicFactory factory = new DynamicFactory(new UserImpl1());
        UserInterface1 face = (UserInterface1)factory.getBean();
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
interface UserInterface1{
    void say(User user);
}

class UserImpl1 implements UserInterface1{
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
        return method.invoke(this.proxy,args);
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
        return method.invoke(this.proxy, args); // 目标方法执行
    }

    //生成代理类
    public Object getBean(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(proxy.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
