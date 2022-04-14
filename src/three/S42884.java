/*
    https://programmers.co.kr/learn/courses/30/lessons/42884
    단속카메라
 */
package three;

import java.util.*;

public class S42884 {
    public int solution(int[][] routes) {
        List<Line> list = new ArrayList<>();
        for(int[] route : routes) {
            list.add(new Line(route[0], route[1]));
        }
        Collections.sort(list);

        int answer = 0;

        int lo = 0;
        while(lo < list.size()) {
            int min = list.get(lo).end;

            for(;lo<list.size();lo++) {
                Line l = list.get(lo);
                min = Math.min(min, l.end);
                if(l.start > min){
                    break;
                }
            }

            answer++;
        }

        return answer;
    }

    static class Line implements Comparable<Line>{
        private final int start;
        private final int end;

        private Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            return start != o.start ? Integer.compare(start, o.start) : Integer.compare(o.end, end);
        }
    }
}