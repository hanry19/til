### 문제

> 대문자와 소문자가 같이 존재하는 문자열을 입력받아 대문자는 소문자로 소문자는 대문자로 변환하여 출력하는 프로그램을 작성하세요.

### 입력
> 첫 줄에 문자열이 입력된다. 문자열의 길이는 100을 넘지 않습니다.
> 문자열은 영어 알파벳으로만 구성되어 있습니다.

### 출력
> 첫 줄에 대문자는 소문자로, 소문자는 대문자로 변환된 문자열을 출력합니다.*/



#### 풀이 

이번문제는 별거 없었다! 실무에서 가장 중요한게 collection 부분이라고 하니, 
쉬운 부분은 빨리 해치우자!!

-----------------------

```JAVA

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.next();

            String result = "";
        for (char x : input.toCharArray()) {            // 입력값을 char형식의 배열로 바꿔서 char x에 대입
            if(Character.isUpperCase(x)==true){         // x값이 대문자인 경우 소문자로 소문자인 경우 대문자로 바꿈
                result += Character.toLowerCase(x);
//                System.out.print(Character.toLowerCase(x));
            } else {
                result += Character.toUpperCase(x);
//                System.out.print(Character.toUpperCase(x));
            }
        }System.out.println("result = " + result);
    }
    
```
