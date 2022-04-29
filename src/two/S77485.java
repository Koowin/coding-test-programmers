/*
    행렬 테두리 회전하기
    https://programmers.co.kr/learn/courses/30/lessons/77485
 */
package two;

public class S77485 {
    private int[][] matrix;

    public int[] solution(int rows, int columns, int[][] queries) {
        initMatrix(rows, columns);
        int[] answer = new int[queries.length];
        int i = 0;
        for (int[] query : queries) {
            answer[i++] = rotate(query);
        }
        return answer;
    }

    private void initMatrix(int rowSize, int columnSize) {
        matrix = new int[rowSize][columnSize];
        int val = 1;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                matrix[i][j] = val++;
            }
        }
    }

    private int rotate(int[] query) {
        int ret = Integer.MAX_VALUE;
        int r1 = query[0];
        int c1 = query[1];
        int r2 = query[2];
        int c2 = query[3];


        return ret;
    }
}
