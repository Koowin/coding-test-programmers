/*
    124 나라의 숫자
    https://programmers.co.kr/learn/courses/30/lessons/12899
 */

package two;

public class S12899 {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        char[] numbers = {'4', '1', '2'};

        while (n > 0) {
            int remainder = n % 3;
            answer.append(numbers[remainder]);
            n /= 3;
            if (remainder == 0) {
                n--;
            }
        }

        return answer.reverse().toString();
    }
}
