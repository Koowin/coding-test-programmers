/*
    폰켓몬
    https://programmers.co.kr/learn/courses/30/lessons/1845
 */

package one;

import java.util.*;

public class S1845 {
    public int solution(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        for (int num : nums) {
            hs.add(num);
        }
        return Math.min(hs.size(), nums.length / 2);
    }
}
