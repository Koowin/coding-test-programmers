/*
    타겟 넘버
    https://programmers.co.kr/learn/courses/30/lessons/43165
 */

package two;

public class S43165 {
    int answer = 0;
    int size;
    int[] numbers;
    int target;

    public int solution(int[] numbers, int target) {
        size = numbers.length;
        this.numbers = numbers;
        this.target = target;

        makeTarget(0, 0);

        return answer;
    }

    private void makeTarget(int loopCount, int val) {
        if (loopCount == size) {
            if (val == target) {
                answer++;
            }
        } else {
            makeTarget(loopCount + 1, val + numbers[loopCount]);
            makeTarget(loopCount + 1, val - numbers[loopCount]);
        }
    }
}
