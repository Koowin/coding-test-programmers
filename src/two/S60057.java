/*
    문자열 압축
    https://programmers.co.kr/learn/courses/30/lessons/60057
 */
package two;

public class S60057 {
    public int solution(String s) {
        int minLength = s.length();
        for (int i = 1, size = s.length() / 2; i <= size; i++) {
            minLength = Math.min(minLength, getCompressedSize(s, i));
        }
        return minLength;
    }

    private static int getCompressedSize(String s, int unit) {
        int len = s.length();

        int ret = s.length() % unit;

        String last = s.substring(0, unit);
        int count = 1;
        for (int i = unit; i <= len - unit; i += unit) {
            String temp = s.substring(i, i + unit);
            if (temp.equals(last)) {
                count++;
            } else {
                ret += unit;
                ret += getNumberLength(count);
                last = temp;
                count = 1;
            }
        }
        ret += unit;
        ret += getNumberLength(count);
        return ret;
    }

    private static int getNumberLength(int n) {
        int ret = 0;
        while (n > 0) {
            n /= 10;
            ret++;
        }
        return ret;
    }
}
