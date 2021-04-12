### 설명

> 영어 알파벳과 특수문자로 구성된 문자열이 주어지면 영어 알파벳만 뒤집고,  
> 특수문자는 자기 자리에 그대로 있는 문자열을 만들어 출력하는 프로그램을 작성하세요.

### 입력

> 첫 줄에 길이가 100을 넘지 않는 문자열이 주어집니다.

### 출력

> 첫 줄에 알파벳만 뒤집힌 문자열을 출력합니다.

### 풀이

예를 들어 "a ! s @ d # f % A ^ V @ J " 이러한 문자가 주어질 경우

1.  시작 index와 끝 index가 특수문자인지 영어인지 구분 필요
2.  영어일 경우 뒤집기
3.  패턴이있는 로직이므로 while문으로 반복

#### 핵심 코드

| 문법 | 설명 | 반환값 |
| --- | --- | --- |
| Character.is Alpgabetic(대상) | 전달되는 문자가 영어인지 구분한다 | 반환타입은 boolean! |

> key 포인트
> 
> ```
>              if( start == 특수문자){
>                  start ++;
>              } else if (done == 특수문자) {
>                  done--;
>              }else {
>                  둘다 특수 문자가 아니면 알파벳!
>                      그러니까 뒤집기 구현
>              }
> ```

---

```

public class Day5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Day5 day5 = new Day5();
        String in = scan.next();

        char[] aa = in.toCharArray();

            int start = 0, done = aa.length - 1;

            while (start < done) {
                if (!Character.isAlphabetic(aa[start])) start++;
                else if(!Character.isAlphabetic(aa[done])) done--;
                else {
                    char temp = aa[start];
                    aa[start] = aa[done];
                    aa[done] = temp;
                    start++;
                    done--;
                }
            }

        String temp = String.valueOf(aa);
        System.out.println(temp);
    }
}

```