/*
    징검다리
    https://programmers.co.kr/learn/courses/30/lessons/43236
 */
package four;

import java.util.*;

public class S43236 {
    private List<Integer> rocks = new ArrayList<>();
    private int stoneLimit;

    public int solution(int distance, int[] rocks, int n) {
        stoneLimit = rocks.length + 2 - n;
        initRocks(distance, rocks);
        return binarySearch(0, distance);
    }

    private void initRocks(int distance, int[] rocks) {
        for (int rock : rocks) {
            this.rocks.add(rock);
        }
        this.rocks.add(distance);
        Collections.sort(this.rocks);
    }


    private int binarySearch(int lo, int hi) {
        if (lo + 1 >= hi) {
            if (lo >= hi) {
                return lo;
            }
            if (countStone(hi) >= stoneLimit) {
                return hi;
            }
            return lo;
        }
        int mid = (lo + hi) / 2;
        if (countStone(mid) >= stoneLimit) {
            return binarySearch(mid, hi);
        } else {
            return binarySearch(lo, mid - 1);
        }
    }

    private int countStone(int distance) {
        int ret = 1;
        int last = 0;
        for (int i : rocks) {
            if (i - last >= distance) {
                last = i;
                ret++;
            }
        }
        return ret;
    }
}
