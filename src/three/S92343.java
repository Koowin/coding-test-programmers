/*
    양과 늑대
    https://programmers.co.kr/learn/courses/30/lessons/92343
 */
package three;

import java.util.*;

public class S92343 {
    public static void main(String[] args) {
        S92343 s = new S92343();
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        System.out.println(s.solution(info, edges));
    }

    private int[] info;
    private List<Integer>[] edges;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        initEdges(info.length, edges);

        return dfs(0, 0, 0, 1);
    }

    private void initEdges(int len, int[][] edges) {
        this.edges = new List[len];
        for (int i = 0; i < len; i++) {
            this.edges[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            this.edges[edge[0]].add(edge[1]);
        }
    }

    private int dfs(int sheep, int wolf, int visit, int nextVisitSet) {
        if (info[visit] == 0) {
            sheep++;
        } else {
            wolf++;
        }
        if (sheep <= wolf) {
            return 0;
        }

        nextVisitSet ^= 1 << visit;
        for (int child : edges[visit]) {
            nextVisitSet |= 1 << child;
        }

        int ret = sheep;
        for (int i = 0, offset = 1; i < info.length; i++, offset <<= 1) {
            if ((nextVisitSet & offset) != 0) {
                ret = Math.max(ret, dfs(sheep, wolf, i, nextVisitSet));
            }
        }
        return ret;
    }
}
