/*
    카카오프렌즈 컬러링북
    https://programmers.co.kr/learn/courses/30/lessons/1829
 */

package two;

public class S1829 {
    static int rowSize, columnSize;
    private int[][] picture;

    public int[] solution(int m, int n, int[][] picture) {
        rowSize = m;
        columnSize = n;
        this.picture = picture;
        int zeroCount = 0;

        UF uf = new UF(m * n);

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (picture[i][j] == 0) {
                    zeroCount++;
                } else {
                    if (i + 1 < rowSize && isConnected(i, j, i + 1, j)) {
                        int index = getIndex(i, j);
                        uf.union(index, index + columnSize);
                    }
                    if (j + 1 < columnSize && isConnected(i, j, i, j + 1)) {
                        int index = getIndex(i, j);
                        uf.union(index, index + 1);
                    }
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = uf.getCount() - zeroCount;
        answer[1] = uf.getMaxCountOfId();
        return answer;
    }

    private boolean isConnected(int i1, int j1, int i2, int j2) {
        if (picture[i1][j1] == picture[i2][j2]) {
            return true;
        }
        return false;
    }

    private static int getIndex(int i, int j) {
        return i * columnSize + j;
    }

    static class UF {
        int[] id;
        int[] sz;
        int count;

        public UF(int N) {
            id = new int[N];
            sz = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
                sz[i] = 1;
            }
            count = N;
        }

        public int find(int p) {
            while (p != id[p]) {
                p = id[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int i = find(p);
            int j = find(q);

            if (i == j) {
                return;
            }

            if (sz[i] < sz[j]) {
                id[i] = j;
                sz[j] += sz[i];
            } else {
                id[j] = i;
                sz[i] += sz[j];
            }
            count--;
        }

        public int getCount() {
            return count;
        }

        public int getMaxCountOfId() {
            int max = Integer.MIN_VALUE;
            for (int i : sz) {
                max = Math.max(i, max);
            }
            return max;
        }
    }
}

/*
Stack 으로 문제에 접근하였을 때 풀이에 실패하였었다.

import java.util.*;

class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(picture[i][j] != 0){
                    numberOfArea++;

                    int thisColor = picture[i][j];
                    int sizeOfThisArea = 1;
                    Stack<Point> stack = new Stack<>();

                    picture[i][j] = 0;
                    stack.push(new Point(i,j));

                    while(!stack.empty()) {
                        Point p = stack.pop();

                        if (p.i < m - 1 && picture[p.i + 1][p.j] == thisColor) {
                            picture[p.i + 1][p.j] = 0;
                            stack.push(new Point(p.i + 1, p.j));
                            sizeOfThisArea++;
                        }
                        if (p.i > 0 && picture[p.i - 1][p.j] == thisColor) {
                            picture[p.i - 1][p.j] = 0;
                            stack.push(new Point(p.i - 1, p.j));
                            sizeOfThisArea++;
                        }
                        if (p.j < n - 1 && picture[p.i][p.j + 1] == thisColor) {
                            picture[p.i][p.j + 1] = 0;
                            stack.push(new Point(p.i, p.j + 1));
                            sizeOfThisArea++;
                        }
                        if (p.j > 0 && picture[p.i][p.j - 1] == thisColor) {
                            picture[p.i][p.j - 1] = 0;
                            stack.push(new Point(p.i, p.j - 1));
                            sizeOfThisArea++;
                        }
                    }
                    maxSizeOfOneArea = maxSizeOfOneArea < sizeOfThisArea ? sizeOfThisArea : maxSizeOfOneArea;
                }
            }
        }



        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    class Point{
        final int i, j;
        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}
 */
