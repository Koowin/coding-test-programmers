/*
    https://programmers.co.kr/learn/courses/30/lessons/76503
    모두 0으로 만들기
 */
package three;

import java.util.*;

public class S76503 {
    public static void main(String[] args) {
        int[] a = {-5, 0, 2, 1, 2};
        int[][] edges = {
                {0, 1},
                {3, 4},
                {2, 3},
                {0, 3}
        };
        S76503 s = new S76503();
        System.out.println(s.solution(a, edges));

    }
    private List<Node> nodeList = new ArrayList<>();

    public long solution(int[] a, int[][] edges) {
        init(a);
        addEdges(edges);
        if (nodeList.get(0).dfs() != 0) {
            return -1;
        }
        return Node.count;
    }

    private void init(int[] a) {
        for (int i : a) {
            Node n = new Node(i);
            nodeList.add(n);
        }
    }

    public void addEdges(int[][] edges){
        for (int[] edge : edges) {
            Node n1 = nodeList.get(edge[0]);
            Node n2 = nodeList.get(edge[1]);
            n1.addLink(n2);
            n2.addLink(n1);
        }
    }

    static class Node {
        private List<Node> link = new ArrayList<>();
        private long weight;
        private static long count = 0;
        private boolean visited = false;

        private Node(int weight) {
            this.weight = weight;
        }

        private void addLink(Node n){
            link.add(n);
        }

        private long dfs() {
            visited = true;
            for (Node n : link) {
                if (!n.visited) {
                    weight += n.dfs();
                }
            }
            count += Math.abs(weight);
            return weight;
        }
    }
}
