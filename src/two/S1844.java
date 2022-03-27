/*
    게임 맵 최단거리
    https://programmers.co.kr/learn/courses/30/lessons/1844
 */
package two;

import java.util.*;

public class S1844 {
    public int solution(int[][] maps) {
        PathFinder pf = new PathFinder(maps);
        pf.findPathCost();
        return pf.getAnswer();
    }

}

class PathFinder {
    private int[][] maps;
    private boolean[][] isVisited;
    private final int ROW_SIZE;
    private final int COL_SIZE;
    private Queue<Point> queue = new LinkedList<>();

    public PathFinder(int[][] maps) {
        ROW_SIZE = maps.length;
        COL_SIZE = maps[0].length;
        this.maps = maps;
        isVisited = new boolean[ROW_SIZE][COL_SIZE];
        setCostMax();
    }

    private void setCostMax() {
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                if (maps[i][j] == 1) {
                    maps[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    public void findPathCost() {
        maps[0][0] = 1;
        queue.offer(new Point(0, 0));

        while (queue.size() > 0) {
            oneMove();
        }

        if (maps[ROW_SIZE - 1][COL_SIZE - 1] == Integer.MAX_VALUE) {
            maps[ROW_SIZE - 1][COL_SIZE - 1] = -1;
        }
    }

    private void oneMove() {
        Point p = queue.poll();
        int nextCost = maps[p.row][p.col] + 1;

        if (p.row - 1 >= 0 && maps[p.row - 1][p.col] != 0 && !isVisited[p.row - 1][p.col]) {
            maps[p.row - 1][p.col] = Math.min(nextCost, maps[p.row - 1][p.col]);
            isVisited[p.row - 1][p.col] = true;
            queue.offer(new Point(p.row - 1, p.col));
        }
        if (p.row + 1 < ROW_SIZE && maps[p.row + 1][p.col] != 0 && !isVisited[p.row + 1][p.col]) {
            maps[p.row + 1][p.col] = Math.min(nextCost, maps[p.row + 1][p.col]);
            isVisited[p.row + 1][p.col] = true;
            queue.offer(new Point(p.row + 1, p.col));
        }
        if (p.col - 1 >= 0 && maps[p.row][p.col - 1] != 0 && !isVisited[p.row][p.col - 1]) {
            maps[p.row][p.col - 1] = Math.min(nextCost, maps[p.row][p.col - 1]);
            isVisited[p.row][p.col - 1] = true;
            queue.offer(new Point(p.row, p.col - 1));
        }
        if (p.col + 1 < COL_SIZE && maps[p.row][p.col + 1] != 0 && !isVisited[p.row][p.col + 1]) {
            maps[p.row][p.col + 1] = Math.min(nextCost, maps[p.row][p.col + 1]);
            isVisited[p.row][p.col + 1] = true;
            queue.offer(new Point(p.row, p.col + 1));
        }
    }


    public int getAnswer() {
        return maps[ROW_SIZE - 1][COL_SIZE - 1];
    }

    static class Point {
        private final int row, col;

        private Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
