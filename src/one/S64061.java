/*
    크레인 인형뽑기 게임
    https://programmers.co.kr/learn/courses/30/lessons/64061
 */
package one;

import java.util.*;

public class S64061 {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int len = board[0].length;
        Stack<Integer>[] stacks = new Stack[len];
        Stack<Integer> basket = new Stack<>();

        for (int i = 0; i < len; i++) {
            stacks[i] = new Stack<>();
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j < len; j++) {
                System.out.print(board[i][j] + " ");
                if (board[i][j] != 0) {
                    stacks[j].push(board[i][j]);
                }
            }
            System.out.println();
        }

        for (int move : moves) {
            if (stacks[move - 1].isEmpty())
                continue;
            int selected = stacks[move - 1].pop();
            if (basket.isEmpty())
                basket.push(selected);
            else {
                if (basket.peek() == selected) {
                    basket.pop();
                    answer += 2;
                } else {
                    basket.push(selected);
                }
            }
        }
        return answer;
    }
}
