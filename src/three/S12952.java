/*
    https://programmers.co.kr/learn/courses/30/lessons/12952
    N-Queen
 */
package three;

public class S12952 {
    private boolean[][] position;
    private int n;

    public int solution(int n) {
        this.n = n;
        position = new boolean[n][n];

        return queenCount(0, 0);
    }

    private int queenCount(int index, int selected) {
        if (index == position.length) {
            return 1;
        }
        int count = 0;
        for (int i = 0, offset = 1; i < n; i++, offset <<= 1) {
            if ((selected & offset) != offset && validCheck(index, i)) {
                position[index][i] = true;
                count += queenCount(index + 1, selected | offset);
                position[index][i] = false;
            }
        }
        return count;
    }

    private boolean validCheck(int r, int c) {
        for (int i = r, j = c; i >= 0 && j >= 0; i--, j--) {
            if (position[i][j]) {
                return false;
            }
        }
        for (int i = r - 1, j = c + 1; i >= 0 && j < n; i--, j++) {
            if (position[i][j]) {
                return false;
            }
        }

        return true;
    }
}
