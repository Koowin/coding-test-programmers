/*
    https://programmers.co.kr/learn/courses/30/lessons/43105
    정수 삼각형
 */
package three;

public class S43105 {
    public int solution(int[][] triangle) {
        int size = triangle.length;

        for(int i=1;i<size;i++) {
            for(int j = 0;j <= i; j++) {
                int max;
                if(j == 0){
                    max = triangle[i-1][0];
                }
                else if(j == i){
                    max = triangle[i-1][i-1];
                }
                else{
                    max = Math.max(triangle[i-1][j-1], triangle[i-1][j]);
                }
                triangle[i][j] += max;
            }
        }

        return findMax(triangle);
    }

    private int findMax(int[][] triangle) {
        int size = triangle.length;

        int max = 0;

        for(int i : triangle[size-1]) {
            if(i > max){
                max = i;
            }
        }

        return max;
    }
}
