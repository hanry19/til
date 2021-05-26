# [남궁성] 람다식(Lamda Expression)

JDK1.8 버전 이후에는 함수형 언어를 추가 하였다. 

대부분이 OOP + fp를  가지고 있다. 

- **함수(메서드)를 간단한 식 으로 표현하는 방법**

![Untitled](https://user-images.githubusercontent.com/63430211/119672651-bca03880-be75-11eb-9c6c-587fbe327a7d.png)


    - **익명 함수(이름이 없는 함수,  anonymous function)**

        반환타입과 이름을 지운다 그리고 → 화살표가 들어간다.  이름이 없어지니까 익명 함수

![Untitled 1](https://user-images.githubusercontent.com/63430211/119672693-c3c74680-be75-11eb-97e3-3e3fcc09842e.png))

    - **함수와 메서드 차이**

        근본적으로 동일, 함수는 일반적 용어, **메서드는 객체지향 개념 용어** 

        **함수는 클래스에 독립적**, ***메서드는 클래스에 종속적***

        자바는 모든 메서드는 클래스 안에 있어야 하는데 이건 메서드 

        클래스 밖에 있는건 함수!

- **람다식 작성하기**
    1. 메서드의 이름과 반환 타입을 제거하고, " - > " 를 블록{ } 앞에 추가 한다.

      ![Untitled 2](https://user-images.githubusercontent.com/63430211/119672728-c9bd2780-be75-11eb-8219-979cd6044c25.png)

        **람다식 = 메서드 함수  =⇒ 간단히 표현하기 위한 것.**

    2. 반환값이 있는 경우, 식이나  값만 적고 return 문 생략 가능 (끝에 세미클론 안붙임)

     ![Untitled 3](https://user-images.githubusercontent.com/63430211/119672764-d0e43580-be75-11eb-91c0-d820c8a86816.png)
    3. **매개변수의 타입이 추론가능하면 생략 가능 (대부분 생략 가능)**

     ![Untitled 4](https://user-images.githubusercontent.com/63430211/119672824-dd688e00-be75-11eb-9ed6-5f0357cbef09.png)

    - 람다식 작성하기 - 주의사항
        1. 매개변수가 하나인 경우, 괄호( ) 생략 가능( 타입이 없을 때만)

            괄호를 생략할 수 없을 땐 생략 불가!

        ![Untitled 5](https://user-images.githubusercontent.com/63430211/119672857-e2c5d880-be75-11eb-9ba4-15fae02cc556.png)
        2. 블록 안의 문장이 하나 뿐 일 때, 괄호{ } 생략 가능(끝에 세미클론) 안붙임)

          ![Untitled 6](https://user-images.githubusercontent.com/63430211/119672876-e6f1f600-be75-11eb-92af-0742a7ff3f29.png)

        - 람다식은 익명함수? ㄴㄴ 익명 객체!
            - 람다식은 익명 함수가 아니라 **익명 객체**이다.!!
![Untitled 7](https://user-images.githubusercontent.com/63430211/119673071-0c7eff80-be76-11eb-9f48-2518520084f6.png)


            - 람다식(익명 객체)을 다루기 위한 ***참조변수가 필요***. 참조변수의 타입은??

                =⇒ 함수형 인터페이스가 필요 하다

- **함수형 인터페이스**
    - 함수형 인터페이스 : 단 **하나의 추상 메서드만** 선언된 인터페이스

        @functionalInterface 라는 애너테이션 붙이면 좋음

      ![Untitled 8](https://user-images.githubusercontent.com/63430211/119673093-1143b380-be76-11eb-8ef0-705c7ddb7673.png)

        이걸 구현 하면, 

        익명 클래스, 클래스의선언, **객체 생성을 동시에 한다.**

      ![Untitled 9](https://user-images.githubusercontent.com/63430211/119673121-16086780-be76-11eb-8191-4eba22b66940.png)

        ```java
        // 객체 생성
        new 조상이름()  { 멤버 }
        // 조상이름은 클래스 or 인터페이스
        ```

        이전에는 **obj.max(3,5)** 는 에러 였다. 

        그런데 함수형 인터페이스는 max 가 있의까 에러가 안뜬다.

      ![Untitled 10](https://user-images.githubusercontent.com/63430211/119673151-1b65b200-be76-11eb-91dc-2b792688f6da.png)

    - 함수형 인터페이스 타입의 참존수로 람다식을 참조할 수 있다.
    (단, 함수형 인터페이스의 메서드와 람다식의 **매개변수 개수와 반환타입이 일치**해야 함)

        람다식도 함수형 참고변수로 다룰 수 있다. 

        ![Untitled 11](https://user-images.githubusercontent.com/63430211/119673206-24ef1a00-be76-11eb-8cf1-24936a8f94d8.png)

    ```java
    public static void main(String[] args) {
    /*        Object obj = (a, b) -> a > b ? a : b;  //람다식은 함수형 인터페이스로 다뤄야 한다. // 에러

            MyFunction2 f = new MyFunction2(){  //해당 객체를 다루기 위한 참조변수
                public int max(int a, int b) {  // 오버라이딩의 조건ㄴ으로 접근제어자는 좁게 못바꾼다.
                    return a > b ? a : b;
                }
            };*/

    //        위 코드가 기니까 줄이기 위해서 람다 식을 쓴다.

            // 람다식을다루기 위한 참조변수의 타입은 함수형 인터페이스로한다.
            // 그리고 함수형 인페의 max가 람다 식과 맞아야 한다.

            // 함수형 인페의 max = a,b를 받는데 둘중에 큰 값을 int로 반환 함
            // 람다 식 역시 a,b를 넣으면 둘 중 하나를 반환함
            // 람다식과 함수형 인페와 매개변수와 결과 타입이 같아야 한다. 다르면 사용 못함.

            // 결국 람다식을 사용하려면 이름이있어야 한다. 근데 이름을 다 지웠다.
            // 메서드를 간단하기 위해 이름하고다 지웟는데, 이름이 있어야 한다.
            // 그래서 람다식에 이름을 붙혀 준다. ==> max !!
            // 그래서 추상 메서드랑 연결하는거라고 생각하면 된다.
            // 추상메서드를 통해 람다식을 호출한다고 생각하면 됨

            MyFunction2 f = (a, b) -> a > b ? a : b; // 람다식은 익명객체 이기 때문에, 함수형 인터페이스 타입이 필요
            int value = f.max(3, 5);
            System.out.println("value = " + value);

            // 그럼 max를 쓰기 위해선
    /*        int value = obj.max(3, 5); // 에러  참조변수 obj에는  max를 가지고 있지 않다.
                                        // object 리모콘에는 max라는 메서드가 없다.
                                       // 그래서 호출을 위해 함수형 인터페이스가 필요하다.*/
        }
    }

    @FunctionalInterface // 함수형 인터페이스는 단 하나의 추상 메서드만 가져야한다.
    interface MyFunction2 {
        public abstract int max(int a, int b);
    //    public abstract int max2(int a, int b);
    }
    ```

    - 익명 객체를 람다식으로 대체

![Untitled 12](https://user-images.githubusercontent.com/63430211/119673280-33d5cc80-be76-11eb-8b94-a0599bdcd1c2.png)

     ![Untitled 13](https://user-images.githubusercontent.com/63430211/119673191-23255680-be76-11eb-924c-8ee62f26264f.png)

        이걸 람다식으로 바꿈

      ![Untitled 14](https://user-images.githubusercontent.com/63430211/119673200-23bded00-be76-11eb-8b4b-54935e67280d.png)

    - 함수형 인터페이스 타입의매개변수

        ```java
        void aMethod (MyFunction f) {  //매개변수로 MyFunction의 함수형 인페로 받는다
        																// 이 뜻은 매개변수를 람다식으로 받겠다는 것
        		f.myMethod(); // MyFunction 함수형 인페에 선언 되어 있는 myMethod를 호출 하는것
        }                   // 람다식에 이르을 붙여주고 호출 하는 것

        @FunctionalInterface
        interface MyFunction {
        		void myMethod(); }

        // 그럼 aMethod 로 호출 하는 건 아래와 같이 호출 된다. 
        // myMethod 를 출력하는 람다 식을 aMethod(f)에 넣는 것이다.
        // 그럼 그 안에사 호출 하는것 
        Myfunction f  = () -> system.out.println("myMethod()");
        aMethod(f); 

        // 위 두 문장을 하나로 합치면
        aMethod ( () -> system.out.println("myMethod()"));

        ```

    - 함수형 인터페이스 타입의 반환 타입

        반환 타입을 함수형 인터페이스로 지정할 수 있다. == >람다식을 반환하겠다는것

        ```java
        MyMethod myMEthod() {            //MyMethod =  람다식을 반환하겠다는 것
        	MyFunction f = () -> {} ;
        return f;

        // 위 식을 간단하게 적으면 아래와 같다.

        MyFunction myMEthod() {
        return ()->{ };
        }
        ```