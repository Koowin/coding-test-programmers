/*
    사라지는 발판
    https://programmers.co.kr/learn/courses/30/lessons/92345
 */
package three;

import java.util.EnumSet;

public class S92345 {


    public int solution(int[][] board, int[] aloc, int[] bloc) {
        Player.initBoard(board);
        Player a = new Player(aloc);
        Player b = new Player(bloc);
        return solve(a, b).turn;
    }

    private Result solve(Player turnPlayer, Player stayPlayer) {
        EnumSet<Player.Movement> movements = turnPlayer.getPossibleMovement();

        if (movements.isEmpty()) {
            return new Result(false, 0);
        } else if (Player.isSamePosition(turnPlayer, stayPlayer)) {
            return new Result(true, 1);
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        boolean canWin = false;

        for (Player.Movement m : movements) {
            m.move(turnPlayer);
            Result r = solve(stayPlayer, turnPlayer);
            if (r.isWin) {
                max = Math.max(max, r.turn);
            } else {
                min = Math.min(min, r.turn);
                canWin = true;
            }
            m.undo(turnPlayer);
        }
        if (canWin) {
            return new Result(true, min + 1);
        } else {
            return new Result(false, max + 1);
        }
    }

    static class Player {
        static boolean[][] board;
        private int r;
        private int c;

        private static void initBoard(int[][] board) {
            int rowSize = board.length;
            int colSize = board[0].length;

            Player.board = new boolean[rowSize + 2][colSize + 2];

            for (int i = 0; i < rowSize; i++) {
                for (int j = 0; j < colSize; j++) {
                    if (board[i][j] == 1) {
                        Player.board[i + 1][j + 1] = true;
                    }
                }
            }
        }

        private static boolean isSamePosition(Player a, Player b) {
            return a.r == b.r && a.c == b.c;
        }

        private Player(int[] loc) {
            this.r = loc[0] + 1;
            this.c = loc[1] + 1;
        }

        private EnumSet<Movement> getPossibleMovement() {
            EnumSet<Movement> ret = EnumSet.noneOf(Movement.class);
            if (board[r][c - 1]) {
                ret.add(Movement.LEFT);
            }
            if (board[r][c + 1]) {
                ret.add(Movement.RIGHT);
            }
            if (board[r - 1][c]) {
                ret.add(Movement.UP);
            }
            if (board[r + 1][c]) {
                ret.add(Movement.DOWN);
            }
            return ret;
        }

        enum Movement {
            LEFT {
                @Override
                public void move(Player p) {
                    Player.board[p.r][p.c] = false;
                    p.c--;
                }

                @Override
                public void undo(Player p) {
                    p.c++;
                    Player.board[p.r][p.c] = true;
                }
            }, RIGHT {
                @Override
                public void move(Player p) {
                    Player.board[p.r][p.c] = false;
                    p.c++;
                }

                @Override
                public void undo(Player p) {
                    p.c--;
                    Player.board[p.r][p.c] = true;
                }
            }, UP {
                @Override
                public void move(Player p) {
                    Player.board[p.r][p.c] = false;
                    p.r--;
                }

                @Override
                public void undo(Player p) {
                    p.r++;
                    Player.board[p.r][p.c] = true;
                }
            }, DOWN {
                @Override
                public void move(Player p) {
                    Player.board[p.r][p.c] = false;
                    p.r++;
                }

                @Override
                public void undo(Player p) {
                    p.r--;
                    Player.board[p.r][p.c] = true;
                }
            };

            public abstract void move(Player p);

            public abstract void undo(Player p);
        }
    }

    private static class Result {
        private final boolean isWin;
        private final int turn;

        private Result(boolean isWin, int turn) {
            this.isWin = isWin;
            this.turn = turn;
        }
    }
}
