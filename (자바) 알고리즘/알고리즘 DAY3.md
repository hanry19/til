### 설명

N개의 단어가 주어지면 각 단어를 뒤집어 출력하는 프로그램을 작성하세요.

### 입력

첫 줄에 자연수 N(3<=N<=20)이 주어집니다.  
두 번째 줄부터 N개의 단어가 각 줄에 하나씩 주어집니다. 단어는 영어 알파벳으로만 구성되어 있습니다.

### 출력

N개의 단어를 입력된 순서대로 한 줄에 하나씩 뒤집어서 출력합니다.

### 풀이

> 주요 문법

| 문법 | 설명 | 예시 |
| --- | --- | --- |
| ArrayList< String > | 데이터순서가 있다. 중복허용 | 나중에 다시 따로 다룰예정 |
| StringBuilder | 기본적으로 string은 final 객체이기 때문에 추가, 수정 등의 지원이안된다. |   |
| 하지만 StringBuilder는 문자열 길이만큼 버퍼를 생성하여 그안에 저장하기 때문에 수정이 용이하다. | new StringBuilder(문자열) |   |
| String.valueOf(값) | String으로 호출한 static 객체이며 null이 들어가도 "null"이라는 문자열로 반환을 하는 특징이 있다. 입력된 값들을 String형태로 바꾸는 역할 | String.valueOf(값) |

```

public class Day3 {


    /*설명

    N개의 단어가 주어지면 각 단어를 뒤집어 출력하는 프로그램을 작성하세요.

    입력
    첫 줄에 자연수 N(3<=N<=20)이 주어집니다.
    두 번째 줄부터 N개의 단어가 각 줄에 하나씩 주어집니다. 단어는 영어 알파벳으로만 구성되어 있습니다.

    출력
    N개의 단어를 입력된 순서대로 한 줄에 하나씩 뒤집어서 출력합니다.*/

    public ArrayList<String> solution(int n, String[] str) {
        ArrayList<String> answer = new ArrayList<>();



// 방법 1
--------------------------------------------------------------------------------------------------------------------
       for (String x: str) {
            String tmp = new StringBuilder(x).reverse().toString();
              //StringBuilder라는 생성자에 단어 x가 들어가 객체를 만든다.
              // 여기서 reverse로 들어온 단어x 를 뒤집고 toString으로 String화 시킨다.

              // string은 스트링끼리 더하거나 replace하면 새로운 객체가 생성된다. 왜냐면 string은 불변이기 때문
              // 그래서 계속 객체가 만들어지지만, StringBuilder는 더하고 수정하고 추가 할 때 객체 하나로 계속 진행
              // 메모리 낭비도 없고 무겁지도 않다. 그래서 STRING 연산이 많아지면 StringBuilder 쓰기도 한다.

            answer.add(tmp);
        }



//방법 2. 
--------------------------------------------------------------------------------------------------------------------
        for (String x : str) {                  // x는 string 이니까 하나하나 이동은 불가능 그래서 문자 배열로 만들어줘야함
            char[] s = x.toCharArray();     //문자 배열화
            int lt = 0, rt = x.length() - 1;

            while (lt < rt) {
                char temp = s[lt];              // lt(++)       rt(--)  temp = lt
                s[lt] = s[rt];                  //  S  T  U  D  Y
                s[rt] = temp;                  //        end
                lt++;
                rt--;
            }
            String tmp = String.valueOf(s); // valueOf(s) ==> s를 스트링 화 시킨다
            // valueOf는 스트링으로 호출할 static 객체이다.
            answer.add(tmp);
        }

        return answer;
    }


실행 
--------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        Day3 t = new Day3();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();     // 몇개가 들어오는지 측정한 것

        String[] str = new String[n]; // n개의 단어를 받은거고

        for (int i = 0; i < n; i++) {
            str[i] = scan.next();       // 스트링 배열에 단어를 하나씩 넣는것
        }

        for (String x : t.solution(n, str)) {   // 스트링 배열을 넣엇다
            //스트링을 원소로 갖는 arrayList에 뒤집어진 단어를 넣어서 어레이 리스트를 리턴 받는 것
            System.out.println(x);
        }

    }
}

```