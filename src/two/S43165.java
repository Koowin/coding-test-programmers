/*
    타겟 넘버
    https://programmers.co.kr/learn/courses/30/lessons/43165
 */

package two;

public class S43165 {
    private int answer = 0;
    private int[] numbers;
    private int target;

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        count(0, 0);
        return answer;
    }

    private void count(int index, int sum) {
        if (index == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }
        count(index + 1, sum + numbers[index]);
        count(index + 1, sum - numbers[index]);
    }
}
