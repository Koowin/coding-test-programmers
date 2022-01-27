/*
    짝지어 제거하기
    https://programmers.co.kr/learn/courses/30/lessons/12973
 */
package two;

import java.util.*;

public class S12973 {
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        /*
        다른 사람의 풀이를 보고 수정하기 전 기존 코드 부분입니다.

        for (int i = 0, size = s.length(); i < size; i++) {
            char next = s.charAt(i);
         */
        for(char next : s.toCharArray()){
            if (stack.isEmpty()) {
                stack.push(next);
            } else {
                if (stack.peek() == next) {
                    stack.pop();
                } else {
                    stack.push(next);
                }
            }
        }

        if (stack.isEmpty()) {
            return 1;
        }
        return 0;
    }
}
