/*
    [1차] 뉴스 클러스터링
    https://programmers.co.kr/learn/courses/30/lessons/17677
 */

package two;

import java.util.*;

public class S17677 {
    public static void main(String[] args) {
        S17677 s = new S17677();
        String str1 = "handshake";
        String str2 = "shake hands";
        System.out.println(s.solution(str1, str2));
    }

    public int solution(String str1, String str2) {
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        String[] arr1 = str1.split("[^A-Z]");
        String[] arr2 = str2.split("[^A-Z]");

        JacquardSet s1 = new JacquardSet();
        JacquardSet s2 = new JacquardSet();
        for (String str : arr1) {
            s1.add(str);
        }
        for (String str : arr2) {
            s2.add(str);
        }

        int intersectionCount = JacquardSet.intersectionCount(s1, s2);
        int unionCount = s1.count + s2.count - intersectionCount;
        if (unionCount == 0) {
            return 65536;
        }
        return intersectionCount * 65536 / unionCount;
    }

    static class JacquardSet {
        private Map<String, Integer> map = new HashMap<>();
        private int count = 0;

        private void add(String str) {
            if (str.length() < 2) {
                return;
            }
            for (int i = 0; i < str.length() - 1; i++) {
                String key = str.substring(i, i + 2);
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            count += str.length() - 1;
        }

        private static int intersectionCount(JacquardSet s1, JacquardSet s2) {
            int count = 0;
            for (Map.Entry<String, Integer> entry : s1.map.entrySet()) {
                int i = s2.map.getOrDefault(entry.getKey(), 0);
                count += Math.min(i, entry.getValue());
            }
            return count;
        }
    }
}

