/*
    https://programmers.co.kr/learn/courses/30/lessons/72414
    광고 삽입
 */
package three;

import java.util.*;

public class S72414 {
    private static final int SEC_PER_HOUR = 3600;
    private static final int SEC_PER_MINUTE = 60;

    private int[] overlapCount;

    private int endOfPlayTime;
    private int advertiseRunTime;

    public String solution(String play_time, String adv_time, String[] logs) {
        endOfPlayTime = parseStringToIntTime(play_time);
        advertiseRunTime = parseStringToIntTime(adv_time);
        overlapCount = new int[endOfPlayTime + 1];

        for (String log : logs) {
            String[] arr = log.split("-");
            int startTime = parseStringToIntTime(arr[0]);
            int endTime = parseStringToIntTime(arr[1]);
            addOverlapCount(startTime, endTime);
        }

        int bestAdvertiseTime = getBestAdvertiseTime();

        return parseIntToStringTime(bestAdvertiseTime);
    }

    private static int parseStringToIntTime(String strTime) {
        int[] timeArr = Arrays.stream(strTime.split(":")).mapToInt(Integer::parseInt).toArray();
        int ret = 0;

        ret += timeArr[0] * SEC_PER_HOUR;
        ret += timeArr[1] * SEC_PER_MINUTE;
        ret += timeArr[2];
        return ret;
    }

    private static String parseIntToStringTime(int intTime) {
        int hour = intTime / SEC_PER_HOUR;
        intTime %= SEC_PER_HOUR;
        int minute = intTime / SEC_PER_MINUTE;
        int second = intTime % SEC_PER_MINUTE;
        String format = "%02d:%02d:%02d";
        return String.format(format, hour, minute, second);
    }

    private void addOverlapCount(int start, int end) {
        for (int i = start; i < end; i++) {
            overlapCount[i]++;
        }
    }

    private int getBestAdvertiseTime() {
        int bestTime = 0;

        long sum = 0;
        for (int i = 0; i < advertiseRunTime; i++) {
            sum += overlapCount[i];
        }

        long max = sum;

        for (int i = 0, j = advertiseRunTime; j < endOfPlayTime; i++, j++) {
            sum += overlapCount[j];
            sum -= overlapCount[i];
            if (sum > max) {
                max = sum;
                bestTime = i + 1;
            }
        }
        return bestTime;
    }
}