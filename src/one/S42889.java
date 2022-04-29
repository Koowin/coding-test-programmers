/*
    실패율
    https://programmers.co.kr/learn/courses/30/lessons/42889
 */

package one;

import java.util.*;

public class S42889 {
    public int[] solution(int N, int[] stages) {
        int[] failCount = new int[N + 2];
        for (int i : stages) {
            failCount[i]++;
        }

        List<Stage> stageList = new ArrayList<>();
        int sum = failCount[N + 1];
        for (int i = N; i > 0; i--) {
            sum += failCount[i];
            stageList.add(new Stage(i, failCount[i], sum));
        }
        Collections.sort(stageList);
        return stageList.stream().mapToInt(o -> o.index).toArray();
    }

    static class Stage implements Comparable<Stage> {
        private final int index;
        private final double rate;

        private Stage(int index, int failUsers, int totalUsers) {
            this.index = index;
            if (totalUsers == 0) {
                rate = 0;
            } else {
                rate = (double) failUsers / totalUsers;
            }
        }

        @Override
        public int compareTo(Stage o) {
            return rate != o.rate ? Double.compare(o.rate, rate) : Integer.compare(index, o.index);
        }
    }
}
