/*
    숫자 문자열과 영단어
    https://programmers.co.kr/learn/courses/30/lessons/81301
 */
package one;

public class S81301 {
    public int solution(String s) {
        s = s.replace("zero", "0");
        s = s.replace("one", "1");
        s = s.replace("two", "2");
        s = s.replace("three", "3");
        s = s.replace("four", "4");
        s = s.replace("five", "5");
        s = s.replace("six", "6");
        s = s.replace("seven", "7");
        s = s.replace("eight", "8");
        s = s.replace("nine", "9");
        return Integer.parseInt(s);
    }
}
