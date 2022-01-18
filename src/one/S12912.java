/*
    두 정수 사이의 합
    https://programmers.co.kr/learn/courses/30/lessons/12912
 */

package one;

public class S12912 {
    public long solution(int a, int b) {
        long answer = 0;

        int minNum = Math.min(a, b);
        int maxNum = Math.max(a, b);

        for (int i = minNum; i <= maxNum; i++) {
            answer += i;
        }
        return answer;
    }
}
