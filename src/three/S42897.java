/*
    https://programmers.co.kr/learn/courses/30/lessons/42897
    도둑질
 */
package three;

public class S42897 {
    public int solution(int[] money) {
        int n= money.length;
        int[] dp0 = new int[n - 1];
        int[] dp1 = new int[n - 1];

        dp0[0] = money[0];
        dp0[1] = Math.max(money[0], money[1]);

        dp1[0] = money[1];
        dp1[1] = Math.max(money[1], money[2]);

        for(int i=2;i<n-1;i++) {
            dp0[i] = Math.max(dp0[i-1], dp0[i-2] + money[i]);
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i+1]);
        }

        return Math.max(dp0[n-2], dp1[n-2]);
    }
}
