/*
    자릿수 더하기
    https://programmers.co.kr/learn/courses/30/lessons/12931
 */

package one;

public class S12931 {
    public int solution(int n) {
        String numString = Integer.toString(n);
        char[] arr = numString.toCharArray();
        int answer = 0;

        for (char c : arr) {
            answer += (int) (c - '0');
        }

        return answer;
    }
}
