/*
    https://programmers.co.kr/learn/courses/30/lessons/72415
    카드 짝 맞추기
 */
package three;

import java.util.*;

public class S72415 {
    public static void main(String[] args) {
        S72415 s = new S72415();
        int[][] board = {
                {1, 0, 0, 3},
                {2, 0, 0, 0},
                {0, 0, 0, 2},
                {3, 0, 1, 0}
        };
        System.out.println(s.solution(board, 1, 0));

    }

    private Point[][] board = new Point[4][4];
    private List<Point> cardLocation = new ArrayList<>();
    private int cardPairSize;
    private int clearCondition;

    public int solution(int[][] board, int r, int c) {
        initBoard(board);
        return deleteCard(this.board[r][c], 0) + cardLocation.size() * 2;
    }

    private void initBoard(int[][] board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.board[i][j] = new Point(i, j);
            }
        }
        for (int card = 1; card <= 6; card++) {
            Point p1 = null;
            loopOut:
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (board[i][j] == card) {
                        p1 = this.board[i][j];
                        break loopOut;
                    }
                }
            }
            if (p1 != null) {
                loopOut:
                for (int i = 3; i >= 0; i--) {
                    for (int j = 3; j >= 0; j--) {
                        if (board[i][j] == card) {
                            Point p2 = this.board[i][j];
                            Point.link(p1, p2);
                            cardLocation.add(p1);
                            break loopOut;
                        }
                    }
                }
            }
        }
        cardPairSize = cardLocation.size();
        clearCondition = (1 << cardPairSize) - 1;
    }

    private int deleteCard(Point from, int deletedCardSet) {
        if (deletedCardSet == clearCondition) {
            return 0;
        }
        int count = Integer.MAX_VALUE;
        for (int i = 0, offset = 1; i < cardPairSize; i++, offset <<= 1) {
            if ((deletedCardSet & offset) == 0) {

                //p1 -> p2 cost
                Point p1 = cardLocation.get(i);
                Point p2 = p1.pair;

                int cost = move(from, p1);
                p1.isCardExist = false;
                cost += move(p1, p2);
                p2.isCardExist = false;

                cost += deleteCard(p2, deletedCardSet | offset);
                count = Math.min(count, cost);

                p1.isCardExist = true;
                p2.isCardExist = true;

                //p2 -> p1 cost
                cost = move(from, p2);
                p2.isCardExist = false;
                cost += move(p2, p1);
                p1.isCardExist = false;

                cost += deleteCard(p1, deletedCardSet | offset);
                count = Math.min(count, cost);

                p1.isCardExist = true;
                p2.isCardExist = true;
            }
        }

        return count;
    }

    private int move(Point from, Point to) {
        if (from == to) {
            return 0;
        }
        int count = Integer.MAX_VALUE;

        if (from.c > to.c) {
            Point p = Move.LEFT.apply(board, from);
            if (p.c >= to.c) {
                count = Math.min(count, move(p, to) + 1);
            }
            p = Move.C_LEFT.apply(board, from);
            if (p.c >= to.c) {
                count = Math.min(count, move(p, to) + 1);
            }
        }
        if (from.c < to.c) {
            Point p = Move.RIGHT.apply(board, from);
            if (p.c <= to.c) {
                count = Math.min(count, move(p, to) + 1);
            }
            p = Move.C_RIGHT.apply(board, from);
            if (p.c <= to.c) {
                count = Math.min(count, move(p, to) + 1);
            }
        }
        if (from.r > to.r) {
            Point p = Move.UP.apply(board, from);
            if (p.r >= to.r) {
                count = Math.min(count, move(p, to) + 1);
            }
            p = Move.C_UP.apply(board, from);
            if (p.r >= to.r) {
                count = Math.min(count, move(p, to) + 1);
            }
        }
        if (from.r < to.r) {
            Point p = Move.DOWN.apply(board, from);
            if (p.r <= to.r) {
                count = Math.min(count, move(p, to) + 1);
            }
            p = Move.C_DOWN.apply(board, from);
            if (p.r <= to.r) {
                count = Math.min(count, move(p, to) + 1);
            }
        }
        return count;
    }

    static class Point {
        private final int r;
        private final int c;
        private boolean isCardExist;
        private Point pair;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        private static void link(Point p1, Point p2) {
            p1.isCardExist = true;
            p2.isCardExist = true;
            p1.pair = p2;
            p2.pair = p1;
        }

        @Override
        public String toString() {
            if (pair != null) {
                return String.format("(%d, %d)\t%b\t -> (%d, %d)", r, c, isCardExist, pair.r, pair.c);
            }
            return String.format("(%d, %d)\t%b\t", r, c, isCardExist);
        }
    }

    enum Move {
        LEFT {
            @Override
            public Point apply(Point[][] board, Point start) {
                int r = start.r;
                int c = start.c - 1;
                return board[r][c];
            }
        },
        RIGHT {
            @Override
            public Point apply(Point[][] board, Point start) {
                int r = start.r;
                int c = start.c + 1;
                return board[r][c];
            }
        },
        UP {
            @Override
            public Point apply(Point[][] board, Point start) {
                int r = start.r - 1;
                int c = start.c;
                return board[r][c];
            }
        },
        DOWN {
            @Override
            public Point apply(Point[][] board, Point start) {
                int r = start.r + 1;
                int c = start.c;
                return board[r][c];
            }
        },
        C_LEFT {
            @Override
            public Point apply(Point[][] board, Point start) {
                int r = start.r;
                int c = start.c - 1;

                for (; c > 0; c--) {
                    if (board[r][c].isCardExist) {
                        break;
                    }
                }
                return board[r][c];
            }
        },
        C_RIGHT {
            @Override
            public Point apply(Point[][] board, Point start) {
                int r = start.r;
                int c = start.c + 1;

                for (; c < 3; c++) {
                    if (board[r][c].isCardExist) {
                        break;
                    }
                }
                return board[r][c];
            }
        },
        C_UP {
            @Override
            public Point apply(Point[][] board, Point start) {
                int r = start.r - 1;
                int c = start.c;

                for (; r > 0; r--) {
                    if (board[r][c].isCardExist) {
                        break;
                    }
                }
                return board[r][c];
            }
        },
        C_DOWN {
            @Override
            public Point apply(Point[][] board, Point start) {
                int r = start.r + 1;
                int c = start.c;

                for (; r < 3; r++) {
                    if (board[r][c].isCardExist) {
                        break;
                    }
                }
                return board[r][c];
            }
        };

        public abstract Point apply(Point[][] board, Point start);
    }
}
