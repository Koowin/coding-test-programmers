/*
    올바른 괄호의 갯수
    https://programmers.co.kr/learn/courses/30/lessons/12929
 */
package four;

public class S12929 {
    int[][] cache;

    public int solution(int n) {
        cache = new int[n + 1][n + 1];
        return count(n, n);
    }

    private int count(int open, int close) {
        if (open == 0) {
            cache[0][close] = 1;
            return 1;
        }
        if (cache[open][close] != 0) {
            return cache[open][close];
        }
        int ret = 0;
        if (open < close) {
            ret += count(open, close - 1);
        }
        ret += count(open - 1, close);
        cache[open][close] = ret;
        return ret;
    }
}
