/*
    멀쩡한 사각형
    https://programmers.co.kr/learn/courses/30/lessons/62048
 */

package two;

public class S62048 {
    public long solution(int w, int h) {
        int gcd = gcd(w, h);
        int mw = w / gcd;
        int hw = h / gcd;

        long answer = (long) w * (h - hw);
        answer += (long) gcd * (mw - 1) * (hw - 1);
        return answer;
    }

    private int gcd(int a, int b) {
        int r = a % b;
        if (r == 0) {
            return b;
        }
        return gcd(b, r);
    }
}
