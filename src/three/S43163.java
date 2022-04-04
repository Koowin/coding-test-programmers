/*
    https://programmers.co.kr/learn/courses/30/lessons/43163
    단어 변환
 */
package three;

import java.util.*;

public class S43163 {
    private String[] words;
    private int targetIndex;
    private boolean[] isUsed;
    Queue<String> queue = new LinkedList<>();
    private int answer = 0;

    private static boolean canChange(String from, String to) {
        int length = from.length();
        int diffCount = 0;

        for (int i = 0; i < length; i++) {
            if (from.charAt(i) != to.charAt(i)) {
                diffCount++;
            }
        }

        if (diffCount == 1) {
            return true;
        }
        return false;
    }

    public int solution(String begin, String target, String[] words) {
        this.words = words;
        targetIndex = getTargetIndex(target);
        if (targetIndex == -1) {
            return 0;
        }

        isUsed = new boolean[words.length];
        queue.offer(begin);

        bfs();

        if (isUsed[targetIndex]) {
            return answer;
        }
        return 0;
    }

    private int getTargetIndex(String target) {
        for (int i = 0; i < words.length; i++) {
            if (target.equals(words[i])) {
                return i;
            }
        }
        return -1;
    }

    private void bfs() {
        answer++;
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            String now = queue.poll();

            for (int j = 0; j < words.length; j++) {
                if (!isUsed[j] && canChange(now, words[j])) {
                    queue.offer(words[j]);
                    isUsed[j] = true;
                }
            }
        }

        if (queue.isEmpty() || isUsed[targetIndex]) {
            return;
        }
        bfs();
    }

}