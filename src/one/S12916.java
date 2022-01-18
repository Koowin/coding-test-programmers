/*
    문자열 내 p와 y의 개수
    https://programmers.co.kr/learn/courses/30/lessons/12916
 */
package one;

public class S12916 {
    boolean solution(String s) {
        int countP = 0;
        int countY = 0;
        s = s.toLowerCase();

        for (int i = 0, size = s.length(); i < size; i++) {
            if (s.charAt(i) == 'p') {
                countP++;
            } else if (s.charAt(i) == 'y') {
                countY++;
            }
        }

        if (countP == countY) {
            return true;
        } else {
            return false;
        }
    }
}
