/*
    K번째수
    https://programmers.co.kr/learn/courses/30/lessons/42748
 */
package one;

import java.util.*;

public class S42748 {
    public int[] solution(int[] array, int[][] commands) {
        int len = commands.length;
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            int[] cutArray = new int[commands[i][1] - commands[i][0] + 1];
            System.arraycopy(array, commands[i][0] - 1, cutArray, 0, cutArray.length);
            Arrays.sort(cutArray);
            answer[i] = cutArray[commands[i][2] - 1];
        }
        return answer;
    }
}
