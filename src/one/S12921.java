/*
    소수 찾기
    https://programmers.co.kr/learn/courses/30/lessons/12921
 */

package one;

public class S12921 {
    public int solution(int n) {
        boolean[] isNotPrime = new boolean[n + 1];
        int answer = 0;
        for (int i = 2; i <= n; i++) {
            if (!isNotPrime[i]) {
                answer++;
                for (int j = i * 2; j <= n; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        return answer;
    }
}
