/*
    [1차] 다트 게임
    https://programmers.co.kr/learn/courses/30/lessons/17682
 */

package one;

public class S17682 {
    public int solution(String dartResult) {
        int[] scores = new int[4];
        String scoreString = dartResult.replaceAll("[#*]", "");
        String[] onlyNumbers = scoreString.split("[SDT]");
        String[] allStringArr = dartResult.split("");

        int i = 1;
        for (String num : onlyNumbers) {
            scores[i] = Integer.parseInt(num);
            i++;
        }

        i = 0;
        for (String ch : allStringArr) {
            if (ch.matches("[SDT]")) {
                i++;
                if (ch.equals("D")) {
                    scores[i] = scores[i] * scores[i];
                } else if (ch.equals("T")) {
                    scores[i] = scores[i] * scores[i] * scores[i];
                }
            }
            if (ch.matches("[*#]")) {
                if (ch.equals("*")) {
                    scores[i - 1] = scores[i - 1] * 2;
                    scores[i] = scores[i] * 2;
                } else {
                    scores[i] = scores[i] * (-1);
                }
            }
        }

        return scores[1] + scores[2] + scores[3];

    }
}
