/*
    스타 수열
    https://programmers.co.kr/learn/courses/30/lessons/70130
 */
package three;

import java.util.*;

public class S70130 {
    public static void main(String[] args) {
        S70130 s = new S70130();
        int[] input = {2, 1, 1, 3, 4, 1, 1, 5};
        System.out.println(s.solution(input));
    }
    private int[] numArray;

    public int solution(int[] a) {
        numArray = a;
        Map<Integer, Number> numberMap = countNumber(a);
        List<Number> numList = new ArrayList<>(numberMap.values());

        return getMaxStarSequenceLength(numList);
    }

    private Map<Integer, Number> countNumber(int[] a) {
        Map<Integer, Number> numberMap = new HashMap<>();
        for (int i : a) {
            Number n = numberMap.get(i);
            if (n == null) {
                n = new Number(i);
                numberMap.put(i, n);
            }
            n.count++;
        }
        return numberMap;
    }

    private int getMaxStarSequenceLength(List<Number> numList) {
        Collections.sort(numList);
        int ret = 0;
        for (Number n : numList) {
            ret = Math.max(ret, countStarSequenceLength(n.num));
            if (n.count < ret) {
                break;
            }
        }
        return ret * 2;
    }

    private int countStarSequenceLength(int n) {
        int count = 0;
        int last = -1;

        if (numArray[0] == n && numArray.length > 1 && numArray[1] != n) {
            count++;
            last = 1;
        }
        for (int i = 1; i < numArray.length - 1; i++) {
            if (numArray[i] == n) {
                if (i - 1 > last && numArray[i - 1] != n) {
                    count++;
                    last = i;
                } else if (numArray[i + 1] != n) {
                    count++;
                    last = i + 1;
                }
            }
        }
        if (numArray[numArray.length - 1] == n && last < numArray.length - 2 && numArray[numArray.length - 2] != n) {
            count++;
        }
        return count;
    }

    private static class Number implements Comparable<Number> {
        private final int num;
        private int count = 0;

        private Number(int num) {
            this.num = num;
        }

        @Override
        public int compareTo(Number o) {
            return Integer.compare(o.count, count);
        }
    }
}
