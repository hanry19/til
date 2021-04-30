### **설명**

> A, B 두 사람이 가위바위보 게임을 합니다. 총 N번의 게임을 하여 A가 이기면 A를 출력하고, B가 이기면 B를 출력합니다. 비길 경우에는 D를 출력합니다.  
> 가위, 바위, 보의 정보는 1:가위, 2:바위, 3:보로 정하겠습니다.  
> 예를 들어 N=5이면 두 사람의 각 회의 가위, 바위, 보 정보가 주어지면 각 회를 누가 이겼는지 출력하는 프로그램을 작성하세요.

### **입력**

> 첫 번째 줄에 게임 횟수인 자연수 N(1<=N<=100)이 주어집니다.  
> 두 번째 줄에는 A가 낸 가위, 바위, 보 정보가 N개 주어집니다.  
> 세 번째 줄에는 B가 낸 가위, 바위, 보 정보가 N개 주어집니다.

### **출력**

> 각 줄에 각 회의 승자를 출력합니다. 비겼을 경우는 D를 출력합니다.

### **풀이**

```
1. if 문으로 A가 이기는 경우와 비기는 경우를 나열한다. 
2. 그 외에 나머지는 B 가 승리한다.
```

```
    public String sol (int a, int b) {
        if  (a == b){
            return "d";
        } else if (a == 2 && b < a) {
            return "A";
        } else if (a == 3 && a != 1) {
            return "A";
        } else if (a == 1 && b == 3) {
            return "a";
        } else {
            return "B";
        }
    }


    public static void main(String[] args) {
        Array3 t = new Array3();
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] a = new int[n];
        int[] b = new int[n];
        String[] temp = new String[n];

        for (int i = 0; i < n ; i++) {
            a[i] = (int)(Math.random()*3+1);
            b[i] = (int)(Math.random()*3+1);
            temp[i]= t.sol(a[i], b[i]);
        }
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("b = " + Arrays.toString(b));
        System.out.println("t = " + Arrays.toString(temp));

    }
```