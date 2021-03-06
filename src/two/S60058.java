/*
    괄호 변환
    https://programmers.co.kr/learn/courses/30/lessons/60058
 */
package two;

public class S60058 {
    private static char OPEN_BRACKET = '(';
    private static char CLOSE_BRACKET = ')';

    public String solution(String p) {
        return makeBracket(p);
    }

    private String makeBracket(String original) {
        if (original.length() == 0) {
            return original;
        }
        int balanceIndex = getBalanceIndex(original);
        String u = original.substring(0, balanceIndex);
        String v = original.substring(balanceIndex);

        if (isRightBracket(u)) {
            return u + makeBracket(v);
        }
        StringBuilder ret = new StringBuilder();
        ret.append(OPEN_BRACKET);
        ret.append(makeBracket(v));
        ret.append(CLOSE_BRACKET);

        StringBuilder uStringBuilder = new StringBuilder(u.substring(1, u.length() - 1));
        for (int i = 0; i < uStringBuilder.length(); i++) {
            if (uStringBuilder.charAt(i) == OPEN_BRACKET) {
                uStringBuilder.setCharAt(i, CLOSE_BRACKET);
            } else {
                uStringBuilder.setCharAt(i, OPEN_BRACKET);
            }
        }
        ret.append(uStringBuilder);
        return ret.toString();
    }

    private int getBalanceIndex(String original) {
        int sum;
        if (original.charAt(0) == OPEN_BRACKET) {
            sum = 1;
        } else {
            sum = -1;
        }
        int i = 1;
        while (sum != 0) {
            if (original.charAt(i++) == OPEN_BRACKET) {
                sum += 1;
            } else {
                sum -= 1;
            }
        }
        return i;
    }

    private boolean isRightBracket(String str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == OPEN_BRACKET) {
                sum += 1;
            } else {
                sum -= 1;
            }
            if (sum < 0) {
                return false;
            }
        }
        return true;
    }
}
