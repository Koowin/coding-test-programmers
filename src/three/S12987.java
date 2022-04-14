/*
    https://programmers.co.kr/learn/courses/30/lessons/12987
    숫자 게임
 */
package three;

import java.util.Arrays;

public class S12987 {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int first = 0;
        int last = B.length - 1;

        int count = 0;
        for(int i=last;i>=0;i--) {
            if(B[last] > A[i]) {
                count++;
                last--;
            } else {
                first++;
            }
        }

        return count;
    }
}
