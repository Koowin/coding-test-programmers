/*
    튜플
    https://programmers.co.kr/learn/courses/30/lessons/64065
 */
package two;

import java.util.*;

public class S64065 {
    public int[] solution(String s) {
        Tuple t = new Tuple(s);
        return t.getAnswer();
    }
}

class Tuple{
    private List<NumberSet> nums = new ArrayList<>();

    public Tuple(String str){
        String[] arr = str.substring(2, str.length()-2).split("},\\{");
        for(String s : arr){
            nums.add(new NumberSet(s));
        }
    }

    public int[] getAnswer(){
        int len = nums.size();
        int[] ret = new int[len];

        Collections.sort(nums);
        for(int i=0;i<len;i++){
            Iterator<Integer> iter = nums.get(i).set.iterator();
            ret[i] = iter.next();
            popAll(i, ret[i]);
        }
        return ret;
    }

    private void popAll(int I, int n){
        for(int i=I, len = nums.size();i<len;i++){
            nums.get(i).set.remove(n);
        }
    }

    static class NumberSet implements Comparable{
        private Set<Integer> set = new HashSet<>();

        private NumberSet(String str){
            String[] arr = str.split(",");
            for(String s : arr){
                set.add(Integer.parseInt(s));
            }
        }

        @Override
        public int compareTo(Object o){
            NumberSet n = (NumberSet) o;
            Integer I = set.size();
            Integer J = n.set.size();
            return I.compareTo(J);
        }

        @Override
        public String toString(){
            String ret = "";
            for(int i : set){
                ret += i;
                ret += " ";
            }
            return ret;
        }
    }
}
