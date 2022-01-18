/*
    문자열 내림차순으로 배치하기
    https://programmers.co.kr/learn/courses/30/lessons/12917
 */

package one;

import java.util.*;

public class S12917 {
    public String solution(String s) {
        String[] strArr = s.split("");
        Arrays.sort(strArr, Collections.reverseOrder());

        String answer = "";
        for (String str : strArr) {
            answer += str;
        }
        return answer;
    }
}
