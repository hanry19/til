package com.example.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
@Log4j2
public class LogAopHelper {

//    @GetMapping 설정된  메소드 또는 클래스 설정
//    @GetMapping 애노테이션이 설정된 특정 클래스, 메소드에만 AspectJ가 적용된다.
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void GetMapping() {

    }

    @Before("GetMapping()")
    public void before(JoinPoint joinPoint) {
        log.info("::::::::: aspectJ test :  Before Logging start :::::::::::");
        log.info("::::::::: aspectJ test :  Before Logging end :::::::::::");
    }

    /*
       @param joinPoint
       @param result
    */
    @AfterReturning(pointcut = "GetMapping()", returning = "reuslt")
    public void AfterReturning(JoinPoint joinPoint, Object reuslt){
        log.info("::::::::: aspectJ test :  AfterReturning Logging start :::::::::::");
        log.info("::::::::: aspectJ test :  AfterReturning Logging end :::::::::::");
    }


    /*
      @param joinPoint
      @return
      @throws Throwable
     */
    @Around("GetMapping()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("::::::::: aspectJ test :  Around Logging start :::::::::::");
        try{
            Object result = joinPoint.proceed();
            log.info("::::::::: aspectJ test :  Around Logging end :::::::::::");
            return result;
        }catch (Exception e){
            log.error("::::::::: aspectJ  Around Exception:::::::::::");
            log.error(e.toString());
            return  null;

        }


    }




}
