# AOP (Aspect Oriented Programming) #1


<br/>


# AOP : 관점 지향 프로그래밍

AOP는 **애플리케이션**에서 관심사의 분리(공통기능의 분리)  **핵심적인 기능**에서 부가적인 기능을 나누어보고 그 관점을 기준으로 각각 모듈화한다. (* 모듈화는 어떤 공통된 로직이나 기능을 하나의 단위로 묶는 것) 이렇게 모듈화 함으로써 핵심 기능을 구현한 코드의 수정없이 공동 기능을 적용할 수 있게 만들어 준다. 

**AOP의 기본 개념은 핵심기능에 공통(반복되는) 기능을 삽입하는 것**이다. 즉, **핵심기능의 코드를 수정하지 않으면서 공통기능의 구현을 추가한 것이다**.  ⇒ Aspect!!

  AOP에서 **각 관점을 기준으로 로직을 모듈화 한다는 것은 코드들을 부분적으로 나누어서 모듈화 하겠다는의미**다. 이때 **소스 코드 상에서 다른 부분에 계속 반복해서 쓰는 코드들**을 발견할 수 있는데 이것을 **흩어진 관심사, 부가기능(Cross-cutting Concenrns )**라고 부른다. 

OOP를 적용하여도 핵심기능에서 부가기능을 쉽게 분리된 모듈로 작성하기 어려운 문제점을 AOP가 해결해 준다고 볼 수 있다. 

AOP는 부가 기능을 aspect로 정의하여, 핵심기능에서 부가기능을 분리함으로써 핵심기능을 설꼐하고 구현할 때 객체지향적인 가치를 지킬 수 있도록 도와주는 개념이다. 

![Untitled](https://user-images.githubusercontent.com/63430211/120209827-159f1080-c26a-11eb-9342-167dcd42b077.png)

## AOP 주요 개념



- **Aspect**

    여러 객채에 공통으로 적용되는 기능 (흩어진 관심사) ex: transaction, 보안 등

- **Target**

    Aspect를 적용하는 곳 (clacss, method...)

- **Advice**

    실질적으로 어떤 일을 해야 할지에 대한 것, 실질적인 부가기능을 담은 구현체

- **JoinPoint**

     Advice를 적용 가능한 지점을 의미. 끼어들 수 있는 지점. 메서드 진입 지점, 생선자 호출 시점, 필드에서 값을 꺼내올 때 등 다양한 시점에서 적용 가능

- **PointCut**

    JoinPoint의 상세한 스펙을 정의한 것. 

## Spring AOP 특징



- 프록시 패턴 기반의 AOP 구현체이다
- 프록시 객체를 쓰는 이유는 접근 제어 및 부가기능을 추가하기 위해서이다.
- 스프링 bean에만 AOP적용 가능
- 모든 AOP기능을 제공하는 거싱 아닌 스프링 loC와 연동하여 엔터프라이즈 애플리케이션에서 가장 흔한 문제에 대한 해결책을 지원하는 것이 목적

## Spring Boot AOP 적용



- **@Pointcut**

    aspectJ를 적용할 타겟을 정의해준다. 

    전체 컨트롤러의 함수대상, 특정 어노테이션을 설정한 함수대상, 특정 메소드 대상 등 개발자가 적용하길 원하는 범위를 정의하는 어노테이션

- **@Before**

    aspectJ를 적용할 타겟 메소드가 실행되기 '전' 수행됨

- **@AfterReturning**

    aspectJ를 적용할 타겟 메소드가 실행된 '후' 수행됨 (제일 마지막에 수행됨)

- **@Around**

    aspectJ를 적용할 타겟 메소드 실행 전 , 후 처리를 모두 할 수 있음

1. build.gradle

    ```jsx
    	implementation 'org.springframework.boot:spring-boot-starter-aop'
    ```

2. controller.java

    ```jsx
    package com.example.aop.controller;

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class AopController {

        @GetMapping("/test")
        public String hello(){
            return "hello world";
        }
    }
    ```

3. LogAopHelper.java

```jsx
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
```

위 코드에서 @Pointcut은 @GetMapping이 선언된 메소드에만 aspectJ가 적용되도록 제한하였다. 

그리고 @Before ,@AfterReturning, @Around는 파라미터로 GetMapping()함수를 선언해 해당 범위에 적용할 수 있도록 환경을 만들었다.

이렇게하여 @GetMapping 이 있는 메소드에 aspectJ 적용이 가능해 졌다. 

위와 같이 class를 작성을 하고 실행을 하면 아래와 같은 결과를 얻을 수 있다. 

순서로는 @Around시작 ⇒ @Before시작 및 종료 ⇒ @Around 종료 ⇒ @AfterReturning 시작 및 종료 순서로 실행되는것을 확인할 수 있다. 

@Around 의 시작과 종료는 Object result = joinPoint.proceed(); 전후로 나뉜다.


![Untitled 1](https://user-images.githubusercontent.com/63430211/120209856-1cc61e80-c26a-11eb-902f-ff8555495a24.png)



# 느낀점



현재 프로젝트를 진행하면서 log를 찍는 작업을 하고 있다. 이 log를 찍는 목적은 server에 request를 할 때 나의 request가 어디를 향하고 있고, error가 발생한다면 어디서 발생하는지 알고 싶어서 log를 찍기 시작하였다. 

그러던 순간 이 log를 하나하나 작성하여 넣는것이 여간 귀찮은게 아니었다. log를 찍는것도 하나의반복이고 부가적인 작업이었고, 그걸 모듈화하여 모든 클래스, 메서드에서 사용할 수 있는 하나의 기능으로 적용할 수 있는 점에서 너무 신선하게 다가왔다. 

내가 한 행동도 생각해보면 내 코드가 어떻게 흘러가는가에 대한 과정에 대한 "관점"을 관찰기 위한 행동이었다. 

아래 사진을 현재 진행중인 코드 일부를 보더라도 관점을 보기 위해 무분별한 log.info를 볼 수 있다. 이러한 불필요한 반복을 줄이기 위해 오늘 공부한 부분에 대한 개념과 간단한 실습을 확장시켜 적용시켜 봐야겠다. 


![Untitled 2](https://user-images.githubusercontent.com/63430211/120209886-23ed2c80-c26a-11eb-8f35-13f4a93ffda4.png)


<br/>


<br/>


> 출처


[[Spring] 스프링 AOP (Spring AOP) 총정리 : 개념, 프록시 기반 AOP, @AOP](https://engkimbs.tistory.com/746)



[[SpringBoot] 스프링부트 aop Aspectj 적용하기 - 메소드 실행시 특정 로직 수행](https://vmpo.tistory.com/100)

[[스프링/Spring] AOP 개념 설명](https://ktko.tistory.com/entry/Spring-AOP-%EA%B0%9C%EB%85%90-%EC%84%A4%EB%AA%85)