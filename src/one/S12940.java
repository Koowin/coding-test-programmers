/*
    최대공약수와 최소공배수
    https://programmers.co.kr/learn/courses/30/lessons/12940
 */

package one;

public class S12940 {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        int minNum = Math.min(n, m);
        int maxNum = Math.max(n, m);

        while (true) {
            int remain = maxNum % minNum;
            if (remain == 0) {
                answer[0] = minNum;
                break;
            }
            maxNum = minNum;
            minNum = remain;
        }
        answer[1] = n / answer[0] * m;

        return answer;
    }
}
