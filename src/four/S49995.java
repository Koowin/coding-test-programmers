/*
    쿠키 구입
    https://programmers.co.kr/learn/courses/30/lessons/49995
 */
package four;

public class S49995 {
    public static void main(String[] args) {
        S49995 s = new S49995();
        int[] input = {1, 1, 2, 3};
        System.out.println(s.solution(input));
    }
    private int answer = 0;
    private int[] sumArray;

    public int solution(int[] cookie) {
        initSumArray(cookie);
        findAnswer();
        return answer;
    }

    private void initSumArray(int[] cookie) {
        sumArray = new int[cookie.length + 1];
        for (int i = 0; i < cookie.length; i++) {
            sumArray[i + 1] = sumArray[i] + cookie[i];
        }
    }

    private void findAnswer() {
        for (int i = 0; i < sumArray.length - 2; i++) {
            for (int j = sumArray.length - 1; j > 1; j--) {
                int n = sumArray[j] - sumArray[i];
                if (n % 2 == 0) {
                    n /= 2;
                    if (answer < n && binarySearch(i + 1, j - 1, n + sumArray[i])) {
                        answer = n;
                    }
                }
            }
        }
    }

    private boolean binarySearch(int lo, int hi, int key) {
        if (hi < lo) {
            return false;
        }

        int mid = (lo + hi) / 2;
        int n = sumArray[mid];
        if (n == key) {
            return true;
        }
        if (n < key) {
            return binarySearch(mid + 1, hi, key);
        } else {
            return binarySearch(lo, mid - 1, key);
        }
    }
}
