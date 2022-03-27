/*
    https://programmers.co.kr/learn/courses/30/lessons/12905
    가장 큰 정사각형 찾기
 */
package two;

public class S12905 {
    public int solution(int[][] board){
        int rowSize = board.length;
        int columnSize = board[0].length;

        int answer = 0;

        for(int i = 0; i<rowSize;i++){
            if(board[i][0] == 1){
                answer = 1;
                break;
            }
        }
        if(answer != 1){
            for(int j = 0;j<columnSize;j++){
                if(board[0][j] == 1){
                    answer = 1;
                    break;
                }
            }
        }

        for(int i = 1; i<rowSize;i++){
            for(int j=1;j<columnSize;j++){
                if(board[i][j] == 1){
                    board[i][j] = Math.min(board[i-1][j], board[i][j-1]);
                    board[i][j] = Math.min(board[i][j], board[i-1][j-1]);
                    board[i][j] ++;
                    answer = Math.max(board[i][j] , answer);
                }
            }
        }
        return answer * answer;
    }
}
