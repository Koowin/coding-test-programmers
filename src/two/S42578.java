/*
    위장
    https://programmers.co.kr/learn/courses/30/lessons/42578
 */
package two;

import java.util.*;

public class S42578 {
    public int solution(String[][] clothes) {
        ClothCounter cc = new ClothCounter(clothes);
        return cc.getAnswer();
    }

    static class ClothCounter{
        Map<String, Integer> map = new HashMap<>();

        private ClothCounter(String[][] clothes){
            for(String[] arr : clothes){
                addCloth(arr[1]);
            }

        }

        private void addCloth(String type){
            Integer n = map.get(type);
            if(n == null){
                map.put(type, 2);
            }
            else{
                map.put(type, n+1);
            }
        }

        public int getAnswer(){
            int count = 1;
            Collection<Integer> c = map.values();
            for(Integer i : c){
                count *= i;
            }

            return count - 1;
        }
    }
}
