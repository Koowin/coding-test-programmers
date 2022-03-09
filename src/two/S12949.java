/*
    행렬의 곱셈
    https://programmers.co.kr/learn/courses/30/lessons/12949
 */
package two;

public class S12949 {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int a = arr1.length;
        int b = arr2.length;
        int c = arr2[0].length;
        int[][] answer = new int[a][c];

        for(int i=0;i<a;i++){
            for(int j=0;j<c;j++){
                for(int k=0;k<b;k++){
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return answer;
    }
}
