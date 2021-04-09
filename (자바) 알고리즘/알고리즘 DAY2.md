### 설명

> 한 개의 문장이 주어지면 그 문장 속에서 가장 긴 단어를 출력하는 프로그램을 작성하세요.  
> 문장속의 각 단어는 공백으로 구분됩니다.

### 입력

> 첫 줄에 길이가 100을 넘지 않는 한 개의 문장이 주어집니다. 문장은 영어 알파벳으로만 구성되어 있습니다.

### 출력

> 첫 줄에 가장 긴 단어를 출력한다. 가장 길이가 긴 단어가 여러개일 경우 문장속에서 가장 앞쪽에 위치한

### 풀이

> 핵심 문법

| **문법** | **설명** | **예시** |
| --- | --- | --- |
| indexOf("타겟") | string 속 타겟의 위치를 찾아서 반환한다. | str.indexOf("#"); |
| substring(어디서부터~ , 여기 이전까지) | string 문자를 잘라낸다. | str.substring(0,4) ==> 0부터 4 이전까지 즉 3까지 값을 얻음 |
| split("타겟") | 문장 속 타겟을 기준으로 문장을 나눈다. | str.split("#"); |

> 최대값을 찾는 알고리즘을 이용한 방법 (indexOf() , substring() 사용)

```

     String input = scan.nextLine();         // 아무 문장이나 입력을 할 시 

     String result = "";

        int m = Integer.MIN_VALUE, pos; // 인트가 출력할 수 있는 가장 작은 값으로 출력 됨

        while((pos = input.indexOf(' '))!=-1){     

            // 띄워쓰기 위치를 반환 해줌 못하면 -1을 반환
            // I am your father. 띄워쓰기 위치를 indexOf는 번호로 반환한다. ex) i = 0 , ' '= 1, a =2 ...
            // 그럼 최초에 1을 반환하게 된다. 그리고 substring으로 잘라내면 되겟지?

            String tmp = input.substring(0, pos); // 0 부터 시작해서 pos전까지 잘라내는 것

             // temp 가 그럼 I 가 됨!

            int len = tmp.length();  // 임의에 값 len 은 tmp의 길이가 되어 m(integer의 제일 작은 값) 과 비교한다. 

            if(len>m){          // 여기서 " >= " 로 하면  뒤에서 같은 단어가 발견되면 뒤에껄 가르키게 된다
                m = len;        //그래서 " > " 로 해야한다.
                result = tmp;
            }
            input = input.substring(pos + 1);

            // input = "I am your father" 이 계속 반복하게 되면 indexof는 공백을 I와 am 사이의 공백만 찾음
            // 그러니까 pos(첫번째 공백위치)에 +1을 해줘서 계속 진행하게 만든다.
            // 하지만 이 때 your 다음의 공백에서는 더 이상 공백이 없으니까 father을 인식하지 못하고 -1을 반환한다.
        }

```

> split을 이용하는 문장을 구분하는 방법

```

        String input = scan.nextLine();         // 아무 문장이나 입력을 할 시 
         String result = "";

        int m = Integer.MIN_VALUE; // 인트가 출력할 수 잇는 가장 작은 값으로 출력 됨

        // split() 특정 문자로 구분해서 배열로 저장하는것
        String[] s = input.split(" ");
        for(String x : s){
            int len =  x.length(); //각각의 단어길이가 len에 저장된다.
            if(len > m){  // 최대값 구하는 알고리즘
                m=len;
                result = x;
            }
        } System.out.println("result = " + result);


```
