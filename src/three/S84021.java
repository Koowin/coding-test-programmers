/*
    퍼즐 조각 채우기
    https://programmers.co.kr/learn/courses/30/lessons/84021
 */
package three;

import java.util.*;

public class S84021 {
    List<Block> boardBlocks;
    List<Block> tableBlocks;

    public int solution(int[][] game_board, int[][] table) {
        boardBlocks = extractBlocks(game_board, 0);
        tableBlocks = extractBlocks(table, 1);

        int answer = 0;
        for (Block boardBlock : boardBlocks) {
            for (Iterator<Block> iter = tableBlocks.iterator(); iter.hasNext(); ) {
                Block tableBlock = iter.next();
                if (boardBlock.equals(tableBlock)) {
                    answer += boardBlock.count;
                    iter.remove();
                    break;
                }
            }
        }
        return answer;
    }

    private List<Block> extractBlocks(int[][] board, int blockInt) {
        List<Block> ret = new LinkedList<>();

        boolean isNeedRotate = blockInt != 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == blockInt) {
                    ret.add(Block.makeBlock(board, i, j, isNeedRotate));
                }
            }
        }
        return ret;
    }

    static class Block {
        private final int count;
        private boolean[][][] shapes;

        private Block(boolean[][][] shapes) {
            this.shapes = shapes;
            int count = 0;
            for (boolean[] arr : shapes[0]) {
                for (boolean b : arr) {
                    if (b) {
                        count++;
                    }
                }
            }
            this.count = count;
        }

        private static Block makeBlock(int[][] board, int r, int c, boolean isNeedRotate) {
            boolean[][] s0 = extractShape(board, r, c);
            if (!isNeedRotate) {
                boolean[][][] shapes = new boolean[1][][];
                shapes[0] = s0;
                return new Block(shapes);
            }
            boolean[][] s1 = rotate(s0);
            boolean[][] s2 = rotate(s1);

            boolean[][][] shapes;
            if (isSame(s0, s2)) {
                shapes = new boolean[2][][];
                shapes[0] = s0;
                shapes[1] = s1;
            } else {
                shapes = new boolean[4][][];
                shapes[0] = s0;
                shapes[1] = s1;
                shapes[2] = s2;
                shapes[3] = rotate(s2);
            }


            return new Block(shapes);
        }

        private static boolean[][] extractShape(int[][] board, int r, int c) {
            boolean[][] ret = new boolean[6][11];
            int trueInt = board[r][c];
            int falseInt = 1 - board[r][c];

            int rowOffset = -r;
            int colOffset = 5 - c;

            Deque<Integer> rowStack = new ArrayDeque<>();
            Deque<Integer> colStack = new ArrayDeque<>();
            rowStack.push(r);
            colStack.push(c);

            int minRow = Integer.MAX_VALUE;
            int maxRow = Integer.MIN_VALUE;
            int minCol = Integer.MAX_VALUE;
            int maxCol = Integer.MIN_VALUE;

            while (!rowStack.isEmpty()) {
                int nr = rowStack.pop();
                int nc = colStack.pop();
                board[nr][nc] = falseInt;
                ret[nr + rowOffset][nc + colOffset] = true;

                minRow = Math.min(minRow, nr + rowOffset);
                maxRow = Math.max(maxRow, nr + rowOffset);
                minCol = Math.min(minCol, nc + colOffset);
                maxCol = Math.max(maxCol, nc + colOffset);

                if (nc > 0 && board[nr][nc - 1] == trueInt) {
                    rowStack.push(nr);
                    colStack.push(nc - 1);
                }
                if (nc + 1 < board[0].length && board[nr][nc + 1] == trueInt) {
                    rowStack.push(nr);
                    colStack.push(nc + 1);
                }
                if (nr > 0 && board[nr - 1][nc] == trueInt) {
                    rowStack.push(nr - 1);
                    colStack.push(nc);
                }
                if (nr + 1 < board.length && board[nr + 1][nc] == trueInt) {
                    rowStack.push(nr + 1);
                    colStack.push(nc);
                }
            }
            return trimBlock(ret, minRow, maxRow, minCol, maxCol);
        }

        private static boolean[][] trimBlock(boolean[][] shape, int minRow, int maxRow, int minCol, int maxCol) {
            boolean[][] ret = new boolean[maxRow - minRow + 1][maxCol - minCol + 1];
            for (int i = minRow; i <= maxRow; i++) {
                for (int j = minCol; j <= maxCol; j++) {
                    ret[i - minRow][j - minCol] = shape[i][j];
                }
            }
            return ret;
        }

        private static boolean[][] rotate(boolean[][] shape) {
            int rowSize = shape.length;
            int colSize = shape[0].length;

            boolean[][] ret = new boolean[colSize][rowSize];

            for (int i = 0; i < rowSize; i++) {
                for (int j = 0; j < colSize; j++) {
                    ret[j][rowSize - 1 - i] = shape[i][j];
                }
            }
            return ret;
        }

        private static boolean isSame(boolean[][] s1, boolean[][] s2) {
            if (s1.length != s2.length || s1[0].length != s2[0].length) {
                return false;
            }
            for (int i = 0; i < s1.length; i++) {
                for (int j = 0; j < s1[0].length; j++) {
                    if (s1[i][j] != s2[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Block block = (Block) o;
            if (this.count != block.count) {
                return false;
            }
            for (boolean[][] s1 : this.shapes) {
                for (boolean[][] s2 : block.shapes) {
                    if (isSame(s1, s2)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
