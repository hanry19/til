# Exception 정리 및 스프링 적용

1. Error
    - 개발자가 해결할 수 없는 치명적인 오류
    - 하드웨어의 잘못된 동작 또는 고장으로 인한 오류
    - 에러가 발생되면 프로그램 종료
    - 정상 실행 상태로 돌아갈수 없다
2. Exception
    - 개발자가 해결할 수 있는 오류
    - 사용자의 잘못된 조작 또는 개발자의 잘못된 코딩으로 인한 오류
    - 예외가 발생되면 프로그램 종료
    - 예외처리 추가하면 정상 상태로 돌아갈 수 있다.
    - 예외가 발생하면 비정상적인 종료를 막고, 프로그램을 계속 진행 할 수 있도록 우회 경로를 제공하는 것이다.

# Exception

자바의 예외에는 일반예외와 실행 예외가 있고, 각각 Checked Exception , UnChecked Exception으로 부른다. 일반 예외는 개발자가 반드시 예외처리를 직접 진행해야 한다.

 모든 예외 클래스는 java.lang.Exception클래스를 상속 받는다. 

- Exception 클래스 자체는 checked exception(일반예외)이다.
- Exception 클래스는 Throwable클래스의 자식클래스이다.
- Exception 클래스의 자식 클래스 중 Runtime Exception클래스는 Unchecked(실행예외)이다.
- 그 외 checked exception이있다.

> **계층도**

![Untitled](https://user-images.githubusercontent.com/63430211/119994756-dbcecf80-c007-11eb-98b6-eb9c475f16c2.png)

1. **일발 예외 : 컴파일 예외** 

    예외 처리를 하지 않으면 컴파일 오류가 발생하므로 꼭 처리해야하는 검사형 예외

2. **실행예외 : RuntimeException**

    RuntimeException의 자식클래스를 모두 포함

    Unchecked Exception이다. 

    try-catch문으로 예외 처리를 직접 하기 보다는 예외가 발생하지 않도록 주의해야 한다. 

    1. **NullPointException**

        객체 참조가 없는 상태 일때 발생

        null값을 갖는 참조변수로 객체 접근 연사자인 도트(.)를 사용했을 떄 발생.

        객체가 없는 상태에서 객체를 사용하려 했으니 당연

    2. **NumberFormatException**

        문자열로 되어 있는 데이터를 숫자로 변경 하는 방법으로 Integer.parseInt(String s) Double.parseDouble(String s)메소드랄 주로 상요하는데 변경하려는 문자 값이 숫자가 아닌 글자로 이루어져 있으면 변환할 수 없기에 해당 Exception을 발생시킨다.

    3. **ArrayindexOutOfBoundsException**

        배열에서 인덱스 범위를 초과하여 사용할 때 발생한다. 

         

    4. **ClassCastException**

        허용되지 앟는데 억지로 타입 변환을 시도할 경우 발생

3. **예외처리 코드 (try-catch- finally)**

    예외별로 다중 catch문을 구성할 수 있지만 catch 블록이 여러개 일지라도 먼저 발생한 예외에 대하여 단 하나의 catch블로만 실행됩니다. 그 이유는 예외 1이 발생했다면, 그 밑의 에외 2 발생코드는 생행하지 않고 1을 catch하는곳으로 이동하기 때문이다.

    그렇기에 상위 예외 클래스를 처리하는 코드는 catch문맨 마지막에 작ㅎ다. 

    > **기본 catch문**

    ```java
    try{
    	예외 발생 가능서잉 있는 코드

    	~~~~~~~~ ;  < 1.예외 발생 시 
    	~~~~~~~~;   < 2. 여기서 실행을 멈추고

    }catch(예외클래스 e) {

    	예외처리; < 3.catch문에서 예외처리

    } finally {

    	무슨 일이 있어도 항상 실행
    }
    ```

    > **다중 catch 문**

    ```java
    try{

    }catch(예외1 을 잡는 곳) {

    }catch(예외2 를 잡는 곳) {

    }
    ```

4. **예외 떠넘기기** 

    내메소드 내부에서 예외가 발생할 수 있는 코드를 작성할 때 try-catch쁠록으로 예외 처리하는 것이 기본이지만, 경우에 따라서 메소드를 호출한 곳으로 예외처리를 떠넘길 수 도 있다. 

    이 때 사용하는 키워드가 throws 이다.

    throws 키워드는 메소드 선언부 맨 끝에 작성하며, 메소드에서 try-catch를 통해 처리하지 않는 예외를 호출한 곳으로 떠넘기는 역할을 한다.  throws뒤에는 떠넘길 에외 클래스를 쉼표로 여러개 구분해서 나열할 수 있다. 

    발생할 수 있는 예외별로 throws 뒤에 나열하는 것이 보통이나, Exception만 작성해서 모든 예외를 간단히 떠넘길 수 도 있다.

    ```java
    public void method1(){

    try{
    	method2();

    } catch { ClassNotFountException e1){

    System.out.println("클래스가 존재하지 않는다");

    }
    public void method2() throw ClassNotFoundException{
    	Class clas = Class.forName("java.lang.Stirng22");
    }
    ```

    만약 method1도 예외를 떠넘긴다면 어떻게 될까? 만일 method1을 main 메소드에서 호출 했다면, main메소드에서 try-catch를 통해 예외처리를 해야할 것이다. 그런데 main()메소드에서도 예외를 떠넘길 수 있다. 그러 면 그 예외는 JVM이 최종적으로 처리를 하게 되지만 권장하지는 않는다. 

5. **사용자정의 에외와 예외발생** 

    사용자 정의 예외 클래스란 개발자가 직접 정의하여 만드는 예외를 말한다. 일반 예외(checked Exception)나 실행예외(RuntiomeException, UnCheckedException) 중 하나로 만들 수 있다. 일반예외는 Exception을 상속받고, 실행예외는 RuntimeException을 상속 받으면 된다. 

    ```java
    public class AnyCreaeteException extends Exception{

    	public AnyCreateException() {};
    	public AnyCreateException(String message) {
    		super(message);
    // 사용자 정의 예외 생성, 
    // 생성자는 보통 가지 선언하는데 
    // 두번째의 경우 예외 메시지를 전달하기 위해 String 타입의 매개벼ㄴ수를 갖는다. 

    	}
    }

    public class Accont{
    	int balance = 100;
    		public void withdraw(int money) throws AnyCreateException{
    			if(balance < money) {
    				throw new AnyCreateException("Exception 발생");

    // AnyCreateException의 두번 째 생성자를 선택하였다.
    // 모든 예외는 Exception이 가지고 있는 메소드를 호출할 수 있다. 
    // 그 중 getMEssage메소드가 이 예외 메시지를 리턴한다. 

    public static void main(String[] args){
    Account account = new Account();
    try{
    account.withdraw(30000);
    }
    catch(AnyCreateException e){
    String message =e.getMessage();

    System.out.println(message);
    e.printStackTrace();
    }

    ```

# Spring 에서 예외처리 하기

Spring MVC에서 @ControllerAdvice를사용해서 controller에서 발생하는 예외를 일괄처리하는 방법을 알아보자 

### 1. @ControllerAdvice클래스 생성

@ControllerAdvice는 호출되는 메소드에서 발생된 Exception을 모두 처리하는 역할을 한다

해당 애노테이션을 통해 이 클래스의 객체가 컨트롤러에서 발생하는 Exception을 전문적으로 처리하는 클래스라는 것을 명시한다.

생성방법으로는 

1. 클래스에 @ControllerAdvice 애노테이션 처리 
2. 각 메소드에 @ExceptionHandler를 이용해서 적절한 타입의 Exception처리

```java
@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {

    @ExceptionHandler(Exception.class )
    public String common(Exception e,Model model) {

        log.info(e.toString());

        model.addAttribute("exception", e);
        
        return "/error/error_common";
    }
}
```

### 2. error_common.html 생성

```java
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<h1>  커스텀 에러페이지 입니다.</h1>
    <h4 th:text="${exception.getMessage()}"></h4>

    <ul th:each="est : ${exception.getStackTrace()}">
        <li th:text="${est.toString()}"></li>
    </ul>

</body>
</html>
```

### 3.  @ControllerAdvice 동작 확인

```java
@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/mypage")
public class UserPageController {

    private final BoardMemberService boardMemberService;

    @GetMapping("/select")
    public String selectAllMember(Criteria cri, Model model, Authentication auth) throws Exception {
        List<MemberDTO> memberDTOS = new ArrayList<>();

        memberDTOS = boardMemberService.selectMember(auth.getName());

        model.addAttribute("result", memberDTOS);
        model.addAttribute("pageMaker", new PageDTO(cri, boardMemberService.getTotal()));

// 일부러 에러냄
        double e1 = 1 / 0;

        return "memberList/memberList";
   
    }
```

### 4. 실제 프로젝트 적용

Spring MVC의 Controller에서 Exception을 처리하는 방식은 3가지 방법이 있다.

이중 가장 범용적으로 사용할 수 있는 방법은 @ControllerAdvice애너테이션을 이용한 방법인데 공통의 Exception처리 전용 객체를 사용하는 방식이다. 

- @ExceptionHandler 애너테이션을 이용한 처리
- @ControllerAdvice 애너테이션을 이용한 처리
- @ResponseStatus 애너테이션을 이용한 http상태 코드 처리

  실제로 적용 해보니 잘 적용이 되었다. spring security 및 기타 예외 처리들 적용이 잘 되는 것을 볼수 있었다.

하지만 더 공부를 해보니 이 예외 처리를 하는 것에도 여러가지 방법이 있었는데 그 방법들에 대해서는 더 공부가 필요할 것 같다. 

![Untitled 1](https://user-images.githubusercontent.com/63430211/119994749-da9da280-c007-11eb-911e-ddcd0eb32eed.png)

실제 적용 하여 얻은 커스텀 페이지