package StringSearching;

import java.util.Locale;
import java.util.Scanner;

public class Day1Prac {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("아무문자나 입력하셈 ");
        String input = scan.next();
        System.out.println("찾을 문자");
        char find = scan.next().charAt(0);
        // scan.next() 는 string을 반환하니까 string에서 첫 글자를 찾는 chatAt(0)을 써준다.

        // 대소문자 구분 없애기 위해 전부다 대문자로 만들어 준다. 소문자도 가능
        input = input.toUpperCase();
        find = Character.toUpperCase(find);

        int count = 0;
/*        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i)==find) ++count;
        }
        System.out.println("count = " + count);*/

        for (char x : input.toCharArray()) {
            if(x == find) ++count;
        }
        System.out.println("count = " + count);
    }
}
