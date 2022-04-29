/*
    기능개발
    https://programmers.co.kr/learn/courses/30/lessons/42586
 */

package two;

import java.util.*;

public class S42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] days = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            days[i] = getDay(progresses[i], speeds[i]);
        }
        List<Integer> answer = new ArrayList<>();
        int count = 0;
        int max = days[0];
        for (int day : days) {
            if (day > max) {
                answer.add(count);
                max = day;
                count = 0;
            }
            count++;
        }
        answer.add(count);
        return answer.stream().mapToInt(o -> o).toArray();
    }

    private int getDay(int progress, int speed) {
        int left = 100 - progress;
        int day = left / speed;
        if (left % speed != 0) {
            day++;
        }
        return day;
    }
}