package com.fankai.reflex;

import com.fankai.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式案例,
 * 1.jdk创建对象的速度远大于cglib，这是由于cglib创建对象时需要操作字节码。
 * 2.cglib执行速度略大于jdk，所以比较适合单例模式。
 * 3.另外由于CGLIB的大部分类是直接对Java字节码进行操作，这样生成的类会在Java的永久堆中。如果动态代理操作过多，容易造成永久堆满，触发OutOfMemory异常。
 * 4.spring默认使用jdk动态代理，如果类没有接口，则使用cglib。
 */
public class DynamicProxyTest {
    @Test
    public void test(){
        User user = new User();
        user.setAge(18);
        user.setName("范凯");
        /**
         * jdk动态代理必须有接口实现才能使用
         */
        DynamicFactory factory = new DynamicFactory(new UserImpl());
        UserInterface face = (UserInterface)factory.getBean();
        face.say(user);

        /**
         * CGLIB代理,可以不用嵌入接口
         */
        CGLIBFactory factory1 = new CGLIBFactory();
        UserImpl2 user2 = (UserImpl2)factory1.getBean(UserImpl2.class);
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
        if(args != null) log.before(args[0]);
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
    LogTest log = new LogTest();

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if(args != null) log.before(args[0]);
        return methodProxy.invokeSuper(proxy, args); // 目标方法执行
    }

    //生成代理类
    public Object getBean(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
}

