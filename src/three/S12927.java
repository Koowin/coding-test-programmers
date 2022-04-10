package three;

public class S12927 {
    int[] count;
    public long solution(int n, int[] works) {
        count = new int[50001];
        for (int i : works) {
            count[i]++;
        }

        int max = subArr(n);
        return sumOfSquare(max);
    }

    private int subArr(int n) {
        int max = findMax();
        while (n > 0 && max > 0) {
            if (count[max] >= n) {
                count[max] -= n;
                count[max-1] += n;
                n = 0;
            }
            else {
                int temp = count[max];
                count[max--] = 0;
                count[max] += temp;
                n -= temp;
            }
        }
        return max;
    }

    private int findMax() {
        int max = 0;
        for (int i = 50000; i > 0; i--) {
            if (count[i] > 0) {
                max = i;
                break;
            }
        }
        return max;
    }

    private long sumOfSquare(int max) {
        long sum = 0;
        for (int i = max; i > 0; i--) {
            if (count[i] > 0) {
                sum += (long) (i * i) * count[i];
            }
        }
        return sum;
    }
}
