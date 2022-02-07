/*
    거리두기 확인하기
    https://programmers.co.kr/learn/courses/30/lessons/81302
 */

package two;

public class S81302 {
    private static final int TABLE_SIZE = 5;
    public int[] solution(String[][] places) {
        int[] answer = new int[TABLE_SIZE];
        for(int i=0;i<TABLE_SIZE;i++){
            Checker c = new Checker(places[i]);
            answer[i] = c.getAnswer();
        }
        return answer;
    }

    static class Checker{
        private final char CH_P = 'P';
        private final char CH_O = 'O';
        private final char CH_X = 'X';
        char[][] place = new char[5][5];
        int I, J;

        Checker(String[] input){
            for(int i=0;i<TABLE_SIZE;i++){
                place[i] = input[i].toCharArray();
            }
        }

        private int getAnswer(){
            int INT_TRUE = 1;
            int INT_FALSE = 0;

            for(int i=0;i<TABLE_SIZE;i++){
                for(int j=0;j<TABLE_SIZE;j++){
                    if(place[i][j] == CH_P){
                        if(!rightDistanceCheck(i,j)){
                            return INT_FALSE;
                        }
                    }
                }
            }
            return INT_TRUE;
        }

        private boolean rightDistanceCheck(int i, int j){
            I = i; J = j;
            return (dfs(0, i, j-1) && dfs(0, i, j+1) && dfs(0, i+1, j));
        }

        private boolean dfs(int count, int i, int j){
            final int DFS_END = 1;

            if(i >= TABLE_SIZE || j >= TABLE_SIZE || j < 0){
                return true;
            }
            if(i == I && j == J){
                return true;
            }

            if(count == DFS_END){
                if(place[i][j] == CH_P){
                    return false;
                }
                else{
                    return true;
                }
            }

            if(place[i][j] == CH_X){
                return true;
            }
            else if(place[i][j] == CH_P){
                return false;
            }
            else{
                return (dfs(count+1, i, j-1) && dfs(count+1, i, j+1) && dfs(count+1, i+1, j));
            }
        }
    }
}
