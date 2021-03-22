package com.kould.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import java.util.Arrays;

@Aspect
@Component
public class ServiceTransactionalAop {
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager ;

    @Pointcut("execution(* com.kould.service..*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("##後置通知");
    }

    @AfterReturning(value = "pointCut()" , returning = "result")
    public void afterReturing(JoinPoint joinPoint,Object result) {
    }

    @AfterThrowing(value = "pointCut()" , throwing = "exception")
    public void throwException(JoinPoint joinPoint,Exception exception) {
        System.out.println("##異常通知" + exception.toString());
    }

    @Around("pointCut()")
    public Object arroundInvoke(ProceedingJoinPoint point) throws Throwable {
        TransactionStatus begin = dataSourceTransactionManager
                .getTransaction(new DefaultTransactionAttribute()); ;
        try {
            Object proceed = point.proceed();
            dataSourceTransactionManager.commit(begin);
            return proceed;
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(begin);
            return "事务已回滚!";
        }
    }
}
