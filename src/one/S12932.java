/*
    자연수 뒤집어 배열로 만들기
    https://programmers.co.kr/learn/courses/30/lessons/12932
 */

package one;

import java.util.*;

public class S12932 {
    public ArrayList<Integer> solution(long n) {
        ArrayList<Integer> answer = new ArrayList<>();
        while (n > 0) {
            answer.add((int) (n % 10));
            n /= 10;
        }
        return answer;
    }
}
