/*
    영어 끝말잇기
    https://programmers.co.kr/learn/courses/30/lessons/12981
 */
package two;

import java.util.*;

public class S12981 {
    Set<String> wordSet = new HashSet<>();

    public int[] solution(int n, String[] words) {
        wordSet.add(words[0]);

        for(int i=1;i<words.length;i++){
            if(isNotMatch(words[i-1], words[i]) || isDuplicated(words[i])){
                return getAnswer(n, i);
            }
        }
        return new int[] {0, 0};
    }

    private boolean isNotMatch(String pre, String post){
        return (pre.charAt(pre.length() - 1) != post.charAt(0));
    }

    private boolean isDuplicated(String s){
        if(wordSet.contains(s)){
            return true;
        }
        wordSet.add(s);
        return false;
    }

    private int[] getAnswer(int n, int index){
        int[] answer = {index % n + 1, index / n + 1};
        return answer;
    }
}
