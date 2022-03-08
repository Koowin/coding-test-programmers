/*
    땅따먹기
    https://programmers.co.kr/learn/courses/30/lessons/12913
 */
package two;

public class S12913 {
    int solution(int[][] land) {
        LandMover landMover = new LandMover(land);
        landMover.findAnswer();
        return landMover.getAnswer();
    }

    static class LandMover{
        private int[][] land;
        private final int ROW_SIZE;
        private final int COL_SIZE;

        private LandMover(int[][] land){
            this.land = land;
            ROW_SIZE = land.length;
            COL_SIZE = land[0].length;
        }

        private void findAnswer(){
            for(int i=1;i<ROW_SIZE;i++){
                addScoreAtRow(i);
            }
        }

        private void addScoreAtRow(int row){
            int[] maxIndexOfPreRow = getMaxIndexOfRow(row-1);

            for(int i=0;i<COL_SIZE;i++){
                if(i == maxIndexOfPreRow[0]){
                    land[row][i] += land[row-1][maxIndexOfPreRow[1]];
                }
                else{
                    land[row][i] += land[row-1][maxIndexOfPreRow[0]];
                }
            }
        }

        private int[] getMaxIndexOfRow(int row){
            int[] ret = new int[2];
            int firstMaxInt = 0;
            int secondMaxInt = 0;

            for(int i=0;i<COL_SIZE;i++){
                if(land[row][i] >= firstMaxInt){
                    ret[1] = ret[0];
                    ret[0] = i;
                    secondMaxInt = firstMaxInt;
                    firstMaxInt = land[row][i];
                }
                else if(land[row][i] > secondMaxInt){
                    ret[1] = i;
                    secondMaxInt = land[row][i];
                }
            }

            return ret;
        }

        private int getAnswer(){
            int answer = 0;
            for(int i : land[ROW_SIZE-1]){
                if(i > answer){
                    answer = i;
                }
            }
            return answer;
        }
    }
}