/*
    직사각형 별찍기
    https://programmers.co.kr/learn/courses/30/lessons/12969
 */

package one;

import java.util.*;

public class S12969 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        String str = "";
        for (int i = 0; i < a; i++) {
            str += '*';
        }
        for (int i = 0; i < b; i++) {
            System.out.println(str);
        }
    }
}
