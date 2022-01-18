/*
    같은 숫자는 싫어
    https://programmers.co.kr/learn/courses/30/lessons/12906
 */

package one;

import java.util.*;

public class S12906 {
    public ArrayList<Integer> solution(int[] arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        int last = -1;
        for (int num : arr) {
            if (num != last) {
                answer.add(num);
                last = num;
            }
        }

        return answer;
    }
}
