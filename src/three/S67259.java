/*
    경주로 건설
    https://programmers.co.kr/learn/courses/30/lessons/67259
 */
package three;

import java.util.*;

public class S67259 {
    public int solution(int[][] board) {
        Block.initBlocks(board);

        Queue<Path> queue = new LinkedList<>();
        queue.offer(new Path(Block.blocks[0][0], 0, Direction.DOWN));
        queue.offer(new Path(Block.blocks[0][0], 0, Direction.RIGHT));


        while (!queue.isEmpty()) {
            Path p = queue.poll();
            for (Direction d : p.location.movable) {
                Direction back = p.lastDirection.reverse();
                if (d != back) {
                    Path next = d.apply(p);
                    if (next.location.updatePath(next)) {
                        queue.offer(next);
                    }
                }
            }
        }
        return Block.blocks[board.length - 1][board[0].length - 1].shortest.cost * 100;
    }

    private static class Block {
        private static Block[][] blocks;
        private final int r, c;
        private final EnumSet<Direction> movable;
        private Path shortest;

        private static void initBlocks(int[][] board) {
            int rowSize = board.length;
            int colSize = board[0].length;
            blocks = new Block[rowSize][colSize];
            for (int i = 0; i < rowSize; i++) {
                for (int j = 0; j < colSize; j++) {
                    blocks[i][j] = new Block(i, j, calMovable(board, i, j));
                }
            }
        }

        private static EnumSet<Direction> calMovable(int[][] board, int i, int j) {
            EnumSet<Direction> ret = EnumSet.noneOf(Direction.class);
            if (board[i][j] == 1) {
                return ret;
            }
            if (j > 0 && board[i][j - 1] == 0) {
                ret.add(Direction.LEFT);
            }
            if (j + 1 < board[0].length && board[i][j + 1] == 0) {
                ret.add(Direction.RIGHT);
            }
            if (i > 0 && board[i - 1][j] == 0) {
                ret.add(Direction.UP);
            }
            if (i + 1 < board.length && board[i + 1][j] == 0) {
                ret.add(Direction.DOWN);
            }
            return ret;
        }

        private Block(int r, int c, EnumSet<Direction> movable) {
            this.r = r;
            this.c = c;
            this.movable = movable;
        }

        private boolean updatePath(Path p) {
            if (shortest == null || shortest.cost > p.cost) {
                shortest = p;
                return true;
            } else if (shortest.cost + 5 > p.cost) {
                return true;
            }
            return false;
        }
    }

    private static class Path {
        private final Block location;
        private final int cost;
        private final Direction lastDirection;

        private Path(Block location, int cost, Direction lastDirection) {
            this.location = location;
            this.cost = cost;
            this.lastDirection = lastDirection;
        }
    }

    enum Direction {
        LEFT {
            @Override
            public Direction reverse() {
                return RIGHT;
            }

            @Override
            public Path apply(Path p) {
                Block nextBlock = Block.blocks[p.location.r][p.location.c - 1];
                int nextCost = p.cost;
                if (p.lastDirection == this) {
                    nextCost += STRAIGHT_COST;
                } else {
                    nextCost += TURN_COST;
                }
                return new Path(nextBlock, nextCost, this);
            }
        }, RIGHT {
            @Override
            public Direction reverse() {
                return LEFT;
            }

            @Override
            public Path apply(Path p) {
                Block nextBlock = Block.blocks[p.location.r][p.location.c + 1];
                int nextCost = p.cost;
                if (p.lastDirection == this) {
                    nextCost += STRAIGHT_COST;
                } else {
                    nextCost += TURN_COST;
                }
                return new Path(nextBlock, nextCost, this);
            }
        }, UP {
            @Override
            public Direction reverse() {
                return DOWN;
            }

            @Override
            public Path apply(Path p) {
                Block nextBlock = Block.blocks[p.location.r - 1][p.location.c];
                int nextCost = p.cost;
                if (p.lastDirection == this) {
                    nextCost += STRAIGHT_COST;
                } else {
                    nextCost += TURN_COST;
                }
                return new Path(nextBlock, nextCost, this);
            }
        }, DOWN {
            @Override
            public Direction reverse() {
                return UP;
            }

            @Override
            public Path apply(Path p) {
                Block nextBlock = Block.blocks[p.location.r + 1][p.location.c];
                int nextCost = p.cost;
                if (p.lastDirection == this) {
                    nextCost += STRAIGHT_COST;
                } else {
                    nextCost += TURN_COST;
                }
                return new Path(nextBlock, nextCost, this);
            }
        };

        private static final int STRAIGHT_COST = 1;
        private static final int TURN_COST = 6;

        public abstract Direction reverse();

        public abstract Path apply(Path p);
    }
}
