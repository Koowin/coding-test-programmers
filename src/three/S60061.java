/*
    https://programmers.co.kr/learn/courses/30/lessons/60061
    기둥과 보 설치
 */
package three;

public class S60061 {
    private int n;
    private Point[][] board;
    private int structureCount = 0;

    public int[][] solution(int n, int[][] build_frame) {
        this.n = n + 1;
        initBoard();
        for (int[] cmd : build_frame) {
            executeCommand(cmd);
        }
        return getAnswer();
    }

    private void initBoard() {
        board = new Point[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = new Point(i, j);
            }
        }
    }

    private void executeCommand(int[] cmd) {
        if (cmd[3] == 0) {
            deleteIfCan(cmd[0], cmd[1], cmd[2]);
        } else {
            addIfCan(cmd[0], cmd[1], cmd[2]);
        }
    }

    private void deleteIfCan(int x, int y, int type) {
        if (type == 0) {
            if (canDeleteColumn(x, y)) {
                board[x][y].structure[0] = false;
                structureCount--;
            }
        } else {
            if (canDeleteBeam(x, y)) {
                board[x][y].structure[1] = false;
                structureCount--;
            }
        }
    }

    private boolean canDeleteColumn(int x, int y) {
        board[x][y].structure[0] = false;
        boolean ret = true;

        if (board[x][y + 1].structure[0]) {
            ret = canExistColumn(x, y + 1);
        }
        if (ret && board[x][y + 1].structure[1]) {
            ret = canExistBeam(x, y + 1);
        }
        if (ret && x > 0 && board[x - 1][y + 1].structure[1]) {
            ret = canExistBeam(x - 1, y + 1);
        }
        board[x][y].structure[0] = true;
        return ret;
    }

    private boolean canDeleteBeam(int x, int y) {
        board[x][y].structure[1] = false;
        boolean ret = true;

        if (board[x][y].structure[0]) {
            ret = canExistColumn(x, y);
        }
        if (ret && board[x + 1][y].structure[0]) {
            ret = canExistColumn(x + 1, y);
        }
        if (ret && board[x + 1][y].structure[1]) {
            ret = canExistBeam(x + 1, y);
        }
        if (ret && x > 0 && board[x - 1][y].structure[1]) {
            ret = canExistBeam(x - 1, y);
        }
        board[x][y].structure[1] = true;
        return ret;
    }

    private void addIfCan(int x, int y, int type) {
        if (type == 0) {
            if (canExistColumn(x, y)) {
                board[x][y].structure[0] = true;
                structureCount++;
            }
        } else {
            if (canExistBeam(x, y)) {
                board[x][y].structure[1] = true;
                structureCount++;
            }
        }
    }

    private boolean canExistColumn(int x, int y) {
        if (y == n - 1) {
            return false;
        }
        if (y == 0) {
            return true;
        }
        if (board[x][y - 1].structure[0]) {
            return true;
        }
        if (board[x][y].structure[1]) {
            return true;
        }
        if (x > 0 && board[x - 1][y].structure[1]) {
            return true;
        }
        return false;
    }

    private boolean canExistBeam(int x, int y) {
        if (x == n - 1) {
            return false;
        }
        if (board[x][y - 1].structure[0]) {
            return true;
        }
        if (board[x + 1][y - 1].structure[0]) {
            return true;
        }
        if ((x > 0 && board[x - 1][y].structure[1]) && (x < n - 2) && board[x + 1][y].structure[1]) {
            return true;
        }
        return false;
    }

    private int[][] getAnswer() {
        int[][] answer = new int[structureCount][3];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j].structure[0]) {
                    answer[index][0] = i;
                    answer[index][1] = j;
                    answer[index++][2] = 0;
                }
                if (board[i][j].structure[1]) {
                    answer[index][0] = i;
                    answer[index][1] = j;
                    answer[index++][2] = 1;
                }
            }
        }
        return answer;
    }

    static class Point {
        private final int x;
        private final int y;
        private boolean[] structure = new boolean[2];

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
