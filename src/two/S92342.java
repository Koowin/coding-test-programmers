/*
    양궁대회
    https://programmers.co.kr/learn/courses/30/lessons/92342
 */
package two;

import java.util.*;

public class S92342 {
    public int[] solution(int n, int[] info) {
        MaxScoreFinder msf = new MaxScoreFinder(n, info);
        return msf.getAnswer();
    }
}

class MaxScoreFinder {
    private final int MAX_ARROW;
    private final int LION_SCORE;

    private GettableScore[] scores = new GettableScore[11];
    private boolean[] isUsed = new boolean[11];

    private int maxScore = 0;
    private boolean[] maxScoreCombination = new boolean[11];

    public MaxScoreFinder(int n, int[] info) {
        MAX_ARROW = n;
        int lionScore = 0;

        for (int i = 0; i < 11; i++) {
            int score = 10 - i;
            if(info[i] > 0){
                lionScore -= score;
            }
            scores[i] = new GettableScore(info[i], score);
        }
        LION_SCORE = lionScore;
    }

    public int[] getAnswer(){
        dfs(10, LION_SCORE, MAX_ARROW);
        if(maxScore > 0){
            return makeArrowList();
        }
        else{
            return new int[] {-1};
        }
    }

    private void dfs(int index, int score, int remainArrow){
        if(index == -1){
            if(maxScore < score) {
                maxScore = score;
                maxScoreCombination = Arrays.copyOf(isUsed, isUsed.length);
            }
            return;
        }

        if(remainArrow >= scores[index].needArrow) {
            isUsed[index] = true;
            dfs(index - 1, score + scores[index].score, remainArrow - scores[index].needArrow);
            isUsed[index] = false;
        }
        dfs(index - 1, score, remainArrow);
    }

    private int[] makeArrowList(){
        int[] ret = new int[11];
        int arrowCount = 0;

        for(int i=0;i<11;i++){
            if(maxScoreCombination[i]){
                arrowCount += scores[i].needArrow;
                ret[i] = scores[i].needArrow;
            }
        }
        if(arrowCount < MAX_ARROW){
            ret[10] += (MAX_ARROW - arrowCount);
        }
        return ret;
    }

    static class GettableScore{
        private final int needArrow;
        private final int score;

        private GettableScore(int apeachArrow, int score){
            needArrow = apeachArrow + 1;
            if(apeachArrow != 0){
                score *= 2;
            }
            this.score = score;
        }
    }
}