/*
    https://programmers.co.kr/learn/courses/30/lessons/77486
    다단계 칫솔 판매
 */
package three;

import java.util.*;

public class S77486 {
    Map<String, Seller> sellers = new HashMap<>();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        sellers.put("-", new Seller());

        for (String str : enroll) {
            sellers.put(str, new Seller());
        }

        for (int i=0;i<enroll.length;i++) {
            Seller child = sellers.get(enroll[i]);
            Seller parent = sellers.get(referral[i]);
            child.setParent(parent);
        }

        int cost = 100;
        for (int i=0;i<seller.length;i++) {
            sellers.get(seller[i]).addIncome(amount[i] * cost);
        }

        int[] answer = new int[enroll.length];
        for (int i=0;i<enroll.length;i++) {
            answer[i] = sellers.get(enroll[i]).income;
        }
        return answer;
    }

    static class Seller {
        private static final int DIVIDER = 10;

        List<Seller> children = new ArrayList<>();
        private Seller parent;
        private int income = 0;

        private void setParent(Seller parent) {
            this.parent = parent;
        }

        private void addIncome(int n) {
            int fee = n / DIVIDER;
            income += n - fee;
            if (fee > 0 && parent != null) {
                parent.addIncome(fee);
            }
        }
    }
}
