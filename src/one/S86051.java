/*
    없는 숫자 더하기
    https://programmers.co.kr/learn/courses/30/lessons/86051
 */

package one;

public class S86051 {
    public int solution(int[] numbers) {
        int answer = 45;
        for (int number : numbers) {
            answer -= number;
        }
        return answer;
    }
}
