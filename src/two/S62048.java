/*
    멀쩡한 사각형
    https://programmers.co.kr/learn/courses/30/lessons/62048
 */

package two;

public class S62048 {
    public long solution(int w, int h) {
        int gcd;
        if (w > h) {
            gcd = getGCD(w, h);
        } else {
            gcd = getGCD(h, w);
        }
        int mw = w / gcd;
        int mh = h / gcd;

        long ret = (long) w * h - (long) gcd * (mw + mh - 1);
        return ret;
    }

    private int getGCD(int B, int S) {
        int r = B % S;
        if (r == 0) {
            return S;
        }
        return getGCD(S, r);
    }
}
