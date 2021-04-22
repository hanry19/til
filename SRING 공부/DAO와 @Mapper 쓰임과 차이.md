# DAO와 @Mapper 쓰임과 차이

> **참고**

[https://twofootdog.github.io/Spring-DAO와-Mapper의-차이점/](https://twofootdog.github.io/Spring-DAO%EC%99%80-Mapper%EC%9D%98-%EC%B0%A8%EC%9D%B4%EC%A0%90/)

[https://congsong.tistory.com/15](https://congsong.tistory.com/15)

---

> **서론**

스프링 MVC공부를 하면서 가장 궁금했던 것이 DAO와 DTO, VO 등의 역할이었다. 

DTO와 VO는 자주 마주할 수 있고, 개념을 찾아봤을 때 별다른 로직이 없고 각 계층간의 값들을 전달하는 역할을 한다고 하여 어느정도 개념을 이해하고 있었다. 

하지만 DAO에 대해서는 어떤 글을 보아도 시원하게 이해하기엔 어려움이 있었다. 

그래서 Spirng MVC구조에 대해서 계속해서 공부를 진행하였고, 드디어 궁금한 부분을 시원하게 긁어주는 글을 찾았다. 

이전까지 궁금했던 것은 DAO는 데이터베이스에서 값을 꺼내오는 역할을 한다고 하는데, 이녀석의 클래스는 어디에 있는가? 였다. 

아무리 눈을 씻고 찾아보고 코드를 하나하나 따라가도 나는 DAO의 행방을 알 수가 없었다. 

그래도 의심스러운 녀석은 데이터베이스과 통신하는 MAPPER.XML파일. 그리고 이 XML파일과 직결되는 MAPPER 인터페이스였다. 

> **결론 1**

**MAPPER 인터페이스에는 항상 @Mapper 라는 annotation을 작성한다**.  그 이유에 대해서 찾아 보면 늘 "해당 클래스가 mapper 라는 것을 알리는 것" 이라고만 했지, DAO의 역할을 대신한다는 건 못봤다. (내가 놓친 가능성이 더 크다.)

기존의 클래스는 DAO 클래스에서 @Repository를 선언하여 해당 클래스가 데이터베이스와 통신하는 클래스임을 나타냈지만**, MyBatis는 인터페이스에 @Mapper 를 지정해주면 XML Mpper에 지정한 메서드 이름과 일치하는 SQL문을 찾아서 실행 시킨다.** 

Mapper의 영역은 데이터베이스와의 통신, 즉 SQL 쿼리문을 호출 하는 것이 전부이며, 다른 로직은 필요하지않는 것이었다. 

---

## **그렇다면 DAO 클래스와 Mapper인터페이스의 차이는 어떨까?**

> **개념**

1. **Controller.java - Service.java - Mapper.java - Mapper.xml 형식으로 구조**

    Mapper.java = 단순 인터페이스

2. **Controller.java - Service.java -** *DAO.java* **- Mapper.xml 형식으로 구조**

    DAO.java =인터페이스와 클래스의 결합된 형태

---

- **DAO (Data Access Object)**

    DB에 접근하여 데이터 조회 및 조작하는 객체

    사용이유는 효율적인 커넥션 관리와 보안성

    DAO는 저수준 Logic과 고급 비즈니스 Logic을 분리하고 Domain Logic으로부터 DB관련 메커니즘을 숨기기 위해 사용

- **Mapper 인터페이스**

    Mybatis Mapper XML에 기재된 SQL을 호출하기 위한 인터페이스.

    Mybatis3.0부터 생겼다.

- **Mapper 인터페이스를 사용하지 않을 경우**
    1. sqlSession을 등록해줘야 한다. 
    2. DAO인터페이스와 구현체(DAO 클래스)를 생성해야한다.
    3. namespace +"." + SQL ID로 지정해서 SQL을 호출해야한다.

        ```java
        (예시)
        sesseion.selectOne(“com.test.mapper.TimeMapper.getReplyer, bno ))
        ```

    4. selectOne, insert, delete 등 제공하는 메소드를 사용해야 한다. 
    5. 문자열로 작성하기 때문에 버그가 생길 수 있다. 
    6. IDE에서 제공하는 code assist를 사용할 수 없다. 

- **Mapper인터페이스 사용 방법**
    - Mapper 인터페이스는 개발자가 직접 작성한다.
    - Mapper nameSpace는 package 명을 포함한 인터페이스 명으로 작성한다.
    - SQL ID는 인터페이스에 정의된 메서드명과 동일하게 작성한다.

> **의문점**

그렇다면 지금까지 Service단에서 Mapper 인터페이스를 호출하여 @Autowired를 붙여서 사용하였다. 

Autowired의 역할은 빈 컨테이너(bean container)에 등록된 객체를 new를 통하지 않고 싱글톤 방식으로 사용하겠다는 것인데, 어떻게 인터페이스만 작성했는데 무언가 스프링 빈으로 등록되고, @Autowired를 이용해 주입 될 수 있을까?

궁금하면 아래 링크 참고.. 

> 출처

[http://wiki.plateer.com/pages/viewpage.action?pageId=7767258](http://wiki.plateer.com/pages/viewpage.action?pageId=7767258)