/*
    나누어 떨어지는 숫자 배열
    https://programmers.co.kr/learn/courses/30/lessons/12910
 */

package one;

import java.util.*;

public class S12910 {
    public ArrayList<Integer> solution(int[] arr, int divisor) {
        ArrayList<Integer> answer = new ArrayList<>();

        for (int num : arr) {
            if (num % divisor == 0) {
                answer.add(num);
            }
        }

        if (answer.size() == 0) {
            answer.add(-1);
        }
        Collections.sort(answer);


        return answer;
    }
}
