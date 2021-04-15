### 설명

> 소문자로 된 한개의 문자열이 입력되면 중복된 문자를 제거하고 출력하는 프로그램을 작성하세요.
> 중복이 제거된 문자열의 각 문자는 원래 문자열의 순서를 유지합니다.

#

### 입력
> 첫 줄에 문자열이 입력됩니다. 문자열의 길이는 100을 넘지 않는다.

#

### 출력
> 첫 줄에 중복문자가 제거된 문자열을 출력합니다.

***

### 풀이 

> 핵심 코드 


|문법 | 설명|
|---|---|
|확인할 문자열.contains(String 찾을 문자) | 검사할 문자열 안에 찾을 문자가 포함되었는지 boolean으로 반환|

***

```java

        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i) + " " + i + " " + str.indexOf(str.charAt(i)));


```

> **핵심 풀이 내용**

|순서|index 번호| 해당 문자의 첫번 째 위치| 설명|
|:---:|:---:|:---:|:---:|
|a|0|0|  index번호와 처음 "a" 가 등장하는 위치  
|a|1|0|  index위치는 1인데 첫 등장은 0 이다. == > 중복이 있다
|b|2|2|
|c|3|3|
|d|4|4|
|d|5|4|
               


***

> **풀이1. contains 를 이용한 방법 **
```java

public class Day6 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        
        char[] inputs = input.toCharArray();    // 입력 받은 문자를 char 배열로 저장
        String asnwer = "";             // 중복제거 된 문자 담을 그릇

        for (char x : inputs) {       // char타입으로 저장 된 값들을 하나씩 꺼내어 대입
            if (!(asnwer.contains(String.valueOf(x)))) {      // 저장 된 그릇(answer)에 하나씩 들어오는 x의 값의 중복여부 확인
                asnwer += String.valueOf(x);        // 중복이 아닐 시 그릇에 하나씩 담는다.
            }
        }
        System.out.println("result = " + asnwer);

    }
 }

```
***
> **풀이 2. indexOf를 이용한 방법**

```java

public class Day6 {

    public String sol(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i) + " " + i + " " + str.indexOf(str.charAt(i)));

            if (str.indexOf(str.charAt(i)) == i) {
                // i 번째 문자의 index와 i가 같을 경우에만  문자 누적!
                result += str.charAt(i);
            }
                    }
        return result;
    }

    public static void main(String[] args) {
        Day6 t = new Day6();
        Scanner scan = new Scanner(System.in);
        String input = scan.next();

        System.out.println(t.sol(input));

    }

}


```
