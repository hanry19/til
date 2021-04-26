### 설명

> N개의 정수를 입력받아, 자신의 바로 앞 수보다 큰 수만 출력하는 프로그램을 작성하세요.  
> (첫 번째 수는 무조건 출력한다)

### 입력

> 첫 줄에 자연수 N(1<=N<=100)이 주어지고, 그 다음 줄에 N개의 정수가 입력된다.

### 출력

> 자신의 바로 앞 수보다 큰 수만 한 줄로 출력한다.

### 풀이

1.  배열의 크기를 동적으로 지정해주고 int 배열을 만든다.
2.  반목분을 통해 int 배열에 값을 넣는다.
3.  한글자 씩 solution 메서드로 넘겨서 (\[x\] > \[x-1\]) 을 비교하여 크면 arrayList에 add한다.
4.  그러고 arrayList를 return 하여 출력한다.

```
public class Array1 {

    public ArrayList<Integer> sol(int n, int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();
                           // 해당 리스트에 넣는거는 출력을위한 녀석들이다.
        ans.add(arr[0]); // 0번은 무조건 나오는거니까 추가한다.

        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) ans.add(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Array1 array1 = new Array1();
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int[] arr = new int[a]; // 입력 값들을 담을 배열을 동적으로 만든다. 

        for (int i = 0; i < a; i++) {            // 반복문으로 배열에 숫자를 넣는다.
            arr[i] = scanner.nextInt();
        }
        for (int i : array1.sol(a,arr)) {       // 또 반복문으로
            System.out.print(i + " " );
        }

    }
}

```