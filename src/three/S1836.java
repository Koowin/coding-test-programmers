/*
    https://programmers.co.kr/learn/courses/30/lessons/1836/solution_groups?language=java
    리틀 프렌즈 사천성
 */
package three;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class S1836 {
    public static void main(String[] args) {
        String[] board = {"DBA", "C*A", "CDB"};
        Board b = new Board(3, 3, board);

        System.out.println(b.getDeleteSequence());
    }

    public String solution(int m, int n, String[] board) {
        Board b = new Board(m, n, board);

        return b.getDeleteSequence();
    }

    static class Board {
        private static final String IMPOSSIBLE = "IMPOSSIBLE";
        private final int ROW_SIZE;
        private final int COLUMN_SIZE;
        private boolean[][] board;

        List<BlockPair> blockPairs = new LinkedList<>();

        public Board(int ROW_SIZE, int COLUMN_SIZE, String[] board) {
            this.ROW_SIZE = ROW_SIZE;
            this.COLUMN_SIZE = COLUMN_SIZE;
            this.board = new boolean[ROW_SIZE][COLUMN_SIZE];
            setBoard(board);
            findBlockPairs(board);
        }

        private void setBoard(String[] board) {
            char blank = '.';
            for (int i = 0; i < ROW_SIZE; i++) {
                for (int j = 0; j < COLUMN_SIZE; j++) {
                    if (board[i].charAt(j) != blank) {
                        this.board[i][j] = true;
                    }
                }
            }
        }

        private void findBlockPairs(String[] board) {
            char next = 'A';
            for (int i = 0; i < 26; i++) {
                BlockPair b = new BlockPair(next++, board);
                if (b.status == BlockPair.Status.EXIST) {
                    blockPairs.add(b);
                }
            }
            for (BlockPair b : blockPairs) {
                b.updateStatus(this.board);
            }
        }

        private String getDeleteSequence() {
            StringBuilder deleteSequence = new StringBuilder();

            while (!blockPairs.isEmpty()) {
                boolean deleteFlag = false;
                for (Iterator<BlockPair> i = blockPairs.iterator(); i.hasNext(); ) {
                    BlockPair b = i.next();
                    if (b.status == BlockPair.Status.CLEARABLE) {
                        deleteSequence.append(b.c);
                        updateBoard(b);
                        i.remove();
                        deleteFlag = true;
                        break;
                    }
                }
                if (!deleteFlag) {
                    return IMPOSSIBLE;
                } else {
                    for (BlockPair b : blockPairs) {
                        b.updateStatus(board);
                    }
                }
            }
            return deleteSequence.toString();
        }

        private void updateBoard(BlockPair deleted) {
            board[deleted.row1][deleted.col1] = false;
            board[deleted.row2][deleted.col2] = false;
        }
    }

    static class BlockPair {
        enum Status {
            EXIST, CLEARABLE
        }

        enum Shape {
            HORIZON, VERTICAL, LEFT_DOWN, RIGHT_DOWN
        }

        private Status status = Status.CLEARABLE;
        private Shape shape;
        private final char c;
        private int row1;
        private int col1;
        private int row2;
        private int col2;

        private BlockPair(char c, String[] board) {
            this.c = c;
            loopOut:
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length(); j++) {
                    if (board[i].charAt(j) == c) {
                        row1 = i;
                        col1 = j;
                        status = Status.EXIST;
                        break loopOut;
                    }
                }
            }

            if (status == Status.EXIST) {
                loopOut:
                for (int i = board.length - 1; i >= 0; i--) {
                    for (int j = board[0].length() - 1; j >= 0; j--) {
                        if (board[i].charAt(j) == c) {
                            row2 = i;
                            col2 = j;
                            setShape();
                            break loopOut;
                        }
                    }
                }
            }
        }

        private void setShape() {
            if (col1 == col2) {
                shape = Shape.HORIZON;
            } else if (row1 == row2) {
                shape = Shape.VERTICAL;
            } else if (col1 > col2) {
                shape = Shape.LEFT_DOWN;
            } else {
                shape = Shape.RIGHT_DOWN;
            }
        }

        private void updateStatus(boolean[][] board) {
            if (status == Status.CLEARABLE) {
                return;
            }
            if (isClearable(board)) {
                status = Status.CLEARABLE;
            }
        }

        private boolean isClearable(boolean[][] board) {
            switch (shape) {
                case HORIZON:
                    return haveHorizonPath(board);
                case VERTICAL:
                    return haveVerticalPath(board);
                case LEFT_DOWN:
                    return haveLeftDownPath(board);
                case RIGHT_DOWN:
                    return haveRightDownPath(board);
            }
            return true;
        }

        private boolean haveHorizonPath(boolean[][] board) {
            for (int i = row1 + 1, j = col1; i < row2; i++) {
                if (board[i][j]) {
                    return false;
                }
            }
            return true;
        }

        private boolean haveVerticalPath(boolean[][] board) {
            for (int i = row1, j = col1 + 1; j < col2; j++) {
                if (board[i][j]) {
                    return false;
                }
            }
            return true;
        }

        private boolean haveLeftDownPath(boolean[][] board) {
            boolean path1 = true;
            int i = row1;
            int j = col1 - 1;
            for (; j > col2; j--) {
                if (board[i][j]) {
                    path1 = false;
                    break;
                }
            }
            if (path1) {
                for (; i < row2; i++) {
                    if (board[i][j]) {
                        path1 = false;
                        break;
                    }
                }
            }

            if (path1) {
                return true;
            }

            i = row1 + 1;
            j = col1;

            for (; i < row2; i++) {
                if (board[i][j]) {
                    return false;
                }
            }
            for (; j > col2; j--) {
                if (board[i][j]) {
                    return false;
                }
            }
            return true;
        }

        private boolean haveRightDownPath(boolean[][] board) {
            boolean path1 = true;
            int i = row1 + 1;
            int j = col1;
            for (; i < row2; i++) {
                if (board[i][j]) {
                    path1 = false;
                    break;
                }
            }
            if (path1) {
                for (; j < col2; j++) {
                    if (board[i][j]) {
                        path1 = false;
                        break;
                    }
                }
            }

            if (path1) {
                return true;
            }

            i = row1;
            j = col1 + 1;

            for (; j < col2; j++) {
                if (board[i][j]) {
                    return false;
                }
            }
            for (; i < row2; i++) {
                if (board[i][j]) {
                    return false;
                }
            }
            return true;
        }
    }
}
