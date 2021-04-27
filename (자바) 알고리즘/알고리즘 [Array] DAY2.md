### 설명

> 선생님이 N명의 학생을 일렬로 세웠습니다. 일렬로 서 있는 학생의 키가 앞에서부터 순서대로 주어질 때, 맨 앞에 서 있는  
> 선생님이 볼 수 있는 학생의 수를 구하는 프로그램을 작성하세요. (앞에 서 있는 사람들보다 크면 보이고, 작거나 같으면 보이지 않습니다.)

### 입력

> 첫 줄에 정수 N(5<=N<=100,000)이 입력된다. 그 다음줄에 N명의 학생의 키가 앞에서부터 순서대로 주어진다.

### 출력

> 선생님이 볼 수 있는 최대학생수를 출력한다.

### 풀이

130 135 148 140 145 150 150 153

위와 같은 키를 가진 학생이 있을 경우 선생님은 중간에 엄청 큰 학생(max)가 서 있을 경우 그 뒤로는 볼 수 없다.  
그러므로

1.  가장 큰 학생(max)는 처음에 0으로 초기화 한다.
2.  첫번째 학생부터 순차적으로 max 값과 비교한다.
3.  max보다 클 경우 cnt++ 그리고 'max = 큰 학생의 키' 로 초기화 해준다.

아주 간단한 로직이다

```
    public static void main(String[] args) {
        Array2 t = new Array2();
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();

        int[] arr = new int[a];

        for (int i = 0; i < a; i++) {
            arr[i] = scanner.nextInt();
        }

        int max = 0;
        int cnt = 0;

        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
                cnt++;
            }
        }
        System.out.println("cnt = " + cnt);

    }
}



```