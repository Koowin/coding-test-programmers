/*
    모의고사
    https://programmers.co.kr/learn/courses/30/lessons/42840
 */

package one;

public class S42840 {
    public int[] solution(int[] answers) {

        int[] n = new int[3];
        int[] array2 = {1, 3, 4, 5};
        int[] array3 = {3, 1, 2, 4, 5};

        for (int i = 0, size = answers.length; i < size; i++) {
            if (answers[i] == i % 5 + 1) {
                n[0]++;
            }
            if (i % 2 == 0) {
                if (answers[i] == 2)
                    n[1]++;
            } else if (answers[i] == array2[(i / 2) % 4]) {
                n[1]++;
            }
            if (answers[i] == array3[(i / 2) % 5]) {
                n[2]++;
            }
        }
        int max = n[0];
        if (n[1] > max)
            max = n[1];
        if (n[2] > max)
            max = n[2];
        int count = 0;
        for (int i : n) {
            if (i == max)
                count++;
        }
        int[] answer = new int[count];
        for (int i = 0, j = 0; i < 3; i++) {
            if (n[i] == max) {
                answer[j] = i + 1;
                j++;
            }
        }
        return answer;
    }
}
