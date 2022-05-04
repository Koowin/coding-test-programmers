/*
    미로 탈출
    https://programmers.co.kr/learn/courses/30/lessons/81304
 */
package four;

import java.util.*;

public class S81304 {
    private static final int INFINITE = 100_000_000;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        Node sNode = initGraph(n, start, end, roads, traps);

        int[][] cache = initCache(n, traps.length);

        PriorityQueue<Path> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Path(sNode, 0, 0));

        Path ret;
        while (true) {
            Path p = priorityQueue.poll();
            if (p.to == Path.DESTINATION) {
                ret = p;
                break;
            }
            Node from = p.to;
            int activeTrapSet = Node.updateTrapSet(from, p.activeTrapSet);
            for (Edge edge : from.edges) {
                if (Node.canVisit(from, edge, activeTrapSet)) {
                    int newCost = p.cost + edge.cost;
                    if (newCost < cache[activeTrapSet][edge.to.index]) {
                        cache[activeTrapSet][edge.to.index] = newCost;
                        Path newPath = new Path(edge.to, newCost, activeTrapSet);
                        priorityQueue.offer(newPath);
                    }
                }
            }
        }

        return ret.cost;
    }

    private Node initGraph(int n, int start, int end, int[][] roads, int[] traps) {
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int[] road : roads) {
            Node.addEdge(nodes[road[0]], nodes[road[1]], road[2]);
        }

        int offset = 1;
        for (int trap : traps) {
            Node.addTrap(nodes[trap], offset);
            offset <<= 1;
        }

        Path.DESTINATION = nodes[end];
        return nodes[start];
    }

    private int[][] initCache(int n, int trapCount) {
        int rowSize = 1 << trapCount;
        int[][] ret = new int[rowSize][n + 1];
        for (int i = 0; i < rowSize; i++) {
            Arrays.fill(ret[i], INFINITE);
        }
        return ret;
    }

    static class Node {
        private static Map<Node, Integer> trapMap = new HashMap<>();

        private final int index;
        private List<Edge> edges = new ArrayList<>();

        private Node(int index) {
            this.index = index;
        }

        private static void addTrap(Node trap, int offset) {
            trapMap.put(trap, offset);
        }

        private static void addEdge(Node from, Node to, int cost) {
            from.edges.add(new Edge(to, cost, true));
            to.edges.add(new Edge(from, cost, false));
        }

        private static boolean canVisit(Node from, Edge edge, int activeTrapSet) {
            boolean oneActivated = isTrapActivated(from, activeTrapSet) ^ isTrapActivated(edge.to, activeTrapSet);
            if (oneActivated) {
                return !edge.direction;
            } else {
                return edge.direction;
            }
        }

        private static boolean isTrapActivated(Node node, int activeTrapSet) {
            Integer i = trapMap.get(node);
            if (i == null) {
                return false;
            }
            return (activeTrapSet & i) != 0;
        }

        private static int updateTrapSet(Node node, int activeTrapSet) {
            Integer i = trapMap.get(node);
            if (i == null) {
                return activeTrapSet;
            }
            return activeTrapSet ^ i;
        }

        @Override
        public boolean equals(Object o) {
            Node n = (Node) o;
            return index == n.index;
        }

        @Override
        public int hashCode() {
            return index;
        }
    }

    static class Edge {
        private final Node to;
        private final int cost;
        private final boolean direction;

        private Edge(Node to, int cost, boolean direction) {
            this.to = to;
            this.cost = cost;
            this.direction = direction;
        }
    }

    static class Path implements Comparable<Path> {
        private static Node DESTINATION;
        private final Node to;
        private final int cost;
        private final int activeTrapSet;

        private Path(Node to, int cost, int activeTrapSet) {
            this.to = to;
            this.cost = cost;
            this.activeTrapSet = activeTrapSet;
        }

        @Override
        public int compareTo(Path o) {
            return cost - o.cost;
        }
    }
}
