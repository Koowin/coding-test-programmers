/*
    https://programmers.co.kr/learn/courses/30/lessons/43238#
    입국심사
 */

package three;

import java.util.Arrays;

public class S43238 {
    int[] times;
    long n;

    public long solution(int n, int[] times) {
        this.n = n;
        this.times = times;
        Arrays.sort(times);
        int maxTime = times[times.length-1];
        long upLimit = (long) maxTime * (long)(n / times.length + 1);

        return binarySearch(0, upLimit + 1);
    }

    private long binarySearch(long lo, long hi) {
        if (hi - lo < 4) {
            for (long i = lo; i <hi;i++) {
                if(canJudgeInTime(i)) {
                    return i;
                }
            }
        }

        long mid = (lo + hi) / 2;
        if(canJudgeInTime(mid)) {
            return binarySearch(lo, mid + 1);
        }
        else {
            return binarySearch(mid, hi);
        }
    }

    private boolean canJudgeInTime(long time) {
        long count = 0;
        for (int i : times) {
            count += time / i;
            if (count >= n) {
                return true;
            }
        }
        return false;
    }
}
