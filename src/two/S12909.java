/*
    올바른 괄호
    https://programmers.co.kr/learn/courses/30/lessons/12909
 */
package two;

import java.util.*;

public class S12909 {
    Deque<Boolean> stack = new LinkedList<>();

    boolean solution(String s) {
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '('){
                stack.push(true);
            }
            else{
                if(stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }
        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }
}