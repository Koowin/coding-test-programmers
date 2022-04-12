/*
    https://programmers.co.kr/learn/courses/30/lessons/72413
    합승 택시 요금
 */
package three;

import java.util.*;

public class S72413 {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        Graph graph = new Graph(n);
        for (int[] fare : fares) {
            graph.addEdge(fare);
        }
        graph.setLocation(s, a, b);
        return graph.getMinimumCost();
    }
}

class Graph {
    private final int n;
    private List<Edge>[] edges;

    private int[] distFromStart;
    private int[] distFromA;
    private int[] distFromB;

    public Graph(int n) {
        n++;
        this.n = n;
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }

        distFromStart = new int[n];
        distFromA = new int[n];
        distFromB = new int[n];

        for (int i = 0; i < n; i++) {
            distFromStart[i] = Integer.MAX_VALUE;
            distFromA[i] = Integer.MAX_VALUE;
            distFromB[i] = Integer.MAX_VALUE;
        }
    }

    public void addEdge(int[] fare) {
        edges[fare[0]].add(new Edge(fare[1], fare[2]));
        edges[fare[1]].add(new Edge(fare[0], fare[2]));
    }

    public void setLocation(int s, int a, int b) {
        dijkstra(s, distFromStart);
        dijkstra(a, distFromA);
        dijkstra(b, distFromB);
    }

    private void dijkstra(int root, int[] dist) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        dist[root] = 0;
        queue.offer(new Edge(root, 0));

        while (!queue.isEmpty()) {
            Edge e = queue.poll();

            if (dist[e.to] < e.length) {
                continue;
            }

            for (Edge i : edges[e.to]) {
                int there = i.to;
                int nextDist = e.length + i.length;

                if (dist[there] > nextDist) {
                    dist[there] = nextDist;
                    queue.offer(new Edge(i.to, nextDist));
                }
            }
        }
    }

    public int getMinimumCost() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (distFromStart[i] == Integer.MAX_VALUE || distFromA[i] == Integer.MAX_VALUE || distFromB[i] == Integer.MAX_VALUE) {
                continue;
            }
            int cost = distFromStart[i] + distFromA[i] + distFromB[i];
            min = Math.min(min, cost);
        }
        return min;
    }

    static class Edge implements Comparable<Edge>{
        private final int to;
        private final int length;

        private Edge(int to, int length) {
            this.to = to;
            this.length = length;
        }

        @Override
        public int compareTo(Edge o){
            return Integer.compare(length, o.length);
        }
    }
}