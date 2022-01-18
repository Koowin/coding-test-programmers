/*
    약수의 개수와 덧셈
    https://programmers.co.kr/learn/courses/30/lessons/77884
 */

package one;

public class S77884 {
    public int solution(int left, int right) {
        boolean[] isNotPrime = new boolean[right + 1];
        for (int i = 2; i < right + 1; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * 2; j < right + 1; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        int answer = 0;

        for (int i = left; i <= right; i++) {
            int num = i;
            boolean isOdd = true;

            for (int j = 2; j <= i; j++) {
                if (!isNotPrime[j]) {
                    int count = 0;

                    while (num % j == 0) {
                        num /= j;
                        count++;
                    }

                    if (count % 2 == 1) {
                        isOdd = false;
                        break;
                    }
                    if (num == 1) {
                        break;
                    }

                }
            }

            if (isOdd) {
                answer -= i;
            } else {
                answer += i;
            }
        }

        return answer;
    }
}
