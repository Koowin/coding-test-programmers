/*
    https://programmers.co.kr/learn/courses/30/lessons/92344
    파괴되지 않은 건물
 */
package three;

public class S92344 {
    private int[][] board;
    private int[][] sum;

    public int solution(int[][] board, int[][] skill) {
        this.board = board;
        sum = new int[board.length + 1][board[0].length + 1];

        for (int[] cmd : skill) {
            executeSkill(cmd);
        }
        return getFineBuildingCount();
    }

    private void executeSkill(int[] cmd) {
        int r1 = cmd[1];
        int c1 = cmd[2];
        int r2 = cmd[3] + 1;
        int c2 = cmd[4] + 1;
        int degree = cmd[5];

        if (cmd[0] == 1) {
            sum[r1][c1] -= degree;
            sum[r2][c2] -= degree;
            sum[r1][c2] += degree;
            sum[r2][c1] += degree;
        } else {
            sum[r1][c1] += degree;
            sum[r2][c2] += degree;
            sum[r1][c2] -= degree;
            sum[r2][c1] -= degree;
        }
    }

    private int getFineBuildingCount() {
        addSum();
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (sum[i][j] + board[i][j] > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private void addSum() {
        for (int i = 0; i < sum.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sum[i][j + 1] += sum[i][j];
            }
        }

        for (int j = 0; j < sum[0].length; j++) {
            for (int i = 0; i < board.length; i++) {
                sum[i + 1][j] += sum[i][j];
            }
        }
    }
}
