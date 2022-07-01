/*
    https://programmers.co.kr/learn/courses/30/lessons/42898
    등굣길
 */
package three;

public class S42898 {

  private static final int MOD = 1_000_000_007;
  private int[][] cache;

  public int solution(int m, int n, int[][] puddles) {
    cache = new int[m + 1][n + 1];
    cache[1][0] = 1;

    for (int[] puddle : puddles) {
      cache[puddle[0]][puddle[1]] = -1;
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (cache[i][j] != -1) {
          if (cache[i - 1][j] != -1) {
            cache[i][j] += cache[i - 1][j];
            cache[i][j] %= MOD;
          }
          if (cache[i][j - 1] != -1) {
            cache[i][j] += cache[i][j - 1];
            cache[i][j] %= MOD;
          }
        }
      }
    }

    return cache[m][n];
  }
}
