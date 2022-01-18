/*
    문자열 내 마음대로 정렬하기
    https://programmers.co.kr/learn/courses/30/lessons/12915
 */

package one;

import java.util.*;

public class S12915 {
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int c = Character.compare(o1.charAt(n), o2.charAt(n));
                if (c == 0) {
                    return o1.compareTo(o2);
                } else {
                    return c;
                }
            }
        });

        return strings;
    }
}
