package com.mengma.jdk;

import com.mengma.ioc.CustomerDao;
import com.mengma.ioc.impl.CustomerDaoImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanFactory {

    /**
     * JDK动态代理
     * @return
     */
    public static CustomerDao getBean() {
        // 准备目标类
        final CustomerDao customerDao = new CustomerDaoImpl();
        // 创建切面类实例
        final MyAspect myAspect = new MyAspect();
        // 使用代理类，进行增强
        return (CustomerDao) Proxy.newProxyInstance(
                MyBeanFactory.class.getClassLoader(),
                new Class[] { CustomerDao.class },
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method,Object[] args) throws Throwable {
                        myAspect.myBefore(); // 前增强
                        Object obj = method.invoke(customerDao, args);
                        myAspect.myAfter(); // 后增强
                        return obj;
                    }
                });
    }

    /**
     * CGLIB动态代理
     * @return
     */
    public static CustomerDao getBeanCG() {
        // 准备目标类
        final CustomerDao goodsDao = new CustomerDaoImpl();
        // 创建切面类实例
        final MyAspect myAspect = new MyAspect();
        // 生成代理类，CGLIB在运行时，生成指定对象的子类，增强
        Enhancer enhancer = new Enhancer();
        // 确定需要增强的类
        enhancer.setSuperclass(goodsDao.getClass());
        // 添加回调函数
        enhancer.setCallback(new MethodInterceptor() {
            // intercept 相当于 jdk invoke，前三个参数与 jdk invoke—致
            @Override
            public Object intercept(Object proxy, Method method, Object[] args,
                                    MethodProxy methodProxy) throws Throwable {
                myAspect.myBefore(); // 前增强
                Object obj = method.invoke(goodsDao, args); // 目标方法执行
                myAspect.myAfter(); // 后增强
                return obj;
            }
        });
        // 创建代理类
        CustomerDao goodsDaoProxy = (CustomerDao) enhancer.create();
        return goodsDaoProxy;
    }
}
