/*
    음양 더하기
    https://programmers.co.kr/learn/courses/30/lessons/76501
 */


package one;

public class S76501 {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        for (int i = 0, size = absolutes.length; i < size; i++) {
            if (signs[i]) {
                answer += absolutes[i];
            } else {
                answer -= absolutes[i];
            }
        }
        return answer;
    }
}
