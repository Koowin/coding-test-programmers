/*
    두개 뽑아서 더하기
    https://programmers.co.kr/learn/courses/30/lessons/68644
 */

package one;

import java.util.*;

public class S68644 {
    public int[] solution(int[] numbers) {
        boolean[] answerChecker = new boolean[201];

        for (int i = 0, size = numbers.length; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                answerChecker[numbers[i] + numbers[j]] = true;
            }
        }

        ArrayList<Integer> answerArrayList = new ArrayList<>();
        for (int i = 0, size = answerChecker.length; i < size; i++) {
            if (answerChecker[i]) {
                answerArrayList.add(i);
            }
        }
        return answerArrayList.stream().mapToInt(i -> i).toArray();
    }
}
