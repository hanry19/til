### 설명

> N개의 자연수가 입력되면 각 자연수를 뒤집은 후 그 뒤집은 수가 소수이면 그 소수를 출력하는 프로그램을 작성하세요.  
> 예를 들어 32를 뒤집으면 23이고, 23은 소수이다. 그러면 23을 출력한다. 단 910를 뒤집으면 19로 숫자화 해야 한다.  
> 첫 자리부터의 연속된 0은 무시한다.

### 입력

> 첫 줄에 자연수의 개수 N(3<=N<=100)이 주어지고, 그 다음 줄에 N개의 자연수가 주어진다.  
> 각 자연수의 크기는 100,000를 넘지 않는다.

### 출력

> 첫 줄에 뒤집은 소수를 출력합니다. 출력순서는 입력된 순서대로 출력합니다.

```
예시 입력
    9  
    32 55 62 20 250 370 200 30 100  

예시 출력 

    23 2 73 2 3
```

### 풀이

**핵심은 숫자를 뒤집는 로직**

```
    임의의 숫자 123 가 있다.

    이'123' 을 뒤집기는 로직은 아래와 같다.
    wheile문을 이용하여 값이 '0'이 나올 때 까지 반복한다 

     123(대상)을 10을 나누면 소수점이 이동함으로 최종적으로 나누는 값이 0.xx가 되면 반복을 중단한다.
```

```
     while (temp > 0) {
          int t = temp % 10;  //1230을 10으로 나눈 나머지 = 0 혹은 1의 자리 숫자이다. ==> 첫번쨰짜리
          res = res * 10 + t;   // 0*10+0 = 0  , 0*10+3 = 3.....
          temp = temp / 10;       // 10을 나누면 123
      }


      /*
         res = 123
        temp= 12 => res=2 = > res *10+t => 30+2+ => 32
         temp = 1 => res= 1 => res* 10+t => 32*10+1 => 321
    */


```

-   풀이 코드
    
    ```java
    
      public boolean isPrime(int num) {
          if(num ==1) return false;
          for (int i = 2; i < num; i++) {
              if(num% i==0) return false;
          }
          return true;
      }
    
      public ArrayList<Integer> sol(int n , int[] arr) {
          ArrayList<Integer> ans = new ArrayList<>();
    
          for (int i = 0; i < n; i++) {
              int temp = arr[i];
              int res = 0;
              while (temp > 0) {
                  int t = temp % 10;  //1230을 10으로 나눈 나머지 = 0 혹은 1의 자리 숫자이다. ==> 첫번쨰짜리
                  res = res * 10 + t;   // 0*10+0 = 0  , 0*10+3 = 3.....
                  temp = temp / 10;       // 10을 나누면 123
              }
              if(isPrime(res)) ans.add(res);
          }
          return ans;
    \
      }
    
    
    ```
    

```
public static void main(String[] args) {
    Array6 array5 = new Array6();
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();

    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
        arr[i] = scanner.nextInt();
    }
    for (Integer x : array5.sol(n, arr)) {
        System.out.print(x + " ");

    }

}
```