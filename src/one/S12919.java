/*
    서울에서 김서방 찾기
    https://programmers.co.kr/learn/courses/30/lessons/12919
 */

package one;

public class S12919 {
    public String solution(String[] seoul) {
        String format = "김서방은 %d에 있다";
        int answer = 0;
        for (String str : seoul) {
            if (str.equals("Kim")) {
                break;
            }
            answer++;
        }
        return String.format(format, answer);
    }
}
