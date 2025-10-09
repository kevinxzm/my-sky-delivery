package com.kevin.aspect;

import com.kevin.Enum.UpdateEnum;
import com.kevin.context.BaseContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
public class AutoFillDateUserAspect {
    private static final Logger log = LoggerFactory.getLogger(AutoFillDateUserAspect.class);

    @Around("execution(* com.kevin.controller.imp.AdminControllerImp.*(..))")
    public Object adminControllerAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("admin controller aspect");
        System.out.println(joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @Pointcut("execution(* com.kevin.controller.imp.CategoryControllerImp.*(..))")
    public void pointcutMethod() {
    }

    @Before("pointcutMethod()")
    public void categoryControllerAspect(JoinPoint joinPoint) throws Throwable {
        log.info("category controller aspect");
        System.out.println(joinPoint.getSignature());
    }


    @Before("@annotation(com.kevin.aspect.AutoFillDateUser)")
    public void beforeAddDate(JoinPoint joinPoint) throws Exception {
        //拿到方法和参数的相关信息
        log.info(joinPoint.getTarget().getClass().getName());
        log.info(joinPoint.getSignature().toString());
        Object[] args = joinPoint.getArgs();

        int i = Integer.MIN_VALUE;

        // 如何取到“update”

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        AutoFillDateUser addDate = method.getAnnotation(AutoFillDateUser.class);
        UpdateEnum value = addDate.value();

        Method setUpdateTime;
        Method setUpdateUser;

        setUpdateTime = args[0].getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
        setUpdateTime.invoke(args[0], LocalDateTime.now());

        setUpdateUser = args[0].getClass().getDeclaredMethod("setUpdateUser", Long.class);
        setUpdateUser.invoke(args[0], BaseContext.getTokenId());

        if (value == UpdateEnum.ADD) {
            Method setCreateTime = args[0].getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
            setCreateTime.invoke(args[0], LocalDateTime.now());
            Method setCreateUser = args[0].getClass().getDeclaredMethod("setCreateUser", Long.class);
            setCreateUser.invoke(args[0], BaseContext.getTokenId());
        }

        System.out.println("after auto fill date user aspect" + args[0]);

    }


}
