/*
    양과 늑대
    https://programmers.co.kr/learn/courses/30/lessons/92343
 */
package three;

import java.util.*;

public class S92343 {

  private Node[] nodes;

  public int solution(int[] info, int[][] edges) {
    Node.initNodes(info);
    Node.initEdges(edges);

    return Node.nodes[0].dfs(1, 0, 0);
  }


  static class Node {

    private static Node[] nodes;

    private final int index;
    private final Type type;
    private List<Node> children = new ArrayList<>();

    private static void initNodes(int[] info) {
      nodes = new Node[info.length];
      for (int i = 0; i < info.length; i++) {
        Type type;
        if (info[i] == 0) {
          type = Type.SHEEP;
        } else {
          type = Type.WOLF;
        }
        nodes[i] = new Node(i, type);
      }
    }

    private static void initEdges(int[][] edges) {
      for (int[] edge : edges) {
        Node parent = nodes[edge[0]];
        Node child = nodes[edge[1]];
        parent.addChild(child);
      }
    }

    private Node(int index, Type type) {
      this.index = index;
      this.type = type;
    }

    private void addChild(Node n) {
      children.add(n);
    }

    private int dfs(int bits, int sheepSum, int wolfSum) {

      if (type == Type.SHEEP) {
        sheepSum += 1;
      } else {
        wolfSum += 1;
      }

      if (sheepSum <= wolfSum) {
        return sheepSum;
      }

      bits ^= (1 << index);

      for (Node child : children) {
        bits |= (1 << child.index);
      }

      int ret = sheepSum;

      for (int i = 0, offset = 1; i < nodes.length; i++, offset <<= 1) {
        if ((bits & offset) != 0) {
          Node next = nodes[i];
          ret = Math.max(ret, next.dfs(bits, sheepSum, wolfSum));
        }
      }
      return ret;
    }
  }

  enum Type {
    SHEEP, WOLF;
  }
}
