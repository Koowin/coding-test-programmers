/*
    https://programmers.co.kr/learn/courses/30/lessons/43162
    네트워크
 */
package three;

public class S43162 {
    public int solution(int n, int[][] computers) {
        UnionFind uf = new UnionFind(n);
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if (computers[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }
    static class UnionFind {
        int[] id;
        int[] size;
        int count;

        private UnionFind(int count) {
            this.count = count;
            id = new int[count];
            size = new int[count];
            for (int i=0;i<count;i++) {
                id[i] = i;
                size[i] = 1;
            }
        }

        private int find(int p) {
            while(p != id[p]) {
                p = id[p];
            }
            return p;
        }

        private void union(int p, int q) {
            int i = find(p);
            int j = find(q);

            if (i == j) {
                return;
            }

            if (size[i] < size[j]) {
                id[i] = j;
                size[j] += size[i];
            } else {
                id[j] = i;
                size[i] += size[j];
            }
            count--;
        }

        private int getCount() {
            return count;
        }
    }
}
