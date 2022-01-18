/*
    소수 만들기
    https://programmers.co.kr/learn/courses/30/lessons/12977
 */

package one;

public class S12977 {
    public int solution(int[] nums) {
        int answer = 0;
        Prime p = new Prime();
        for (int i = 0, size = nums.length; i < size - 2; i++) {
            for (int j = i + 1; j < size - 1; j++) {
                for (int k = j + 1; k < size; k++) {
                    if (p.checkPrime(nums[i] + nums[j] + nums[k]))
                        answer++;
                }
            }
        }

        return answer;
    }

    class Prime {
        private int[] primes = new int[3000];

        public boolean checkPrime(int value) {
            if (primes[value] == 1) {
                return true;
            } else if (primes[value] == -1) {
                return false;
            } else {
                int size = (int) Math.sqrt(value);
                for (int i = 2; i <= size; i++) {
                    if (value % i == 0) {
                        primes[value] = -1;
                        return false;
                    }
                }
                primes[value] = 1;
                return true;
            }
        }
    }
}
