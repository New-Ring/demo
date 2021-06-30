package com.mengma.ioc;

import com.mengma.annotation.OperationLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 注解类切面例子
 */
@Aspect
@Component
public class MyAspectAnnotation {

    /**
     * 切点方法注册
     */
//    @Pointcut("execution(* com.mengma.ioc..*.*(..))")  //基于类
    @Pointcut("@annotation(com.mengma.annotation.OperationLog)")
    private void pointCunt(){}

    // 前置通知
    @Before("pointCunt()")
    public void myBefore(JoinPoint joinPoint) {
        try {
            Class clazz = joinPoint.getTarget().getClass();
            String methodName = joinPoint.getSignature().getName();
            Class[] parameterTypes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
            Method method = clazz.getMethod(methodName,parameterTypes);
            OperationLog annotation = method.getAnnotation(OperationLog.class);
            if (annotation != null) {
                System.out.println("方法类型："+annotation.operationType().getValue());
            }
        } catch (Exception ex) {
            ex.toString();
        }

        /*System.out.print("前置通知，目标：");
        System.out.print(joinPoint.getTarget() + "方法名称:");
        System.out.println(joinPoint.getSignature().getName());*/
    }
}
