/*
    https://programmers.co.kr/learn/courses/30/lessons/49189
    가장 먼 노드
 */
package three;

import java.util.*;

public class S49189 {
    public int solution(int n, int[][] edge) {
        final int answer = 0;

        Graph graph = new Graph(n);
        for(int[] e : edge){
            graph.addEdge(e);
        }

        return graph.getAnswer();
    }

    static class Graph{
        private final int n;
        private List<Integer>[] edges;

        private boolean[] visited;
        private int visitedCount = 0;
        private int answer;

        private Queue<Integer> queue = new LinkedList<>();

        private Graph(int n){
            this.n = n;
            visited = new boolean[n];
            edges = new List[n];
            for(int i=0;i<n;i++){
                edges[i] = new ArrayList<Integer>();
            }
        }

        private void addEdge(int[] e){
            int n1 = e[0] - 1;
            int n2 = e[1] - 1;
            edges[n1].add(n2);
            edges[n2].add(n1);
        }

        private int getAnswer(){
            queue.offer(0);
            visited[0] = true;
            while(oneStep());
            return answer;
        }

        private boolean oneStep(){
            int size = queue.size();
            if(visitedCount + size >= n){
                answer = size;
                return false;
            }

            for(int i=0;i<size;i++){
                visitNode(queue.poll());
            }

            visitedCount += size;

            return true;
        }

        private void visitNode(int node){
            for(int i : edges[node]){
                if(!visited[i]){
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
    }
}
