/*
    https://programmers.co.kr/learn/courses/30/lessons/42898
    등굣길
 */
package three;

public class S42898 {
    public int solution(int m, int n, int[][] puddles) {
        PathCounter counter = new PathCounter(m, n);
        counter.setPuddles(puddles);
        
        return counter.countPath();
    }

    static class PathCounter {
        private final int columnSize;
        private final int rowSize;

        private int[][] townMap;
        private boolean[][] puddles;

        private PathCounter(int m, int n) {
            columnSize = n + 1;
            rowSize = m + 1;

            townMap = new int[rowSize][columnSize];
            puddles = new boolean[rowSize][columnSize];
        }

        private void setPuddles(int[][] puddleLocation) {
            for (int[] puddle : puddleLocation) {
                puddles[puddle[0]][puddle[1]] = true;
            }
        }

        private int countPath() {
            final int divider = 1000000007;
            townMap[1][0] = 1;
            for (int i = 1; i < rowSize; i++) {
                for (int j = 1; j < columnSize; j++) {
                    if(!puddles[i][j]) {
                        townMap[i][j] = (townMap[i-1][j] + townMap[i][j-1]) % divider;
                    }
                }
            }

            return townMap[rowSize-1][columnSize-1];
        }
    }
}
