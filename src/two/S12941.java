/*
    최솟값 만들기
    https://programmers.co.kr/learn/courses/30/lessons/12941
 */
package two;

import java.util.*;

public class S12941 {
    public int solution(int[] A, int[] B){
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for(int i=0, size=A.length;i<size;i++){
            answer += A[i] * B[size - i - 1];
        }

        return answer;
    }
}
