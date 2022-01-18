/*
    3진법 뒤집기
    https://programmers.co.kr/learn/courses/30/lessons/68935
 */

package one;

public class S68935 {
    public int solution(int n) {
        int answer = 0;

        while (n > 0) {
            answer *= 3;
            answer += n % 3;
            n /= 3;
        }

        return answer;
    }
}
