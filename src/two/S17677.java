/*
    [1차] 뉴스 클러스터링
    https://programmers.co.kr/learn/courses/30/lessons/17677
 */

package two;

import java.util.*;

public class S17677 {
    public static void main(String[] args){
        S17677 s = new S17677();
        String str1 = "handshake";
        String str2 = "shake hands";
        System.out.println(s.solution(str1, str2));
    }

    public int solution(String str1, String str2){
        final int MUL = 65536;

        JacquardSet j1 = new JacquardSet();
        JacquardSet j2 = new JacquardSet();

        for(int i=0, len=str1.length()-1;i<len;i++){
            j1.add(str1.substring(i,i+2));
        }
        for(int i=0, len=str2.length()-1;i<len;i++){
            j2.add(str2.substring(i,i+2));
        }

        double answer = j1.getSimilarValue(j2);
        answer *= MUL;

        return (int) answer;
    }
}

class JacquardSet {
    private Map<Item, Integer> map = new HashMap<>();

    public void add(String str){
        if(str.matches("[a-zA-Z]{2}")){
            Item i = new Item(str.toLowerCase());
            if(map.containsKey(i)){
                map.put(i, map.get(i) + 1);
            }
            else{
                map.put(i, 1);
            }
        }
    }

    public double getSimilarValue(JacquardSet o){
        int intersectionCount = getIntersectionCount(o);
        int unionCount = getSum() + o.getSum() - intersectionCount;
        if(unionCount == 0){
            return 0;
        }
        return (double) intersectionCount / unionCount;
    }

    private int getSum(){
        int count = 0;
        Set<Item> keys = map.keySet();
        for(Item i : keys){
            count += map.get(i);
        }
        return count;
    }

    private int getIntersectionCount(JacquardSet o){
        int count = 0;
        Set<Item> keys = map.keySet();
        for(Item i : keys){
            if(o.map.containsKey(i)){
                count += Math.min(map.get(i), o.map.get(i));
            }
        }
        return count;
    }

    static class Item {
        final String str;

        public Item(String str) {
            this.str = str;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Item)) {
                return false;
            }
            return str.equals(((Item) o).str);
        }

        @Override
        public int hashCode() {
            return str.hashCode();
        }
    }
}

