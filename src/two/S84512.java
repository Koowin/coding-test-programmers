/*
    모음사전
    https://programmers.co.kr/learn/courses/30/lessons/84512
 */
package two;

public class S84512 {
    public int solution(String word) {
        int[] cost = new int[5];
        String alp = "AEIOU";
        cost[4] = 1;
        for(int i=3;i>=0;i--){
            cost[i] = cost[i+1] * 5 + 1;
        }

        int answer = word.length();
        for(int i=0;i<word.length();i++){
            answer += (alp.indexOf(word.charAt(i)) * cost[i]);
        }
        return answer;
    }
}
