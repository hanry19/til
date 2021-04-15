### 설명

> 앞에서 읽을 때나 뒤에서 읽을 때나 같은 문자열을 회문 문자열이라고 합니다.  
> 문자열이 입력되면 해당 문자열이 회문 문자열이면 "YES", 회문 문자열이 아니면 “NO"를 출력하는 프로그램을 작성하세요.  
> 단 회문을 검사할 때 대소문자를 구분하지 않습니다.

### 입력

> 첫 줄에 길이 100을 넘지 않는 공백이 없는 문자열이 주어집니다.

### 출력

> 첫 번째 줄에 회문 문자열인지의 결과를 YES 또는 NO로 출력합니다.

### 풀이

> 핵심 문법

| 문법 | 설명 |
| --- | --- |
| StringBuilder(대상 문자열).**reverse()**.toString(); | reverse() 는 문자를 뒤집어 준다. 그리고 StringBuilder의 반환값은 StringBuilder이기 때문에 toString으로 String 으로 바꾼다. |

> 핵심 논리

-   문자 길이를 반으로 나눠, 그 값 만큼 반복문을 돌린다. 반복문 안에는 String의 첫번 째 글자와 마지막 글자를 비교하는 수식이 들어간다. 만약 첫글자와 마지막 글자가 틀릴 시 "NO" 반환

> 풀이 1.

```
    public String sol(String str) {
        String answer = "NO";
        String tmp = new StringBuilder(str).reverse().toString();

        if (str.equals(tmp)) return "YES";          // 단순 비교
        if (str.equalsIgnoreCase(tmp)) return "YES"; // 대소문자 무시

        return answer;
    }

    public static void main(String[] args) {
        Day7 t = new Day7();
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        input = input.toUpperCase();

        System.out.println(t.sol(input));
    }

```

> 풀이 2.

```
    public String sol(String str) {

        String answer = "YES";

        // length /2 를 했을 때 길이가 짝수인 경우 딱 맞게 떨어지고 홀수의 경우 가운데 숫자는 그냥 그대로 있는다.

        int len = str.length();
        for (int i = 0; i < len/2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) return "no";
            // i번째 글자가 총 길이 - i -1 (끝자리) 글자랑 일치하지 않으면 no를 반환
        }

        return answer;
    }

    public static void main(String[] args) {
        Day7 t = new Day7();
        Scanner scan = new Scanner(System.in);

        String input = scan.next();
        input = input.toUpperCase();

        System.out.println(t.sol(input));
    }

```