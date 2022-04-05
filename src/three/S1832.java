/*
    https://programmers.co.kr/learn/courses/30/lessons/1832
    보행자 천국
 */

package three;

public class S1832 {
    private static final int MOD = 20170805;
    private Intersection[][] cityMap;

    public int solution(int m, int n, int[][] cityMap) {
        this.cityMap = new Intersection[m][n];

        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                int type = cityMap[i][j];
                if(type == 0) {
                    this.cityMap[i][j] = new Intersection(Type.FREE);
                } else if (type == 1) {
                    this.cityMap[i][j] = new Intersection(Type.BLOCKED);
                } else {
                    this.cityMap[i][j] = new Intersection(Type.STRAIGHT);
                }
            }
        }

        moveFollowEdge();
        moveToDestination();

        return getAnswer();
    }

    private void moveFollowEdge() {
        for (int i=0;i<cityMap.length;i++) {
            Type t = cityMap[i][0].type;
            if (t == Type.FREE) {
                cityMap[i][0].toRight = 1;
                cityMap[i][0].toDown = 1;
            } else if (t == Type.BLOCKED) {
                break;
            } else {
                cityMap[i][0].toDown = 1;
            }
        }
        for (int i=0, size = cityMap[0].length;i<size;i++) {
            Type t = cityMap[0][i].type;
            if (t == Type.FREE) {
                cityMap[0][i].toRight = 1;
                cityMap[0][i].toDown = 1;
            } else if (t == Type.BLOCKED) {
                break;
            } else {
                cityMap[0][i].toRight = 1;
            }
        }
    }

    private void moveToDestination() {
        for (int i=1;i<cityMap.length;i++) {
            for (int j=1, size = cityMap[0].length; j<size;j++) {
                Type t = cityMap[i][j].type;
                if (t == Type.FREE) {
                    int count = cityMap[i-1][j].toDown + cityMap[i][j-1].toRight;
                    count %= MOD;
                    cityMap[i][j].toRight = count;
                    cityMap[i][j].toDown = count;
                }
                else if (t == Type.STRAIGHT) {
                    cityMap[i][j].toRight = cityMap[i][j-1].toRight % MOD;
                    cityMap[i][j].toDown = cityMap[i-1][j].toDown % MOD;
                }
            }
        }
    }

    private int getAnswer() {
        int row = cityMap.length - 1;
        int col = cityMap[0].length - 1;
        int ret;
        if (cityMap[row][col].type == Type.FREE) {
            ret = cityMap[row][col].toRight;
        } else {
            ret = (cityMap[row][col].toRight + cityMap[row][col].toDown);
        }
        return ret % MOD;
    }

    enum Type {
        FREE, BLOCKED, STRAIGHT
    }

    static class Intersection {
        private final Type type;
        private int toRight = 0;
        private int toDown = 0;

        private Intersection(Type type) {
            this.type = type;
        }
    }
}
