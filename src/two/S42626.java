/*
    더 맵게
    https://programmers.co.kr/learn/courses/30/lessons/42626
 */

package two;

import java.util.*;

public class S42626 {

    public int solution(int[] scoville, int K) {
        Mixer mixer = new Mixer(scoville, K);
        mixer.mixAll();
        return mixer.getMixCount();
    }

    static class Mixer {
        private PriorityQueue<Integer> pq = new PriorityQueue<>();
        private int K;
        private int count = 0;

        Mixer(int[] scoville, int K) {
            this.K = K;
            for (int i : scoville) {
                pq.add(i);
            }
        }

        private void mixAll() {
            while (pq.size() > 1 && pq.peek() < K) {
                mixOneTime();
                count++;
            }
            if (pq.peek() < K) {
                count = -1;
            }
        }

        private void mixOneTime() {
            int a = pq.poll();
            int b = pq.poll();
            pq.add(a + b * 2);
        }

        private int getMixCount() {
            return count;
        }
    }
}