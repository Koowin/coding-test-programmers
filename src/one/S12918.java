/*
    문자열 다루기 기본
    https://programmers.co.kr/learn/courses/30/lessons/12918
 */

package one;

public class S12918 {
    public boolean solution(String s) {
        return s.matches("(\\d){4}|{6}");
    }
}
