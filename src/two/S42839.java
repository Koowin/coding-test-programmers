/*
    소수 찾기
    https://programmers.co.kr/learn/courses/30/lessons/42839
 */
package two;

import java.util.*;

public class S42839 {
    public int solution(String numbers) {
        AnswerFinder af = new AnswerFinder(numbers);
        af.findAnswer();
        return af.getAnswer();
    }

    static class AnswerFinder{
        private final int maxNum;
        private char[] nums;
        private boolean[] isNotPrime;
        private Set<Integer> primeSet = new HashSet<>();

        private boolean[] isUsed;

        private AnswerFinder(String numbers){
            nums = numbers.toCharArray();
            isUsed = new boolean[nums.length];
            Arrays.sort(nums);
            StringBuilder sb = new StringBuilder();
            sb.append(nums);
            maxNum = Integer.parseInt(sb.reverse().toString());
            isNotPrime = new boolean[maxNum+1];
        }

        private void findAnswer(){
            primeChecker();
            for(int i=1;i<=nums.length;i++){
                dfs(i, "");
            }
        }

        private void primeChecker(){
            isNotPrime[0] = true;
            isNotPrime[1] = true;

            for(int i=2;i*i<=maxNum;i++){
                if(!isNotPrime[i]){
                    for(int j=i+i;j<=maxNum;j += i){
                        isNotPrime[j] = true;
                    }
                }
            }
        }

        private void dfs(int count, String numString){
            if(count == 0){
                Integer num = Integer.parseInt(numString);
                if(!isNotPrime[num]){
                    primeSet.add(num);
                }
                return;
            }
            else{
                for(int i=0;i<nums.length;i++){
                    if(!isUsed[i]){
                        isUsed[i] = true;
                        dfs(count-1, numString + nums[i]);
                        isUsed[i] = false;
                    }
                }
            }
        }

        private int getAnswer(){
            return primeSet.size();
        }

    }
}