/*
    단체사진 찍기
    https://programmers.co.kr/learn/courses/30/lessons/1835
 */
package two;

import java.util.*;

public class S1835 {

    private int[] sequence = new int[8];
    private List<Condition> conditions = new ArrayList<>();

    public int solution(int n, String[] data) {
        initMap();
        initCondition(data);

        return count(0,0);
    }

    private void initMap() {
        Condition.friendMap.put('A', 0);
        Condition.friendMap.put('C', 1);
        Condition.friendMap.put('F', 2);
        Condition.friendMap.put('J', 3);
        Condition.friendMap.put('M', 4);
        Condition.friendMap.put('N', 5);
        Condition.friendMap.put('R', 6);
        Condition.friendMap.put('T', 7);
    }

    private void initCondition(String[] data) {
        for (String str : data) {
            conditions.add(new Condition(str));
        }
    }

    private int count(int index, int usedSet) {
        if (index == 8) {
            for (Condition c : conditions) {
                if (!c.isMeet(sequence, usedSet)) {
                    return 0;
                }
            }
            return 1;
        }
        for (Condition c : conditions) {
            if (!c.isMeet(sequence, usedSet)) {
                return 0;
            }
        }
        int ret = 0;
        for (int i = 0, offset = 1; i < 8; i++, offset <<= 1) {
            if ((usedSet & offset) == 0) {
                sequence[index] = i;
                ret += count(index + 1, usedSet | offset);
            }
        }
        return ret;
    }

    static class Condition {
        private static Map<Character, Integer> friendMap = new HashMap<>();
        private static final int OFFSET = '0' - 1;
        private final int f1;
        private final int f2;
        private final int len;
        private final Type type;

        private Condition(String str) {
            f1 = friendMap.get(str.charAt(0));
            f2 = friendMap.get(str.charAt(2));
            len = str.charAt(4) - OFFSET;
            type = Type.findByChar(str.charAt(3));
        }

        private boolean isMeet(int[] sequence, int used) {
            if ((used & 1 << f1) == 0 || (used & 1 << f2) == 0) {
                return true;
            }
            int loc1 = findLoc(f1, sequence);
            int loc2 = findLoc(f2, sequence);
            return type.isMeet(Math.abs(loc1 - loc2), len);
        }

        private static int findLoc(int friend, int[] sequence) {
            int i = 0;
            for (; i < 8; i++) {
                if (sequence[i] == friend) {
                    break;
                }
            }
            return i;
        }

        enum Type {
            EQUAL {
                @Override
                public boolean isMeet(int a, int b) {
                    return a == b;
                }
            }, CLOSER {
                @Override
                public boolean isMeet(int a, int b) {
                    return a < b;
                }
            }, FAR {
                @Override
                public boolean isMeet(int a, int b) {
                    return a > b;
                }
            };

            public static Type findByChar(char c) {
                if (c == '=') {
                    return EQUAL;
                } else if (c == '<') {
                    return CLOSER;
                } else {
                    return FAR;
                }
            }

            public abstract boolean isMeet(int a, int b);
        }
    }
}
