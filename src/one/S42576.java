/*
    완주하지 못한 선수
    https://programmers.co.kr/learn/courses/30/lessons/42576
 */

package one;

import java.util.*;

public class S42576 {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String name : participant) {
            Integer beforeValue = map.put(name, 1);
            if (beforeValue != null) {
                map.put(name, beforeValue + 1);
            }
        }

        for (String name : completion) {
            Integer value = map.get(name);
            if (value == 1) {
                map.remove(name);
            } else {
                map.put(name, value - 1);
            }
        }

        for (String name : map.keySet()) {
            return name;
        }
        return "";
    }
}
