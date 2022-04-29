/*
    카카오프렌즈 컬러링북
    https://programmers.co.kr/learn/courses/30/lessons/1829
 */

package two;

public class S1829 {
    private int[][] picture;
    private int rowSize;
    private int colSize;

    public int[] solution(int m, int n, int[][] picture) {
        rowSize = m;
        colSize = n;

        this.picture = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                this.picture[i][j] = picture[i][j];
            }
        }

        int maxAreaSize = 0;
        int count = 0;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (this.picture[i][j] != 0) {
                    count++;
                    int areaSize = getSameColorSize(i, j);
                    maxAreaSize = Math.max(maxAreaSize, areaSize);
                }
            }
        }
        return new int[]{count, maxAreaSize};
    }

    private int getSameColorSize(int r, int c) {
        int color = picture[r][c];
        picture[r][c] = 0;
        int ret = 1;
        if (r > 0 && picture[r - 1][c] == color) {
            ret += getSameColorSize(r - 1, c);
        }
        if (r + 1 < rowSize && picture[r + 1][c] == color) {
            ret += getSameColorSize(r + 1, c);
        }
        if (c > 0 && picture[r][c - 1] == color) {
            ret += getSameColorSize(r, c - 1);
        }
        if (c + 1 < colSize && picture[r][c + 1] == color) {
            ret += getSameColorSize(r, c + 1);
        }
        return ret;
    }
}
