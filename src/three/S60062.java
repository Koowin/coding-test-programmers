package three;

import java.util.*;

public class S60062 {

}

class CircleFence {
    private final int N;
    private int[] dist;
    private WeakPoint minPoint;
    private int length;

    private int answer = -1;


    private CircleFence(int N, int[] weak, int[] dist) {
        this.N = N;
        Arrays.sort(dist);
        this.dist = dist;
    }

    private boolean deleteWeakPoint(int friendIndex) {
        if (dist[friendIndex] >= length) {
            answer = dist.length - friendIndex;
            return true;
        }
        
        return false;
    }

    static class WeakPoint {
        private static int N;
        private final int index;
        private WeakPoint prev;
        private WeakPoint next;

        public WeakPoint(int index) {
            this.index = index;
        }

        private static void link(WeakPoint w1, WeakPoint w2) {
            w1.next = w2;
            w2.prev = w1;
        }
    }
}
