### 설명

> 앞에서 읽을 때나 뒤에서 읽을 때나 같은 문자열을 팰린드롬이라고 합니다.  
> 문자열이 입력되면 해당 문자열이 팰린드롬이면 "YES", 아니면 “NO"를 출력하는 프로그램을 작성하세요.  
> 단 회문을 검사할 때 알파벳만 가지고 회문을 검사하며, 대소문자를 구분하지 않습니다.  
> 알파벳 이외의 문자들의 무시합니다.

### 입력

> 첫 줄에 길이 100을 넘지 않는 공백이 없는 문자열이 주어집니다.

### 출력

> 첫 번째 줄에 팰린드롬인지의 결과를 YES 또는 NO로 출력합니다.

## 풀이

> 사용 문법

정규표현식(Regular Expressions : Regex) - 문자열의 패턴을 찾을 때 사용

```
String replace(찾는 문자열, 바꿀 문자열) // char 타입 입력

String replaceAll(정규식/기존문자 , 바꿀문자) // String 타입 입력

```

| 정규식 | 의미 |
| --- | --- |
| \[^abc\] | abd를 제외한 문자 1개 |
| \[^a-z\] | a~z |

> 풀이

```
  public String sol(String s) {
        String answer = "no";
        s = s.toUpperCase().replaceAll("[^A-Z]", "");
                                    //대문자 A~Z가 아니면, 아닌것들은 "" 빈문자로 만들어라

        System.out.println("s = " + s);  // 대문자만 출력되는걸 확인할 수 있다.

        String temp= new StringBuilder(s).reverse().toString();
        // temp = s의 문자열을 뒤집은 것을 String화 한다.

        if(s.equals(temp)) answer = "yes";
        // 만약 입력받은 문자열과 temp(뒤집어진 문자열)이 같으면 "yes"


        return answer;
    }


    public static void main(String[] args) {
        Day8 t = new Day8();
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();
        input = input.toUpperCase();

        System.out.println(t.sol(input));
    }
```