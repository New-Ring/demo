package com.fankai.aspect;

import com.fankai.annotation.Bpm;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class BpmAspect {

    @Pointcut("@annotation(com.fankai.annotation.Bpm)")
    private void pointCunt() {
    }

    @Before("pointCunt()")
    public void myBefore(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs(); // 参数值

            for (Object obj : args) {
                System.out.println("参数：" + obj.toString());
            }
            Class clazz = joinPoint.getTarget().getClass();
            String methodName = joinPoint.getSignature().getName();
            Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
            Method method = clazz.getMethod(methodName, parameterTypes);
            Bpm annotation = method.getAnnotation(Bpm.class);
            if (annotation != null) {
                System.out.println("id:" + annotation.processDefId().getProcessDefId());
            }
        } catch (Exception ex) {
            ex.toString();
        }
    }
}
