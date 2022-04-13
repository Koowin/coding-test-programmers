/*
    https://programmers.co.kr/learn/courses/30/lessons/67258
    보석 쇼핑
 */
package three;

import java.util.*;

public class S67258 {
    public int[] solution(String[] gems) {
        ShoppingListFinder finder = new ShoppingListFinder(gems);
        return finder.getMinShoppingList();
    }
}

class ShoppingListFinder {
    Sack sack;
    private int[] gemList;

    public ShoppingListFinder(String[] gems) {
        initGemList(gems);
    }

    private void initGemList(String[] gems) {
        gemList = new int[gems.length];
        Map<String, Integer> map = new HashMap<>();
        int nextIndex = 0;

        for (int i = 0; i < gems.length; i++) {
            Integer value = map.get(gems[i]);
            if (value == null) {
                value = nextIndex++;
                map.put(gems[i], value);
            }
            gemList[i] = value;
        }
        sack = new Sack(nextIndex);
    }

    public int[] getMinShoppingList() {
        int[] ret = new int[2];
        int min = Integer.MAX_VALUE;

        int lo = 0;
        int hi = 0;

        while (!sack.isFull()) {
            sack.addGem(gemList[hi]);
            hi++;
        }

        while (true) {
            while (sack.isFull()) {
                sack.removeGem(gemList[lo]);
                lo++;
            }
            int len = hi - lo;
            if (len < min) {
                min = len;
                ret[0] = lo;
                ret[1] = hi;
            }
            while (hi < gemList.length && !sack.isFull()) {
                sack.addGem(gemList[hi]);
                hi++;
            }

            if (hi == gemList.length && !sack.isFull()) {
                break;
            }
        }
        return ret;
    }

    static class Sack {
        private int[] count;
        private Set<Integer> lack = new HashSet<>();

        private Sack(int size) {
            count = new int[size];
            for (int i = 0; i < size; i++) {
                lack.add(i);
            }
        }

        private void addGem(int gem) {
            if (count[gem] == 0) {
                lack.remove(gem);
            }
            count[gem]++;
        }

        private void removeGem(int gem) {
            count[gem]--;
            if (count[gem] == 0) {
                lack.add(gem);
            }
        }

        private boolean isFull() {
            return lack.isEmpty();
        }
    }
}