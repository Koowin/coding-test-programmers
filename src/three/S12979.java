/*
    https://programmers.co.kr/learn/courses/30/lessons/12979
    기지국 설치
 */
package three;

public class S12979 {
    private int[] cache;
    private int coverLength;

    public int solution(int n, int[] stations, int w) {
        coverLength = w + w + 1;

        int adder = w + 1;
        int count = 0;
        int lo = 1;

        for (int s : stations) {
            int hi = s - w;
            count += minimumNeedBase(lo, hi);
            lo = s + adder;
        }
        count += minimumNeedBase(lo, n+1);
        return count;
    }

    private int minimumNeedBase(int lo, int hi) {
        if(hi <= lo){
            return 0;
        }
        int length = hi - lo;
        int ret = length / coverLength;
        if (length % coverLength != 0) {
            ret++;
        }
        return ret;
    }
}