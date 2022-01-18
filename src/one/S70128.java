/*
    내적
    https://programmers.co.kr/learn/courses/30/lessons/70128
 */

package one;

public class S70128 {
    public int solution(int[] a, int[] b) {
        int answer = 0;
        for (int i = 0, size = a.length; i < size; i++) {
            answer += a[i] * b[i];
        }
        return answer;
    }
}
