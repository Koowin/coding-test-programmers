/*
    크레인 인형뽑기 게임
    https://programmers.co.kr/learn/courses/30/lessons/64061
 */
package one;

import java.util.*;

public class S64061 {
    Deque<Integer>[] stacks;
    public int solution(int[][] board, int[] moves) {
        initStacks(board);
        Basket basket = new Basket();
        for (int i : moves) {
            int item = getItem(i);
            if (item != -1) {
                basket.addItem(item);
            }
        }
        return basket.count;
    }

    private void initStacks(int[][] board) {
        stacks = new Deque[board.length];
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new ArrayDeque<>();
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = board.length - 1; j >= 0; j--) {
                if (board[j][i] != 0) {
                    stacks[i].push(board[j][i]);
                } else {
                    break;
                }
            }
        }
    }

    private int getItem(int index) {
        if (stacks[--index].isEmpty()) {
            return -1;
        }
        return stacks[index].pop();
    }

    static class Basket {
        private int count = 0;
        private Deque<Integer> stack = new ArrayDeque<>();

        private void addItem(int n) {
            if (stack.isEmpty()) {
                stack.push(n);
                return;
            }
            if (stack.peek() == n) {
                count+=2;
                stack.pop();
            } else {
                stack.push(n);
            }
        }
    }
}
