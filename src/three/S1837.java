/*
    https://programmers.co.kr/learn/courses/30/lessons/1837
    GPS
 */
package three;

import java.util.*;

public class S1837 {
    private List<Integer>[] edges;
    private int[][] dp;

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        initEdgeList(n);
        addEdges(edge_list);

        dp = new int[k][n + 1];

        return findAnswer(gps_log);
    }

    private void initEdgeList(int n) {
        edges = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    private void addEdges(int[][] edgeList) {
        for (int[] edge : edgeList) {
            edges[edge[0]].add(edge[1]);
            edges[edge[1]].add(edge[0]);
        }
    }

    private int findAnswer(int[] gpsLog) {
        int infinite = 10_000;
        for (int i = 1; i < edges.length; i++) {
            dp[0][i] = infinite;
        }
        dp[0][gpsLog[0]] = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < edges.length; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k : edges[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k]);
                }
                dp[i][j]++;
            }
            dp[i][gpsLog[i]]--;
        }
        int ret = dp[dp.length - 1][gpsLog[gpsLog.length-1]];
        if (ret >= infinite) {
            return -1;
        }
        return ret;
    }
}
