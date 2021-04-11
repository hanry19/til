### 설명

> N개의 단어가 주어지면 각 단어를 뒤집어 출력하는 프로그램을 작성하세요.

### 입력

> 첫 줄에 자연수 N(3<=N<=20)이 주어집니다. 두 번째 줄부터 N개의 단어가 각 줄에 하나씩 주어집니다.  
> 단어는 영어 알파벳으로만 구성되어 있습니다.

### 출력

> N개의 단어를 입력된 순서대로 한 줄에 하나씩 뒤집어서 출력합니다.

---

### 풀이

```
public  class Day4 {


    static Scanner  scan = new Scanner(System.in);

    public ArrayList<String> sol (String[] str) {
        ArrayList<String> list = new ArrayList<>();
        // array list 객체 생성
        // 그리거 이 해다아 ArrayList는 generics가 String 이므로 String 만 받을 수 있다.

        for (String x : str) {  // main에서 전달 한 i, love, you string 배열을
                                // 한 단어씩 x에 넣으며 toCharArray() 로 한 단어 씩 배열로 저장!
                                // 저장 한 단어의 첫 단어와 끝 단어를 바꿔야 하니까
                                // 첫 단어 위치와 끝 단어 위치 index를 증가/감소 시켜 자리를 반복한다.

            char[] a =x.toCharArray();
            int start = 0,  done = x.length()-1; // 단어의 처음과 끝 자리 index

            while (start < done) { // 시작 index와 끝 index의 위치를 바꿔준다.
                                   // 시작 index가 끝 index보다 커질 떄 까지
                char temp = a[start];
                a[start] = a[done];
                a[done] = temp;
                ++start;
                --done;
            }                     // while 문을 돌며 문자를 뒤집었다. 뒤집은 문자는 현재 char 형식이다.
                                  //  char ==> String 화 화기 위해서 아래와 같이 한다.
            String temp = String.valueOf(a); // valueOf  는 char[]배열도 충분히 받을 수 있다!
            list.add(temp);         // 그리고 전달한 첫번 째 단어를 arrayList에 추가 한다!!
        }
        // 배열에 있던 모든 단어들을 전부 다 뒤집어 ArrayList에 저장했다면, return을 해서 다시 돌려보낸다.
        return list;
    }



    public static void main(String[] args) {
        Day4 t = new Day4();
        int n = scan.nextInt();

        String[] nl = new String[n];

        // String 배열에 n개의 단어 입력
        for (int i = 0; i < n; i++) {
            nl[i] = scan.next();                // i, love you 세 단어를 넣으면.
        }

        for (String x : t.sol(nl)) {    // 한 단어 씩 sol 메소드에 호출 된다.
            System.out.println(x);
        }

    }

}


```