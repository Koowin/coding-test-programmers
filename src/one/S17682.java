/*
    [1차] 다트 게임
    https://programmers.co.kr/learn/courses/30/lessons/17682
 */

package one;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S17682 {
    public int solution(String dartResult) {
        Pattern pattern = Pattern.compile("\\d+[SDT][*#]?");
        Matcher matcher = pattern.matcher(dartResult);
        for (int i = 0; i < 3; i++) {
            matcher.find();
            Score.scores[i] = new Score(matcher.group(), i);
        }
        int answer = 0;
        for (Score s : Score.scores) {
            answer += s.score;
        }
        return answer;
    }

    static class Score {
        private static Score[] scores = new Score[3];
        private int score;

        private Score(String str, int index) {
            int i = 1;
            if (str.charAt(i) == '0') {
                i++;
            }
            score = Integer.parseInt(str.substring(0, i));
            multipleScore(str.charAt(i++));
            if (i < str.length()) {
                calculateAward(str.charAt(i), index);
            }
        }

        private void multipleScore(char c) {
            if (c == 'D') {
                score *= score;
            } else if (c == 'T') {
                score = score * score * score;
            }
        }

        private void calculateAward(char c, int index) {
            if (c == '#') {
                score *= -1;
            } else {
                score *= 2;
                if (index > 0) {
                    scores[index-1].score *= 2;
                }
            }
        }
    }
}
