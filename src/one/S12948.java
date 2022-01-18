/*
    핸드폰 번호 가리기
    https://programmers.co.kr/learn/courses/30/lessons/12948
 */

package one;

public class S12948 {
    public String solution(String phone_number) {
        String answer = "";
        for (int i = 0, size = phone_number.length() - 4; i < size; i++) {
            answer += "*";
        }
        answer += phone_number.substring(phone_number.length() - 4);
        return answer;
    }
}
