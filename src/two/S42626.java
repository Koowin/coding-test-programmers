/*
    더 맵게
    https://programmers.co.kr/learn/courses/30/lessons/42626
 */

package two;

import java.util.*;

public class S42626 {
    private PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    public int solution(int[] scoville, int K) {
        for (int i : scoville) {
            priorityQueue.offer(i);
        }
        int count =0;
        while (priorityQueue.size() > 1 && priorityQueue.peek() < K) {
            int a = priorityQueue.poll();
            int b = priorityQueue.poll();
            int mixed = a + b * 2;
            priorityQueue.offer(mixed);
            count++;
        }
        if (priorityQueue.size() == 1 && priorityQueue.peek() < K) {
            return -1;
        }
        return count;
    }
}