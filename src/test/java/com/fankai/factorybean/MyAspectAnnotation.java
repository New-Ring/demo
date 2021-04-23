package com.fankai.factorybean;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 注解类切面例子
 */
@Aspect
@Component
public class MyAspectAnnotation {

    /**
     * 切点方法注册
     */
    @Pointcut("execution(* com.fankai.factorybean..*.*(..))")  //基于类
//    @Pointcut("@annotation(com.fankai.annotation.OperationLog)")
    private void pointCunt(){}

    // 前置通知
    @Before("pointCunt()")
    public void myBefore(JoinPoint joinPoint) {
        System.out.print("前置通知，目标：");
        System.out.print(joinPoint.getTarget() + "方法名称:");
        System.out.println(joinPoint.getSignature().getName());
    }
}
