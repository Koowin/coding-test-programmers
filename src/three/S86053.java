/*
    https://programmers.co.kr/learn/courses/30/lessons/86053
    금과 은 운반하기
 */
package three;

public class S86053 {
    private int needGold;
    private int needSilver;
    private int sum;

    private int[] goldList;
    private int[] silverList;
    private int[] sumList;
    private int[] weightList;
    private int[] timeList;

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        needGold = a;
        needSilver = b;
        sum = a + b;
        goldList = g;
        silverList = s;
        weightList = w;
        timeList = t;
        sumList = new int[g.length];
        for (int i = 0; i < sumList.length; i++) {
            sumList[i] = g[i] + s[i];
        }
        long max = (long) 4e14;
        return getMinBuildableTime(0, max);
    }

    private long getMinBuildableTime(long lo, long hi) {
        if (lo >= hi) {
            return lo;
        }
        long mid = (lo + hi) / 2;
        if (canBuildInTime(mid)) {
            return getMinBuildableTime(lo, mid);
        } else {
            return getMinBuildableTime(mid + 1, hi);
        }
    }

    private boolean canBuildInTime(long time) {
        long goldSum = 0;
        long silverSum = 0;
        long totalSum = 0;
        for (int i = 0; i < timeList.length; i++) {
            long count = getMovableCount(time, timeList[i]);
            long max = count * weightList[i];
            goldSum += Math.min(max, goldList[i]);
            silverSum += Math.min(max, silverList[i]);
            totalSum += Math.min(max, sumList[i]);
        }
        if (totalSum < sum || goldSum < needGold || silverSum < needSilver) {
            return false;
        }
        return true;
    }

    private static long getMovableCount(long time, int unit) {
        time += unit;
        unit *= 2;
        return time / unit;
    }
}
