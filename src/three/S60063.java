/*
    https://programmers.co.kr/learn/courses/30/lessons/60063
    블록 이동하기
 */
package three;

import java.util.*;

public class S60063 {
    private boolean[][] board;
    private boolean[][][] visit;

    public int solution(int[][] board) {
        init(board);
        return getAnswer();
    }

    private void init(int[][] board) {
        this.board = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    this.board[i][j] = true;
                }
            }
        }

        visit = new boolean[board.length][board[0].length][2];
        Robot.goalR = board.length - 1;
        Robot.goalC = board[0].length - 1;
    }

    private int getAnswer() {
        PriorityQueue<Robot> pq = new PriorityQueue<>();
        pq.offer(new Robot(0, 1, Robot.Status.HORIZON, 0));
        visit[0][1][0] = true;

        int ret;
        loopOut:
        while (true) {
            Robot robot = pq.poll();
            EnumSet<Robot.Move> set = Robot.possibleMove(board, robot);
            for (Robot.Move m : set) {
                Robot next = m.apply(robot);
                if (!visit[next.r][next.c][next.status.getN()]) {
                    if (Robot.isGoal(next)) {
                        ret = next.cost;
                        break loopOut;
                    }
                    visit[next.r][next.c][next.status.getN()] = true;
                    pq.offer(next);
                }
            }
        }
        return ret;
    }

    private static class Robot implements Comparable<Robot> {
        private static int goalR;
        private static int goalC;
        private final int r;
        private final int c;
        private final Status status;
        private final int cost;

        private Robot(int r, int c, Status status, int cost) {
            this.r = r;
            this.c = c;
            this.status = status;
            this.cost = cost;
        }

        private static EnumSet<Move> possibleMove(boolean[][] board, Robot r) {
            EnumSet<Move> ret = EnumSet.noneOf(Move.class);
            for (Move m : Move.values()) {
                if (m.canMove(board, r)) {
                    ret.add(m);
                }
            }
            return ret;
        }

        private static boolean isGoal(Robot r) {
            return r.r == goalR && r.c == goalC;
        }

        @Override
        public int compareTo(Robot o) {
            return cost - o.cost;
        }

        enum Status {
            HORIZON(0) {
                @Override
                public int getN() {
                    return this.n;
                }
            }, VERTICAL(1) {
                @Override
                public int getN() {
                    return this.n;
                }
            };

            final int n;

            Status(int n) {
                this.n = n;
            }

            public abstract int getN();
        }

        enum Move {
            LEFT {
                @Override
                public boolean canMove(boolean[][] board, Robot r) {
                    if (r.status == Status.HORIZON) {
                        return Move.isLeftOneEmpty(board, r);
                    } else {
                        return Move.isLeftTwoEmpty(board, r);
                    }
                }

                @Override
                public Robot apply(Robot r) {
                    return new Robot(r.r, r.c - 1, r.status, r.cost + 1);
                }
            },
            RIGHT {
                @Override
                public boolean canMove(boolean[][] board, Robot r) {
                    if (r.status == Status.HORIZON) {
                        return Move.isRightOneEmpty(board, r);
                    } else {
                        return Move.isRightTwoEmpty(board, r);
                    }
                }

                @Override
                public Robot apply(Robot r) {
                    return new Robot(r.r, r.c + 1, r.status, r.cost + 1);
                }
            },
            UP {
                @Override
                public boolean canMove(boolean[][] board, Robot r) {
                    if (r.status == Status.HORIZON) {
                        return Move.isUpTwoEmpty(board, r);
                    } else {
                        return Move.isUpOneEmpty(board, r);
                    }
                }

                @Override
                public Robot apply(Robot r) {
                    return new Robot(r.r - 1, r.c, r.status, r.cost + 1);
                }
            },
            DOWN {
                @Override
                public boolean canMove(boolean[][] board, Robot r) {
                    if (r.status == Status.HORIZON) {
                        return Move.isDownTwoEmpty(board, r);
                    } else {
                        return Move.isDownOneEmpty(board, r);
                    }
                }

                @Override
                public Robot apply(Robot r) {
                    return new Robot(r.r + 1, r.c, r.status, r.cost + 1);
                }
            },
            TURN_HEAD_CLOCK {
                @Override
                public boolean canMove(boolean[][] board, Robot r) {
                    if (r.status == Status.HORIZON) {
                        return Move.isDownTwoEmpty(board, r);
                    } else {
                        return Move.isLeftTwoEmpty(board, r);
                    }
                }

                @Override
                public Robot apply(Robot r) {
                    if (r.status == Status.HORIZON) {
                        return new Robot(r.r + 1, r.c - 1, Status.VERTICAL, r.cost + 1);
                    } else {
                        return new Robot(r.r - 1, r.c, Status.HORIZON, r.cost + 1);
                    }
                }
            },
            TURN_TAIL_CLOCK {
                @Override
                public boolean canMove(boolean[][] board, Robot r) {
                    if (r.status == Status.HORIZON) {
                        return Move.isUpTwoEmpty(board, r);
                    } else {
                        return Move.isRightTwoEmpty(board, r);
                    }
                }

                @Override
                public Robot apply(Robot r) {
                    if (r.status == Status.HORIZON) {
                        return new Robot(r.r, r.c, Status.VERTICAL, r.cost + 1);
                    } else {
                        return new Robot(r.r, r.c + 1, Status.HORIZON, r.cost + 1);
                    }
                }
            },
            TURN_HEAD_COUNTER_CLOCK {
                @Override
                public boolean canMove(boolean[][] board, Robot r) {
                    if (r.status == Status.HORIZON) {
                        return Move.isUpTwoEmpty(board, r);
                    } else {
                        return Move.isRightTwoEmpty(board, r);
                    }
                }

                @Override
                public Robot apply(Robot r) {
                    if (r.status == Status.HORIZON) {
                        return new Robot(r.r, r.c - 1, Status.VERTICAL, r.cost + 1);
                    } else {
                        return new Robot(r.r - 1, r.c + 1, Status.HORIZON, r.cost + 1);
                    }
                }
            },
            TURN_TAIL_COUNTER_CLOCK {
                @Override
                public boolean canMove(boolean[][] board, Robot r) {
                    if (r.status == Status.HORIZON) {
                        return Move.isDownTwoEmpty(board, r);
                    } else {
                        return Move.isLeftTwoEmpty(board, r);
                    }
                }

                @Override
                public Robot apply(Robot r) {
                    if (r.status == Status.HORIZON) {
                        return new Robot(r.r + 1, r.c, Status.VERTICAL, r.cost + 1);
                    } else {
                        return new Robot(r.r, r.c, Status.HORIZON, r.cost + 1);
                    }
                }
            };

            private static boolean isLeftOneEmpty(boolean[][] board, Robot r) {
                return r.c > 1 && !board[r.r][r.c - 2];
            }

            private static boolean isLeftTwoEmpty(boolean[][] board, Robot r) {
                return r.c > 0 && !board[r.r][r.c - 1] && !board[r.r - 1][r.c - 1];
            }

            private static boolean isRightOneEmpty(boolean[][] board, Robot r) {
                return r.c + 1 < board[0].length && !board[r.r][r.c + 1];
            }

            private static boolean isRightTwoEmpty(boolean[][] board, Robot r) {
                return r.c + 1 < board[0].length && !board[r.r][r.c + 1] && !board[r.r - 1][r.c + 1];
            }

            private static boolean isUpOneEmpty(boolean[][] board, Robot r) {
                return r.r > 1 && !board[r.r - 2][r.c];
            }

            private static boolean isUpTwoEmpty(boolean[][] board, Robot r) {
                return r.r > 0 && !board[r.r - 1][r.c] && !board[r.r - 1][r.c - 1];
            }

            private static boolean isDownOneEmpty(boolean[][] board, Robot r) {
                return r.r + 1 < board.length && !board[r.r + 1][r.c];
            }

            private static boolean isDownTwoEmpty(boolean[][] board, Robot r) {
                return r.r + 1 < board.length && !board[r.r + 1][r.c] && !board[r.r + 1][r.c - 1];
            }

            public abstract boolean canMove(boolean[][] board, Robot r);

            public abstract Robot apply(Robot r);
        }

    }
}
