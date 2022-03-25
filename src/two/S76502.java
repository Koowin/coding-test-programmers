/*
    괄호 회전하기
    https://programmers.co.kr/learn/courses/30/lessons/76502
 */
package two;

import java.util.*;

public class S76502 {
    private final char[] openBrackets = {'(', '{', '['};
    private final char[] closeBrackets = {')', '}', ']'};
    private String goodString;

    public int solution(String s) {
        if(s.length() % 2 == 1){
            return 0;
        }
        if(!canMakeGoodString(s)){
            return 0;
        }

        return getAnswer();
    }

    private boolean canMakeGoodString(String s){
        Deque<Character> bracketStack = new LinkedList<>();
        Deque<Integer> indexStack = new LinkedList<>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            boolean isBracketPopped = false;

            for(int j = 0; j < 3; j++){
                if(c == closeBrackets[j]){
                    if(!bracketStack.isEmpty() && bracketStack.peek() == openBrackets[j]){
                        bracketStack.pop();
                        indexStack.pop();
                        isBracketPopped = true;
                    }
                    break;
                }
            }
            if(!isBracketPopped){
                bracketStack.push(c);
                indexStack.push(i);
            }
        }

        if(indexStack.isEmpty()){
            goodString = s;
            return true;
        }
        for(int i = 0, size = indexStack.size() / 2; i < size; i++){
            indexStack.pop();
        }
        int splitIndex = indexStack.peek() + 1;

        if(isGoodString(bracketStack)){
            makeGoodString(s, splitIndex);
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isGoodString(Deque<Character> bracketStack){
        Deque<Character> stack = new LinkedList<>();

        for(Character c : bracketStack){
            boolean isPopped = false;

            for(int i = 0;i<3;i++){
                if(c == closeBrackets[i]){
                    if(stack.isEmpty() || stack.pop() != openBrackets[i]){
                        return false;
                    }
                    isPopped = true;
                    break;
                }
            }
            if(!isPopped){
                stack.push(c);
            }
        }

        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }

    private void makeGoodString(String s, int splitIndex){
        goodString = s.substring(splitIndex, s.length());
        goodString += s.substring(0, splitIndex);
    }

    private int getAnswer(){
        int count = 0;

        Deque<Character> stack = new LinkedList<>();

        for(int i = 0; i < goodString.length();i++){
            char c = goodString.charAt(i);
            boolean isPopped = false;

            for(int j = 0; j < 3; j++){
                if(c == closeBrackets[j]){
                    if(stack.peek() == openBrackets[j]){
                        stack.pop();
                        isPopped = true;
                        if(stack.isEmpty()){
                            count++;
                        }
                        break;
                    }
                }
            }

            if(!isPopped){
                stack.push(c);
            }
        }
        return count;
    }
}