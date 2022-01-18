/*
    정수 내림차순으로 배치하기
    https://programmers.co.kr/learn/courses/30/lessons/12933
 */

package one;

import java.util.*;

public class S12933 {
    public long solution(long n) {
        String[] arr = ("" + n).split("");
        for (String s : arr) {
            System.out.print(s);
        }
        System.out.println();
        Arrays.sort(arr, Collections.reverseOrder());

        String answer = "";
        for (String s : arr) {
            answer += s;
        }
        System.out.println();

        return Long.parseLong(answer);
    }
}
