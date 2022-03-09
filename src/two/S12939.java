/*
    최댓값과 최솟값
    https://programmers.co.kr/learn/courses/30/lessons/12939
 */
package two;

public class S12939 {
    public String solution(String s) {
        String[] arr = s.split(" ");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(String str : arr){
            int n = Integer.parseInt(str);
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        String answer = Integer.toString(min) + " " + Integer.toString(max);

        return answer;
    }
}
