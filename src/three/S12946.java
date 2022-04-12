/*
    https://programmers.co.kr/learn/courses/30/lessons/12946
    하노이의 탑
 */
package three;

public class S12946 {
    int[][] answer;
    int i = 0;

    public int[][] solution(int n) {
        answer = new int[(1<<n) - 1][2];
        move(n, 1, 3);
        return answer;
    }

    private void move(int n, int from, int to) {
        if (n == 1) {
            answer[i][0] = from;
            answer[i++][1] = to;
            return;
        }
        int left = 6 - from - to;
        move(n - 1, from, left);
        answer[i][0] = from;
        answer[i++][1] = to;
        move(n - 1, left, to);
    }
}
