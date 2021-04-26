### 설명

> 알파벳 대문자로 이루어진 문자열을 입력받아 같은 문자가 연속으로 반복되는 경우 반복되는  
> 문자 바로 오른쪽에 반복 횟수를 표기하는 방법으로 문자열을 압축하는 프로그램을 작성하시오.  
> 단 반복횟수가 1인 경우 생략합니다.

### 입력

> 첫 줄에 문자열이 주어진다. 문자열의 길이는 100을 넘지 않는다.

### 출력

> 첫 줄에 압축된 문자열을 출력한다.

### 풀이

> 핵심논리

[##_Image|kage@b48UNa/btq3nSZRCeg/yIKuoWyBrUioDW5R9Y9OR0/img.png|alignCenter|data-origin-width="0" data-origin-height="0" data-ke-mobilestyle="widthContent"|||_##]

초기 설정. String으로 구성 된 녀석들을 str.charAt\[i\] 을 통해 i 와 i+1 이 동일 여부를 확인해야한다.  
같을 시 CNT 에 +1을 해준다.  
다를 경우 임의의 String 공간에 str.charAt\[i\] 와 cnt는 넣는다. 이 작업을 반복한다.

```
String temp = ""; 
temp += str.charAt[i]; 
temp 9= cnt;
cnt= 1; // cnt는 다시 1로 초기화 해준다
```

하지만 string의 마지막 문자는 \[i\] 는 i+1 이 없기에 에러가 발생한다.  
그렇기에 처음에 str += " " ; 입력받은 문자에 공백을 추가해준다.

> 코드

```
public class Day11 {

    public String sol(String str) {
        String solu = "";

        str =str+" ";
        int cnt = 1;
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) cnt++;
           else {
                solu += str.charAt(i);
                if(cnt>1) solu += String.valueOf(cnt); // int==> string    have 2 ways for change to string from int
//                    sol += Integer.toString(cnt); // int==> string
                    cnt = 1; // reset to count 1
            }
        }
        return solu;
    }


    public static void main(String[] args) {
        Day11 day11 = new Day11();
        Scanner scan = new Scanner(System.in);

        String str = scan.next();

        System.out.println(day11.sol(str));

    }
}

```