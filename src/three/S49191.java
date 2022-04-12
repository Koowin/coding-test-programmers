package three;

public class S49191 {
    private boolean[][] isWin;
    private int size;

    public int solution(int n, int[][] results) {
        size = n + 1;
        isWin = new boolean[size][size];

        for(int[] result: results) {
            addResult(result);
        }

        floyd();
        return getCount();
    }

    private void addResult(int[] result) {
        isWin[result[0]][result[1]] = true;
    }

    private void floyd() {
        for(int i=1;i<size;i++) {
            for(int j=1;j<size;j++) {
                if(isWin[j][i]) {
                    addWin(j, i);
                }
            }
        }
    }

    private void addWin(int winner, int loser) {
        for (int i=1;i<size;i++) {
            isWin[winner][i] |= isWin[loser][i];
        }
    }

    private int getCount() {
        int ret = 0;
        int n = size - 2;
        for (int i=1;i<size;i++) {
            int count = 0;
            for (int j=1;j<size;j++) {
                if(isWin[i][j]) {
                    count++;
                }
                if(isWin[j][i]) {
                    count++;
                }
            }
            if(count == n) {
                ret++;
            }
        }
        return ret;
    }
}
