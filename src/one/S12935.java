/*
    제일 작은 수 제거하기
    https://programmers.co.kr/learn/courses/30/lessons/12935
 */

package one;

import java.util.*;

public class S12935 {
    public ArrayList<Integer> solution(int[] arr) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        int minNum = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0, size = arr.length; i < size; i++) {
            arrayList.add(arr[i]);
            if (arr[i] < minNum) {
                minNum = arr[i];
                minIndex = i;
            }
        }
        arrayList.remove(minIndex);
        if (arrayList.size() == 0) {
            arrayList.add(-1);
        }

        return arrayList;
    }
}
