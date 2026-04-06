package com.example.multipledatasource.common;

import com.example.multipledatasource.utils.DynamicDataSourceHolder;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

    @Around("@annotation(ds)")
    public Object around(ProceedingJoinPoint joinPoint, DataSourceType ds) throws Throwable{
        DynamicDataSourceHolder.setDataSourceType(ds.name());
        try{
            return joinPoint.proceed();
        } finally {
            DynamicDataSourceHolder.clearDataSourceType();
        }
    }

}
