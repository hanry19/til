### **설명**

> 1) 피보나키 수열을 출력한다. 피보나치 수열이란 앞의 2개의 수를 합하여 다음 숫자가 되는 수열이다.  
> 2) 입력은 피보나치 수열의 총 항의 수 이다. 만약 7이 입력되면 1 1 2 3 5 8 13을 출력하면 된다.

### **입력**

> 첫 줄에 총 항수 N(3<=N<=45)이 입력된다.

### **출력**

> 첫 줄에 피보나치 수열을 출력합니다.

### **풀이**

이 문제의 핵심은 규칙을 찾아내는 것

```
현재 = 이전 + 이전의 이전
x(n) = x(n-1) + x(n-2)
```

> **방법 1 : 배열로 풀기**

```

      public static void main(String[] args) {
         Array4 array4 = new Array4();

         Scanner scanner = new Scanner(System.in);

         int n = scanner.nextInt();
         int[] arr = new int[n];

         arr[0] = 1;
         arr[1] = 1;

         for (int i = 2; i < n; i++) {
             arr[i] = arr[i - 1] + arr[i - 2];
         }
         System.out.println(Arrays.toString(arr));

         }

```

> **방법 2 : 배열없이 풀기**

```

    public static void main(String[] args) {
         Array4 array4 = new Array4();

         Scanner scanner = new Scanner(System.in);

         int n = scanner.nextInt();
         int[] arr = new int[n];

         int a = 1, b=1, c;
         System.out.print(a + " " + b + " ");
         for (int i = 2; i < n; i++) {
             c=a+b;
             System.out.print(c + " ");
             a = b;
             b = c;
         }

```

> **결과**

```
    10
    [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
    1 1 2 3 5 8 13 21 34 55 
    Process finished with exit code 


```