/*
    https://programmers.co.kr/learn/courses/30/lessons/60059
    자물쇠와 열쇠
 */
package three;

public class S60059 {
    public static void main(String[] args) {
        int[][] key = {
                {0, 0, 0},
                {1, 0, 0},
                {0, 1, 1}
        };

        int[][] lock = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        S60059 s = new S60059();
        System.out.println(s.solution(key, lock));

    }

    private int[][] lock;
    private int centerRowSize;
    private int centerColSize;

    public boolean solution(int[][] key, int[][] lock) {
        centerRowSize = lock.length;
        centerColSize = lock[0].length;
        initLock(lock);

        if (isValidKey(key)) {
            return true;
        }
        for (int i = 0; i < 3; i++) {
            key = rotateKey(key);
            if (isValidKey(key)) {
                return true;
            }
        }
        return false;
    }

    private void initLock(int[][] lock) {
        this.lock = new int[lock.length * 3][lock[0].length * 3];

        int rowSize = lock.length;
        int colSize = lock[0].length;

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                this.lock[rowSize + i][colSize + j] = lock[i][j];
            }
        }
    }

    private boolean isValidKey(int[][] key) {
        boolean ret = false;
        int rowSize = lock.length - key.length + 1;
        int colSize = lock[0].length - key[0].length + 1;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                addKey(key, i, j);
                ret = isClear();
                subKey(key, i, j);
                if (ret) {
                    return ret;
                }
            }
        }
        return ret;
    }

    private void addKey(int[][] key, int row, int col) {
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[0].length; j++) {
                lock[i + row][j + col] += key[i][j];
            }
        }
    }

    private void subKey(int[][] key, int row, int col) {
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[0].length; j++) {
                lock[i + row][j + col] -= key[i][j];
            }
        }
    }

    private boolean isClear(){
        for (int i = centerRowSize, len1 = centerRowSize * 2; i < len1; i++) {
            for (int j = centerColSize, len2 = centerColSize * 2; j < len2; j++) {
                if (lock[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[][] rotateKey(int[][] key) {
        int rowSize = key.length;
        int colSize = key[0].length;
        int[][] ret = new int[colSize][rowSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                ret[j][colSize - i - 1] = key[i][j];
            }
        }
        return ret;
    }
}
