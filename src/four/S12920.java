/*
    선입 선출 스케줄링
    https://programmers.co.kr/learn/courses/30/lessons/12920
 */
package four;

public class S12920 {
    int[] cores;
    public int solution(int n, int[] cores) {
        if (n <= cores.length) {
            return n;
        }
        this.cores = cores;
        n -= cores.length;
        int time = binarySearch(0, 500000000, n);
        n -= jobCountSum(time++);

        int answer = 0;
        for (int i = 0; i < cores.length; i++) {
            if (time % cores[i] == 0) {
                if (--n == 0) {
                    answer = i + 1;
                    break;
                }
            }
        }
        return answer;
    }

    private int binarySearch(int lo, int hi, int n) {
        if (lo + 1 >= hi) {
            if (lo >= hi) {
                return lo;
            }
            if (jobCountSum(hi) < n) {
                return hi;
            } else {
                return lo;
            }
        }
        int mid = (lo + hi) / 2;
        if (jobCountSum(mid) < n) {
            return binarySearch(mid, hi, n);
        } else {
            return binarySearch(lo, mid - 1, n);
        }
    }

    private int jobCountSum(int time) {
        long sum = 0;
        for (int i : cores) {
            sum += time / i;
        }
        if(sum > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        return (int)sum;
    }
}