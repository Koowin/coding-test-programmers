/*
    전력망을 둘로 나누기
    https://programmers.co.kr/learn/courses/30/lessons/86971
 */
package two;

import java.util.*;

public class S86971 {
    public int solution(int n, int[][] wires) {
        Tree tree = new Tree(n);
        for(int[] arr : wires){
            tree.addEdge(arr[0]-1, arr[1]-1);
        }

        TreeWeight treeWeight = new TreeWeight(tree);
        treeWeight.findAllWeight();

        return treeWeight.getAnswer();
    }

    static class Tree{
        private final int size;
        private Set[] adj;

        public Tree(int size){
            this.size = size;
            adj = new Set[size];
            for(int i=0;i<size;i++){
                adj[i] = new HashSet<Integer>();
            }
        }

        public int getSize(){
            return size;
        }

        public void addEdge(int v1, int v2){
            adj[v1].add(v2);
            adj[v2].add(v1);
        }

        public Set<Integer> getAdj(int v){
            return adj[v];
        }
    }

    static class TreeWeight{
        private final int size;
        private final Tree tree;
        private int[] weights;
        private boolean[] isMarked;

        public TreeWeight(Tree tree){
            this.tree = tree;
            size = tree.getSize();
            weights = new int[size];
            isMarked = new boolean[size];
        }

        public void findAllWeight(){
            setWeight(0);
        }

        private void setWeight(int v){
            int weight = 1;
            isMarked[v] = true;

            Set<Integer> adj = tree.getAdj(v);
            for(Integer i : adj){
                if(!isMarked[i]){
                    setWeight(i);
                    weight += weights[i];
                }
            }

            weights[v] = weight;
        }

        public int getAnswer(){
            int min = size;
            for(int i : weights){
                int abs = Math.abs(i - (size - i));
                if(abs < min){
                    min = abs;
                }
            }
            return min;
        }
    }
}
