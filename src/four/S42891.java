/*
    무지의 먹방 라이브
    https://programmers.co.kr/learn/courses/30/lessons/42891
 */
package four;

import java.util.*;

public class S42891 {
    public static void main(String[] args) {
        S42891 s = new S42891();
        System.out.println(s.solution(new int[] {3,1,2}, 6));
    }
    private List<Food> foodList = new ArrayList<>();
    private int[] sortedFoodAmount;
    private int index = 0;

    public int solution(int[] food_times, long k) {
        for (int i = 0; i < food_times.length; i++) {
            foodList.add(new Food(i + 1, food_times[i]));
        }
        sortedFoodAmount = food_times;
        Arrays.sort(sortedFoodAmount);
        return eat(0, k);
    }

    private int eat(int minSum, long k) {
        int size = sortedFoodAmount.length - index;
        int min = sortedFoodAmount[index] - minSum;
        minSum = sortedFoodAmount[index];

        long l = (long) min * size;
        if (l <= k) {
            for (index++; index < sortedFoodAmount.length; index++) {
                if (sortedFoodAmount[index] > minSum) {
                    break;
                }
            }
            if (index == sortedFoodAmount.length) {
                return -1;
            }
            return eat(minSum,k - l);
        }
        int target = (int) (k % size) + 1;
        int ret = 0;
        for (Food f : foodList) {
            if (f.amount >= minSum) {
                if (--target == 0) {
                    ret = f.index;
                    break;
                }
            }
        }
        return ret;
    }

    static class Food {
        private final int index;
        private int amount;

        private Food(int index, int amount) {
            this.index = index;
            this.amount = amount;
        }
    }
}
