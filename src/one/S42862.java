/*
    체육복
    https://programmers.co.kr/learn/courses/30/lessons/42862
 */

package one;

public class S42862 {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;

        int[] possible = new int[n + 2];

        for (int num : reserve) {
            possible[num]++;
        }
        for (int num : lost) {
            possible[num]--;
        }

        for (int i = 1; i < n + 1; i++) {
            if (possible[i] != -1) {
                continue;
            }
            if (possible[i - 1] == 1) {
                possible[i - 1]--;
                continue;
            }
            if (possible[i + 1] == 1) {
                possible[i + 1]--;
                continue;
            }
            answer--;
        }

        return answer;
    }
}
