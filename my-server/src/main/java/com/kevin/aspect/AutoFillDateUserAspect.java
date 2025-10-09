package com.kevin.aspect;

import com.kevin.Constant.AutoFillConstant;
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

import java.lang.reflect.InvocationTargetException;
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
    public void beforeAddDate(JoinPoint joinPoint)  {
        //拿到方法的参数
        Object[] args = joinPoint.getArgs();

        if (args[0] == null) {
            return;
        }

        // 拿到方法的方法对象（通过signature），然后拿到方法的注解
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod(); //获得方法对象 //getName是获得方法名
        AutoFillDateUser autoFillDateUser = method.getAnnotation(AutoFillDateUser.class);//获取指定的注解
        UpdateEnum value = autoFillDateUser.value();


        Method setUpdateTime;
        Method setUpdateUser;

        try {
            setUpdateTime = args[0].getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
            setUpdateTime.invoke(args[0], LocalDateTime.now());

            setUpdateUser = args[0].getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
            setUpdateUser.invoke(args[0], BaseContext.getTokenId());

            //getClass() 方法参数对象
            if (value == UpdateEnum.ADD) {
                Method setCreateTime = args[0].getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                setCreateTime.invoke(args[0], LocalDateTime.now());
                Method setCreateUser = args[0].getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                setCreateUser.invoke(args[0], BaseContext.getTokenId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("after auto fill date user aspect" + args[0]);

    }


}
