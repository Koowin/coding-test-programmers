/*
    https://programmers.co.kr/learn/courses/30/lessons/42628
    이중우선순위큐
 */
package three;

import java.util.*;

public class S42628 {
    Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    Queue<Integer> minHeap = new PriorityQueue<>();

    public int[] solution(String[] operations) {

        for (String operation : operations) {
            if (operation.charAt(0) == 'I') {
                int num = Integer.parseInt(operation.substring(2, operation.length()));
                addNumber(num);
            }
            else {
                if (operation.length() == 3) {
                    deleteMax();
                }
                else {
                    deleteMin();
                }
            }
        }

        return getAnswer();
    }

    private void addNumber(int n) {
        maxHeap.offer(n);
        minHeap.offer(n);
    }

    private void deleteMax() {
        if (!maxHeap.isEmpty()) {
            int max = maxHeap.poll();
            minHeap.remove(max);
        }
    }

    private void deleteMin() {
        if (!minHeap.isEmpty()) {
            int min = minHeap.poll();
            maxHeap.remove(min);
        }
    }

    private int[] getAnswer() {
        int[] answer = new int[2];
        if (!maxHeap.isEmpty()) {
            answer[0] = maxHeap.peek();
            answer[1] = minHeap.peek();
        }
        return answer;
    }
}
