package two;

import java.util.*;

public class S92342 {
    public int[] solution(int n, int[] info) {
        ScoreMaxer scoreMaxer = new ScoreMaxer(n, info);
        scoreMaxer.calculateArrowValues();
        scoreMaxer.findAnswer();
        return scoreMaxer.getAnswer();
    }
}


class ScoreMaxer{
    private static final int SCORE_ARRAY_SIZE = 11;
    private final int sumOfArrows;

    private final int[] apeachArrows;

    private int[] answer = new int[SCORE_ARRAY_SIZE];
    private int arrowCount = 0;

    private ArrowValue[] arrowValueList = new ArrowValue[SCORE_ARRAY_SIZE];

    public ScoreMaxer(int n, int[] info){
        sumOfArrows = n;
        apeachArrows = info;
    }

    public void calculateArrowValues(){
        int MAX_SCORE = 10;
        for(int i=0;i<SCORE_ARRAY_SIZE;i++){
            int score = MAX_SCORE - i;
            int requiredArrow = apeachArrows[i] + 1;
            arrowValueList[i] = new ArrowValue(score, requiredArrow);
        }
    }

    public void findAnswer(){
        int MAX_SCORE = 10;

        Arrays.sort(arrowValueList);

        for(ArrowValue value : arrowValueList){
            if(arrowCount + value.requiredArrow <= sumOfArrows){
                int index = MAX_SCORE - value.score;
                answer[index] = value.requiredArrow;
                arrowCount += value.requiredArrow;
            }
        }

        System.out.print("[");
        for(int i : answer){
            System.out.print(i + ",");
        }
        System.out.println("\b]");
        if(isLionWin()){
            remainArrowAdder();
        }
        else{
            answer = new int[] {-1};
        }
    }

    private boolean isLionWin(){
        int MAX_SCORE = 10;
        int scoreDiff = 0;

        for(int i=0;i<SCORE_ARRAY_SIZE;i++){
            if(answer[i] > apeachArrows[i]){
                scoreDiff += ((MAX_SCORE-i) * 2);
            }
            else if(apeachArrows[i] != 0){
                scoreDiff -= ((MAX_SCORE-i) * 2);
            }
        }
        if(scoreDiff > 0){
            return true;
        }
        else{
            return false;
        }
    }

    private void remainArrowAdder(){
        if(arrowCount < sumOfArrows){
            answer[10] += (sumOfArrows - arrowCount);
        }
    }

    public int[] getAnswer(){
        return answer;
    }

    static class ArrowValue implements Comparable<ArrowValue>{
        private final int score;
        private final int requiredArrow;
        private final double arrowValue;

        private ArrowValue(int s, int n){
            score = s;
            requiredArrow = n;
            if(requiredArrow == 1){
                arrowValue = (double) s / n;
            }
            else {
                arrowValue = ((double) s * 2) / n;
            }
        }

        @Override
        public int compareTo(ArrowValue o){
            if(arrowValue == o.arrowValue){
                return Integer.compare(score, o.score);
            }
            return Double.compare(o.arrowValue, arrowValue);
        }
    }
}
