/*
    https://programmers.co.kr/learn/courses/30/lessons/68645
    삼각 달팽이
 */

package two;

import java.util.*;

public class S68645 {
    Deque<Integer>[] numberTower;

    public int[] solution(int n) {
        numberTower = new Deque[n];
        for(int i=0;i<n;i++){
            numberTower[i] = new LinkedList<Integer>();
        }

        fillEdgeOfTower(0, 1, n-1);

        List<Integer> answer = new LinkedList<>();
        for(Deque d : numberTower){
            answer.addAll(d);
        }

        return answer.stream().mapToInt(i->i).toArray();
    }

    private void fillEdgeOfTower(int index, int startNumber, int edgeLength){
        if(edgeLength > 2){
            fillEdgeOfTower(index + 2, startNumber + edgeLength * 3, edgeLength - 3);
        }

        if(edgeLength == 0){
            numberTower[index].add(startNumber);
            return;
        }

        for(int i = 0;i<edgeLength;i++){
            numberTower[index++].addFirst(startNumber++);
        }
        for(int i = 0;i<edgeLength;i++){
            numberTower[index].add(startNumber++);
        }
        for(int i = 0;i<edgeLength;i++){
            numberTower[index--].add(startNumber++);
        }
    }
}
