/*
    수식 최대화
    https://programmers.co.kr/learn/courses/30/lessons/67257
 */
package two;

import java.util.*;

public class S67257 {
    public long solution(String expression) {
        ExpressionCalculator ec = new ExpressionCalculator(expression);
        ec.findAnswer();
        return ec.getAnswer();
    }
    static class ExpressionCalculator {
        List<String> expression = new LinkedList<>();
        long answer = Long.MIN_VALUE;
        boolean[] isOpUsed = new boolean[3];
        private static final String[] OPS_ARR = {"+", "-", "*"};


        private ExpressionCalculator(String str){
            char[] charArr = str.toCharArray();
            int numIndex = 0;
            for(int i=0;i<charArr.length;i++){
                if(charArr[i] == '+' || charArr[i] == '-' || charArr[i] == '*'){
                    expression.add(str.substring(numIndex,i));
                    expression.add(Character.toString(charArr[i]));
                    numIndex = i+1;
                }
            }
            expression.add(str.substring(numIndex));
        }

        private void findAnswer(){
            dfs(3, "");
        }

        private void dfs(int count, String str){
            if(count == 0){
                long num = Math.abs(calculateNum(str));
                answer = Math.max(answer, num);
            }
            else{
                for(int i=0;i<isOpUsed.length;i++){
                    if(!isOpUsed[i]){
                        isOpUsed[i] = true;
                        dfs(count-1, str + OPS_ARR[i]);
                        isOpUsed[i] = false;
                    }
                }
            }
        }

        private long calculateNum(String str){
            Deque<String> deque1 = new LinkedList<>();
            Deque<String> deque2 = new LinkedList<>();
            deque1.addAll(expression);

            for(int i=0;i<3;i++){
                String op = str.substring(i,i+1);
                while(deque1.size() > 0){
                    String polled = deque1.poll();
                    if(polled.equals(op)){
                        Long num1 = Long.parseLong(deque2.pollLast());
                        Long num2 = Long.parseLong(deque1.poll());
                        if(op.equals(OPS_ARR[0])){
                            deque2.offer(Long.toString(num1 + num2));
                        }
                        else if(op.equals(OPS_ARR[1])){
                            deque2.offer(Long.toString(num1 - num2));
                        }
                        else{
                            deque2.offer(Long.toString(num1 * num2));
                        }
                    }
                    else{
                        deque2.offer(polled);
                    }
                }
                Deque<String> temp = deque1;
                deque1 = deque2;
                deque2 = temp;
            }
            return Long.parseLong(deque1.poll());
        }

        private long getAnswer(){
            return answer;
        }
    }

}
