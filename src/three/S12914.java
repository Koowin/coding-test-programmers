/*
    https://programmers.co.kr/learn/courses/30/lessons/12914
    멀리 뛰기
 */
package three;

public class S12914 {
    public long solution(int n) {
        long MOD = 1234567;
        long[] arr = new long[n+1];
        arr[1] = 1;
        if(n > 1) {
            arr[2] = 2;
        }
        for(int i=3;i<=n;i++) {
            arr[i] = (arr[i-1] + arr[i-2]) % MOD;
        }

        return arr[n];
    }
}
