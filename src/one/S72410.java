/*
    신규 아이디 추천
    https://programmers.co.kr/learn/courses/30/lessons/72410
    
    정규식을 알지 못할 때 풀이한 것. 수정 필요
 */

package one;

public class S72410 {
    public String solution(String new_id) {
        StringBuilder stringBuilder = new StringBuilder();
        int len = new_id.length();
        for (int i = 0; i < len; i++) {
            char ch = new_id.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                stringBuilder.append((char) (ch + 32));
                continue;
            }
            if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) {
                stringBuilder.append(ch);
                continue;
            }
            if ((ch == '-') || (ch == '_')) {
                stringBuilder.append(ch);
                continue;
            }
            if (ch == '.') {
                if (stringBuilder.length() == 0) {
                    continue;
                } else if (stringBuilder.charAt(stringBuilder.length() - 1) == '.') {
                    continue;
                } else {
                    stringBuilder.append(ch);
                }
            }
        }


        if (stringBuilder.length() > 15) {
            stringBuilder.delete(15, stringBuilder.length());
        }

        int j = stringBuilder.length() - 1;
        for (; j > 0; j--) {
            if (stringBuilder.charAt(j) != '.') {
                break;
            }
        }
        stringBuilder.delete(j + 1, stringBuilder.length());

        int len2 = stringBuilder.length();
        if (len2 == 0) {
            stringBuilder.append("aaa");
        } else if (len2 < 3) {
            char lastChar = stringBuilder.charAt(len2 - 1);
            for (int i = 0; i < (3 - len2); i++) {
                stringBuilder.append(lastChar);
            }
        }

        return stringBuilder.toString();
    }
}
