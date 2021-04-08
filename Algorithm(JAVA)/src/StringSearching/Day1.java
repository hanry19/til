package StringSearching;

import java.util.Locale;
import java.util.Scanner;

public class Day1 {

/*
1. 문자 찾기
  설명
        한 개의 문자열을 입력받고, 특정 문자를 입력받아 해당 특정문자가 입력받은 문자열에 몇 개
        존재하는지 알아내는 프로그램을 작성하세요.
        대소문자를 구분하지 않습니다.문자열의 길이는 100을 넘지 않습니다.

  입력
        첫 줄에 문자열이 주어지고, 두 번째 줄에 문자가 주어진다.
        문자열은 영어 알파벳으로만 구성되어 있습니다.

        출력
*/

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("문자열 입력하셈");
        String input = scan.next(); // 문자열 입력

        System.out.println("찾을 문자 입력하셈");
        char find = scan.next().charAt(0);  // 찾을 문자 입력

        int count=0;
        //대소문자 구분 X 를 위해 str , t를 대문자로 바꿔버린다
        input = input.toUpperCase();
        find = Character.toUpperCase(find);
        System.out.println(input +" "+ find);    // 대문자로 변한걸 확인할 수 있다.

/*        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)==t) answer++;
            // index로 문자를 접근하는게 charAt이고 이걸 한글자씩 확인을 한다.
        }*/

        // forEach 문에서는 string은 안됨.
        // 배열 혹은 iterator를 제공하는 collection framework (ex : Arraylist , list)등  가능
        // 그렇기에 문자를 하나하나 나눠서 배열로 만들어야한다. ==> toCharArray()
        // String을 하나하나 분리해서 문자 배열을 만든다.

        for (char x : input.toCharArray()) {
            if(x == find) count++; //x 가 str의 하나하나 대응하는ㄱ 것
        }

    }




}
