### 자바 알고리즘 문제 및 풀이



#### 문제



> 한 개의 문자열을 입력받고, 특정 문자를 입력받아 해당 특정문자가 입력받은 문자열에 몇 개 존재하는지 알아내는 프로그램을 작성하세요.
> 대소문자를 구분하지 않습니다.문자열의 길이는 100을 넘지 않습니다.


#### 풀이


> 사용된 핵심 문법 

##

문법                        | 설명                                    |
|---|---|
toUpperCase()               | 문자열을 모두 대문자로 변환             |
Character.toUpperCase(대상) | char 형식을 대문자로 변환               |
toChar()                    | String을 char 형태로 변환               |
toCharArray()               | String을 char[ ] 배열로 변환            |
CharAt()                    | String중 index위치에 대당되는 문자 추출  |




```java
 public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("문자열 입력하셈");
        String input = scan.next(); // 문자열 입력

        System.out.println("찾을 문자 입력하셈");
        char find = scan.next().charAt(0);  // 찾을 문자 입력

        int count=0;
        //대소문자 구분 X 를 위해 str , t를 대문자로 바꿔버린다
        input = input.toUpperCase();
        find = Character.toUpperCase(find);
        System.out.println(input +" "+ find);    // 대문자로 변한걸 확인할 수 있다.
        
        
        // 기본 for문 사용
        
        
         for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i)==find) 
            count++;
        // index로 문자를 접근하는게 charAt이고 이걸 통해 input 문자열을 한글자씩 확인
        // 만약 같을 경우 ture 이고 count +1
        }
        
        
        // forEach문 
        
        
       // forEach 문에서는 string은 안됨.
       // 배열 혹은 iterator를 제공하는 collection framework (ex : Arraylist , list)등  가능
       // 그렇기에 문자를 하나하나 나눠서 배열로 만들어야한다. ==> toCharArray()
       // 그리고 한글자 씩 비교해가며 counr +1

        for (char x : input.toCharArray()) {
            if(x == find) count++;
        }
```
