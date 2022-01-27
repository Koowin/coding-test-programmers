/*
    행렬 테두리 회전하기
    https://programmers.co.kr/learn/courses/30/lessons/77485
 */
package two;

public class S77485 {
    public static void main(String[] args) {
        S77485 s = new S77485();
        int[] answer = s.solution(6, 6, new int[][]{{2,2,5,4},{3,3,6,6},{5,1,6,3}});
        for(int i:answer){
            System.out.println(i);
        }
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        new MatrixCircular(rows, columns);

        int i=0;
        for (int[] query : queries) {
            answer[i++] = MatrixCircular.oneCirculate(query);
        }
        return answer;
    }

    static class MatrixCircular {
        static int[][] matrix;

        MatrixCircular(int row, int col) {
            matrix = new int[row][col];
            int count = 1;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = count++;
                }
            }
        }

        static int oneCirculate(int[] query) {
            int r1 = query[0] - 1;
            int c1 = query[1] - 1;
            int r2 = query[2] - 1;
            int c2 = query[3] - 1;

            int temp = matrix[r1][c1];
            int min = temp;
            for(int i=r1;i<r2;i++){
                matrix[i][c1] = matrix[i+1][c1];
                min = Math.min(matrix[i][c1], min);
            }
            for(int i=c1;i<c2;i++){
                matrix[r2][i] = matrix[r2][i+1];
                min = Math.min(matrix[r2][i], min);
            }
            for(int i=r2;i>r1;i--){
                matrix[i][c2] = matrix[i-1][c2];
                min = Math.min(matrix[i][c2], min);
            }
            for(int i=c2;i>c1+1;i--){
                matrix[r1][i] = matrix[r1][i-1];
                min = Math.min(matrix[r1][i], min);
            }
            matrix[r1][c1+1] = temp;

            return min;
        }
    }
}
