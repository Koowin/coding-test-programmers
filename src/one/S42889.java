/*
    실패율
    https://programmers.co.kr/learn/courses/30/lessons/42889
 */

package one;

import java.util.*;

public class S42889 {
    public int[] solution(int N, int[] stages) {
        int[] stageChallengeCount = new int[N + 2];
        for (int stage : stages) {
            stageChallengeCount[stage]++;
        }
        ArrayList<StageAndFailRate> arr = new ArrayList<>();
        int totalCount = stageChallengeCount[N + 1];
        for (int i = N; i > 0; i--) {
            totalCount += stageChallengeCount[i];
            arr.add(new StageAndFailRate(i, (double) stageChallengeCount[i] / totalCount));
        }
        Collections.sort(arr);
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = arr.get(i).stage;
        }
        return answer;
    }

    class StageAndFailRate implements Comparable<StageAndFailRate> {
        private int stage;
        private double failRate;

        private StageAndFailRate(int stage, double failRate) {
            this.stage = stage;
            this.failRate = failRate;
        }

        @Override
        public int compareTo(StageAndFailRate o) {
            if (this.failRate < o.failRate) {
                return 1;
            } else if (this.failRate > o.failRate) {
                return -1;
            } else {
                if (this.stage > o.stage) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }
}
