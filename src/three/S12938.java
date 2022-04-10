/*
    https://programmers.co.kr/learn/courses/30/lessons/12938
    최고의 집합
 */
package three;

public class S12938 {
    public int[] solution(int n, int s) {
        if (s < n) {
            return new int[] {-1};
        }

        int l = s / n;
        int r = s % n;
        int[] answer = new int[n];

        for(int i=0;i<n-r;i++) {
            answer[i] = l;
        }
        for (int i=n-r;i<n;i++) {
            answer[i] = l + 1;
        }
        return answer;
    }
}
