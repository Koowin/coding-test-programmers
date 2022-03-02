/*
    주식가격
    https://programmers.co.kr/learn/courses/30/lessons/42584
 */
package two;

import java.util.*;

public class S42584 {
    int[] answer;
    Deque<PriceIndex> stack = new LinkedList<>();

    public int[] solution(int[] prices) {
        answer = new int[prices.length];

        for(int i=0;i<prices.length;i++){
            PriceIndex p = new PriceIndex(prices[i], i);
            addPriceIndex(p);
        }

        popAll(prices.length-1);

        return answer;
    }

    private void addPriceIndex(PriceIndex p){
        while(!stack.isEmpty() && stack.peek().price > p.price){
            PriceIndex out = stack.pop();
            answer[out.index] = p.index - out.index;
        }
        stack.push(p);
    }

    private void popAll(int n){
        while(!stack.isEmpty()){
            PriceIndex out = stack.pop();
            answer[out.index] = n - out.index;
        }
    }

    static class PriceIndex{
        private final int price;
        private final int index;

        private PriceIndex(int price, int index){
            this.price = price;
            this.index = index;
        }
    }
}
