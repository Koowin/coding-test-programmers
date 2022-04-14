/*
    https://programmers.co.kr/learn/courses/30/lessons/64062
    징검다리 건너기
 */
package three;

public class S64062 {
    private int[] stones;
    private int k;
    public int solution(int[] stones, int k) {
        this.stones = stones;
        this.k = k;
        int lo = 1;
        int hi = 200_000_000;
        return binarySearch(lo, hi);
    }

    private int binarySearch(int lo, int hi) {
        if (hi <= lo) {
            return lo;
        }
        int mid = (lo + hi) / 2;
        if(canCrossRiver(mid)) {
            return binarySearch(mid + 1, hi);
        } else {
            return binarySearch(lo, mid);
        }
    }

    private boolean canCrossRiver(int n) {
        int broken = 0;
        for(int i : stones) {
            if(i <= n) {
                broken++;
                if(broken == k) {
                    return false;
                }
            } else {
                broken = 0;
            }
        }
        return true;
    }
}
