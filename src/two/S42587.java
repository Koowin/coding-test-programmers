/*
    프린터
    https://programmers.co.kr/learn/courses/30/lessons/42587

    객체지향적으로 구현하지 않음. 수정이 필요할 듯
 */

package two;

public class S42587 {
    private int answer = 0;
    private int size;
    private int index;
    private int wantedLocation;
    private int wantedPriority;
    private int[] priorities;

    public int solution(int[] priorities, int location) {
        this.priorities = priorities;
        int answer = 0;
        size = priorities.length;
        index = size - 1;

        int[] jobCount = new int[10];

        wantedLocation = location;
        wantedPriority = priorities[location];

        for (int i : priorities) {
            jobCount[i]++;
        }

        for (int i = 9; i > wantedPriority; i--) {
            if (jobCount[i] > 0) {
                index = getLeft(i);
                answer += jobCount[i];
            }
        }

        answer += getRightCount();
        return answer;
    }

    private int getLeft(int priority) {
        for (; index >= 0; index--) {
            if (priorities[index] == priority) {
                return index;
            }
        }
        for (index = size - 1; ; index--) {
            if (priorities[index] == priority) {
                return index;
            }
        }
    }

    private int getRightCount() {
        int count = 0;
        for (++index; index < size; index++) {
            if (priorities[index] == wantedPriority) {
                count++;
                if (index == wantedLocation) {
                    return count;
                }
            }
        }
        for (index = 0; ; index++) {
            if (priorities[index] == wantedPriority) {
                count++;
                if (index == wantedLocation) {
                    return count;
                }
            }
        }
    }
}
