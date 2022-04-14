/*
    https://programmers.co.kr/learn/courses/30/lessons/42892
    길 찾기 게임
 */
package three;

import java.util.*;

public class S42892 {
    List<Node> nodeList = new ArrayList<>();
    Node root;

    public int[][] solution(int[][] nodeinfo) {
        for (int i = 0; i < nodeinfo.length; i++) {
            Node n = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
            nodeList.add(n);
        }
        Collections.sort(nodeList);

        makeTree();

        List<Integer> preorder = new ArrayList<>();
        root.preorder(preorder);

        List<Integer> postorder = new ArrayList<>();
        root.postorder(postorder);

        int[][] answer = new int[2][];
        answer[0] = preorder.stream().mapToInt(i -> i).toArray();
        answer[1] = postorder.stream().mapToInt(i -> i).toArray();
        return answer;
    }

    private void makeTree() {
        root = nodeList.get(0);
        for (int i = 1; i < nodeList.size(); i++) {
            root.findChildAndAdd(nodeList.get(i));
        }
    }

    static class Node implements Comparable<Node> {
        private final int index;
        private final int x;
        private final int y;

        private Node leftChild;
        private Node rightChild;

        private Node(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }

        private void findChildAndAdd(Node n) {
            if (n.x < x) {
                if (leftChild != null) {
                    leftChild.findChildAndAdd(n);
                } else {
                    leftChild = n;
                }
            } else {
                if (rightChild != null) {
                    rightChild.findChildAndAdd(n);
                } else {
                    rightChild = n;
                }
            }
        }

        private void preorder(List<Integer> arr) {
            arr.add(index);
            if (leftChild != null) {
                leftChild.preorder(arr);
            }
            if (rightChild != null) {
                rightChild.preorder(arr);
            }
        }

        private void postorder(List<Integer> arr) {
            if (leftChild != null) {
                leftChild.postorder(arr);
            }
            if (rightChild != null) {
                rightChild.postorder(arr);
            }
            arr.add(index);
        }

        @Override
        public int compareTo(Node o) {
            return y != o.y ? Integer.compare(o.y, y) : Integer.compare(x, o.x);
        }
    }
}
