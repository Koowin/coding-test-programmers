/*
    예산
    https://programmers.co.kr/learn/courses/30/lessons/12982
 */

package one;

import java.util.*;

public class S12982 {
    public int solution(int[] d, int budget) {
        int answer = 0;

        Arrays.sort(d);

        for (int money : d) {
            budget -= money;
            answer++;
            if (budget < 0) {
                answer--;
                break;
            }
        }
        return answer;
    }
}
