/*
    정수 제곱근 판별
    https://programmers.co.kr/learn/courses/30/lessons/12934
 */

package one;

public class S12934 {
    public long solution(long n) {
        Double d = Math.sqrt(n);
        if (d == d.intValue()) {
            return (long) Math.pow(d + 1, 2);
        } else {
            return -1;
        }
    }
}
