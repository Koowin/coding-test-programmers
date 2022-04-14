/*
    https://programmers.co.kr/learn/courses/30/lessons/60062
    외벽 점검
 */
package three;

import java.util.*;

public class S60062 {
    public static void main(String[] args) {
        S60062 s = new S60062();
        int n = 12;
        int[] weak = {1,5,6,10};
        int[] dist = {1,2,3,4};
        System.out.println(s.solution(n, weak, dist));
    }

    private List<Integer> weakPoint = new ArrayList<>();
    private int[] dist;

    public int solution(int n, int[] weak, int[] dist) {
        for (int i : weak) {
            weakPoint.add(i);
        }
        this.dist = dist;

        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < weak.length; i++) {
            ret = Math.min(ret, minCount(0,0,0));
            weakPoint.add(weakPoint.get(0) + n);
            weakPoint.remove(0);
        }
        if (ret == Integer.MAX_VALUE) {
            return -1;
        }
        return ret;
    }

    private int minCount(int start, int visited, int count) {
        if (start == weakPoint.size()) {
            return count;
        }
        int ret = Integer.MAX_VALUE;
        int select = 1;
        for (int i = 0; i < dist.length; i++) {
            if ((select & visited) != select) {
                visited |= select;
                int next = getNextWeakPoint(start, dist[i]);
                ret = Math.min(ret, minCount(next, visited, count + 1));
                visited ^= select;
            }
            select <<= 1;
        }
        return ret;
    }

    private int getNextWeakPoint(int start, int dist) {
        int i = start;
        int target = weakPoint.get(start) + dist;
        for (; i < weakPoint.size(); i++) {
            if (weakPoint.get(i) > target) {
                return i;
            }
        }
        return i;
    }
}
