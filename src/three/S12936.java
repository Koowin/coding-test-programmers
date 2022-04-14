package three;

import java.util.*;

public class S12936 {
    private List<Integer> list = new ArrayList<>();
    private int[] answer;
    private int nextIndex = 0;

    public int[] solution(int n, long k) {
        k--;
        long[] div = new long[n];
        answer = new int[n];

        for(int i=1;i<=n;i++) {
            list.add(i);
        }

        div[1] = 1;
        for (int i=2;i<n;i++) {
            div[i] = div[i-1] * i;
        }

        for(int i=n-1;i>0;i--) {
            add((int)(k / div[i]));
            k %= div[i];
        }
        add((int)k);


        return answer;
    }

    private void add(int n) {
        answer[nextIndex++] = list.get(n);
        list.remove(n);
    }
}
