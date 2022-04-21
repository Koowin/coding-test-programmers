/*
    로또의 최고 순위와 최저 순위
    https://programmers.co.kr/learn/courses/30/lessons/77484
 */

package one;

import java.util.*;

public class S77484 {
    public int[] solution(int[] lottos, int[] win_nums) {
        final int[] hitRank = {6, 6, 5, 4, 3, 2, 1};

        Set<Integer> lottoNumbers = new HashSet<>();
        for (int i : win_nums) {
            lottoNumbers.add(i);
        }

        int zeroCount = 0;
        int hitCount = 0;
        for (int i : lottos) {
            if (i == 0) {
                zeroCount++;
            } else if (lottoNumbers.contains(i)) {
                hitCount++;
            }
        }

        int[] answer = new int[2];
        answer[0] = hitRank[zeroCount + hitCount];
        answer[1] = hitRank[hitCount];
        return answer;
    }
}
