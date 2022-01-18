/*
    이상한 문자 만들기
    https://programmers.co.kr/learn/courses/30/lessons/12930
 */

package one;

public class S12930 {
    public String solution(String s) {
        String answer = "";
        char[] arr = s.toCharArray();
        int c = 0;
        for (int i = 0, size = arr.length; i < size; i++) {
            if (arr[i] == ' ') {
                answer += ' ';
                c = 0;
            } else {
                if (c % 2 == 0) {
                    answer += Character.toUpperCase(arr[i]);
                } else {
                    answer += Character.toLowerCase(arr[i]);
                }
                c++;
            }
        }
        return answer;
    }
}
