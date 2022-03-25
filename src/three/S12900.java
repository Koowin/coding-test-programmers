/*
    https://programmers.co.kr/learn/courses/30/lessons/12900
    2 x n 타일링
 */
package three;

public class S12900 {
    public int solution(int n) {
        final int modder = 1000000007;
        int[] arr = new int[n+1];
        arr[1] = 1;
        arr[2] = 2;

        for(int i=3;i<=n;i++){
            arr[i] = (arr[i-1] + arr[i-2]) % modder;
        }
        return arr[n];
    }
}
