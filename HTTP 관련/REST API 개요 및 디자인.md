# REST API 디자인

## 1. **Rest : Representational State Transfer API**

REST는 Representational State Transfer의 줄임말로, 웹을 위한 네트워크 기반 아키텍처 스타일이다. 

- **REST 구성**
    1. 자원 (Resource) : URI
    2. 행위 (Verb) : HTTP METHOD
    3. 표현 (Representations)

- **REST의 특징**
    1. **Uniform Interface**

        URI로 지정한 리소스에 대한 조작을 통일되고 한정적인 인터페이스로 수행하는 아키텍쳐 스타일

    2. **Stateless : 무상태**

        무상태성이란 서버가 클라이언트의 정보를 저장을 하지 않는 다는 것이다.  세션, 쿠키 등에 저장하고 관리를 하는데, 서버는 전혀 저장하지도 관리하지도 않는다. 

        그렇기 때문에 API서버는 요청만 단순히 처리한다. 그로인해 자유도가 높아지고 서버에 불필요한 정보를 저장하지 않아 구현이 단순해 진다.

    3. **Cacheable(캐시 가능)**

        가장큰 특징은 HTTP 기존 웹 표준 그대로 사용여 웹의 인프라 그대로 활용가능

        따라서 HTTP 프로토콜 표준에서 사용하는 Last-Modified 캐그 및 E-Tag를 이용하면 캐싱 구현이 가능

    4. **Self-descriptiveness (자체 표현구조)**

        REST API 메시지만 보고도 쉽게 이해 할 수 잇는 구조

    5. **Client - Server 구조**

        REST 서버는 API 제공, 클라이언트는 사용자 인증이나 컨텐스트(세션, 로그인 정보) 등을 직접 관리하는 구조로 **각각의 역할이 확실**하기 때문에  **클라이언트와 서버에서 개발해야할 내용이 명확**해지고 서로간 **의존성**이 줄어들게 된다. 

    6. **계층형 구조**

        REST 서버는 **다중계층으로 구성될 수 있으며** 보안, 로드 밸런싱, 암호화 계층을 추가해 **구조상의 유연성을 둘 수 있고** PROXY, 게이트웨이 같은 기반의 중간 매체를 사용할 수 있다. 

    [HTTP Method 종류 및 역할](https://www.notion.so/9b4f31fb28434cc08af1718e1c9f6a3f)

    - **HTML과 HTTP**

        **HTML (Hyper Text Markup Language)**

        웹 페이지만드는 언어이며 웹 브라우저 위에서 동작하는 언어이다. 웹 서버는 URL에 해당하는 HTML 문서를 전송하는데 HTML 문서를 받은 웹 브라우저는 정해진 규칙에 따라 HTML 문서를 분석하여 화면을 렌더링 한다.

        **HTTP(Hyper Text Transfer Protocol)**

        HTML은 HTTP라는 방식을 이용하여 모든 데이터를 주고 받을 수 있다.

        HTTP는 크게 두가지 관점에서 규칙을 정의한다.

        1. **요청** 

            웹 브라우저가 웹 서버에 HTML같은 요청을 할 떄 사용하는 데이터 구성규칭 

        2. **응답** 

            웹 서버가 웹 브라우저에 전송할 때 사용하는 데이터 구성 규정

        
	
	![Untitled](https://user-images.githubusercontent.com/63430211/116780752-71367c80-aab9-11eb-8700-387ad1cc3f57.png)
	

        > **HTML에서 GET/ POST만 지원하는 이유**

        [REST - HTML Form에서 GET/POST만 지원하는 이유](http://haah.kr/2017/05/23/rest-http-method-in-html-form/)

## 2. REST API 설계

1. **Resouse 중심 REST ful API URI 설계**
    - REST API는 URL만 보고 의미를 알 수 있어야 한다.
    - 길게 만들기 보다 짧게 만들어 제공
    - URL은 대도록 명사를 사용
    - 불필요한 파라미터를 URI Path에 추가하지 말자

```java
[잘못된 예] 
HTTP Post로 모두 정의하고 get/set을 URL에 명시함으로서행위를 구분하고자 하였다.
   HTTP Post : /getDevices
   HTTP Post : /setDevices

   [올바른 예]

   HTTP Get  :  /devices
   HTTP Post : /devices/{cleaner}
```

- 동일한 URI에  행위에 맞게 메서드 적용

```java
**잘못된 방식의 예**

1. **게시판 멤버 리스트 조회**.  
		[GET 요청] http://로컬호스트 8080/api/member/list

**2. 게시판 멤버 중 "한성준"을 조회한다.**
		[GET 요청] http://로컬호스트 8080/api/member/list/한성준
or  [GET 요청] http://로컬호스트 8080/member/list?name=한성준

**3. 게시판에 신규 멤버 등록**
		[POST 요청] http://로컬호스트 8080/api/member/create-member
**4. 게시판 멤버에서 "한성준"을 삭제한다.**
		[DELETE요청] http://로컬호스트 8080/api/member/delete/한성준
```

위에 사용된 list, create, delete 등은 불필요하다. 

RestAPI 철학을 잘 활용하기 위해선 **동일한 URL에 메서드를 활용해서 디자인**해야한다. 

```java
**개선된 방식 예**

1. **게시판 멤버 리스트 조회**.   
		[GET요청] http://로컬호스트 8080/member/

**2. 게시판 멤버 중 "한성준"을 조회한다.**  
		[GET요청] http://로컬호스트 8080/member/한성준

**3. 게시판에 신규 멤버 등록**.  
		[POST요청] http://로컬호스트 8080/member/

**4. 게시판 멤버에서 "한성준"을 삭제한다.** 
		[DELETE요청] http://로컬호스트 8080/member/한성준
```

## 부록. 스프링 부트에서의 REST API

- **@RestController**

    RestController는 클래스 레벨에 선언할 수 있으며, 이 RestController가 붙은 클래스의 모든 메서드는 자동으로 @ResponseBody가 적용된다.

    그리고 테스트 결과는 아래와 같다.

![Untitled 2](https://user-images.githubusercontent.com/63430211/116780736-6845ab00-aab9-11eb-8821-e9d200adb448.png)


![Untitled 3](https://user-images.githubusercontent.com/63430211/116780742-6b409b80-aab9-11eb-9ce7-7bd0138e5950.png)
     

- **@ResponseBody**

    메서드에  @ResponseBody가 붙게되면 html 형식으로 화면에 출력하는게 아니라 데이터 자체로 리턴을 한다. 

    아래 그림은 게시판 구현 중 db에서 데이터를 가져오는 메서드이다. 

    반환값과 return 값을 db로부터 값을 가져오는 객체로 지정을 하면 아래와 JSON 형식의 결과를 얻을 수 있다. 

![Untitled 1](https://user-images.githubusercontent.com/63430211/116780724-5ebc4300-aab9-11eb-99c3-6da0b0602f5f.png)

![Untitled 4](https://user-images.githubusercontent.com/63430211/116780746-6e3b8c00-aab9-11eb-8331-3070bc31493a.png)



 
