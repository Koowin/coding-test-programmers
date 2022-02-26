/*
    가장 큰 수
    https://programmers.co.kr/learn/courses/30/lessons/42746
 */
package two;

import java.util.*;

public class S42746 {
    Comparator<String> c = new Comparator<>(){
        @Override
        public int compare(String o1, String o2){
            String str1 = o1 + o2;
            String str2 = o2 + o1;

            return str2.compareTo(str1);
        }
    };
    public String solution(int[] numbers) {
        List<String> list = new ArrayList<>();

        for(int num : numbers){
            list.add(Integer.toString(num));
        }

        Collections.sort(list, c);

        StringBuilder sb = new StringBuilder();
        for(String str : list){
            sb.append(str);
        }
        return sb.toString().replaceAll("^[0]+", "0");
    }
}