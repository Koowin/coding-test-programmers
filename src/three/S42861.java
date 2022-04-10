/*
    https://programmers.co.kr/learn/courses/30/lessons/42861
    섬 연결하기
 */
package three;

import java.util.*;

public class S42861 {
    public int solution(int n, int[][] costs) {

        List<Bridge> bridges = new ArrayList<>();
        for (int[] bridge : costs) {
            bridges.add(new Bridge(bridge[0], bridge[1], bridge[2]));
        }
        Collections.sort(bridges);

        UnionFind uf = new UnionFind(n);
        int cost = 0;

        for (Bridge b : bridges) {
            cost += b.connectIfNeed(uf);
        }

        return cost;
    }

    static class UnionFind {
        private int[] id;
        private int[] size;
        private int count;

        private UnionFind(int count) {
            this.count = count;
            id = new int[count];
            size = new int[count];
            for (int i = 0;i < count;i++) {
                id[i] = i;
                size[i] = 1;
            }
        }

        private boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        private int find(int p) {
            while (p != id[p]) {
                p = id[p];
            }
            return p;
        }

        public boolean union(int p, int q) {
            int i = find(p);
            int j = find(q);
            if (i == j) {
                return false;
            }

            if (size[i] < size[j]) {
                id[i] = id[j];
                size[j] += size[i];
            } else {
                id[j] = id[i];
                size[i] += size[j];
            }
            count--;
            return true;
        }
    }

    static class Bridge implements Comparable<Bridge> {
        private final int from;
        private final int to;
        private final int cost;

        private Bridge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        private int connectIfNeed(UnionFind uf) {
            if (uf.union(from, to)) {
                return cost;
            }
            return 0;
        }

        @Override
        public int compareTo(Bridge o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
