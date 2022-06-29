/*
    https://programmers.co.kr/learn/courses/30/lessons/77486
    다단계 칫솔 판매
 */
package three;

import java.util.*;

public class S77486 {
    private Map<String, Person> map = new LinkedHashMap<>();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        initMap(enroll, referral);

        for(int i=0;i<seller.length;i++){
            Person p = map.get(seller[i]);
            p.addIncome(amount[i] * 100);
        }

        map.remove("-");
        int[] answer = map.values().stream().mapToInt(Person::getIncome).toArray();

        return answer;
    }

    private void initMap(String[] enroll, String[] referral){
        map.put("-", new Person(null));

        for(int i=0;i<enroll.length;i++){
            Person p = new Person(map.get(referral[i]));
            map.put(enroll[i], p);
        }
    }

    static class Person {
        private final Person parent;
        private int income = 0;

        private Person(Person parent){
            this.parent = parent;
        }

        private void addIncome(int n){
            int fee = n / 10;
            income += (n - fee);

            if(fee > 0 && parent != null){
                parent.addIncome(fee);
            }
        }

        private int getIncome(){
            return income;
        }
    }
}
