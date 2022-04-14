/*
    https://programmers.co.kr/learn/courses/30/lessons/12907
    거스름돈
 */
package three;

import java.util.*;

public class S12907 {
    public static void main(String[] args) {
        S12907 s = new S12907();
        System.out.println(s.solution(10, new int[] {1,2,5}));
    }
    private final int MOD = 1_000_000_007;

    public int solution(int n, int[] money) {
        Arrays.sort(money);

        int[][] cache = new int[money.length+1][n+1];

        for(int i=1;i<cache.length;i++) {
            int unit = money[i-1];
            for(int j=1;j<=unit;j++) {
                cache[i][j] = cache[i-1][j];
            }
            cache[i][unit]++;
            for(int j=unit + 1;j<=n;j++) {
                cache[i][j] = (cache[i-1][j] + cache[i][j-unit]) % MOD;
            }
        }

        return cache[money.length][n];
    }
}