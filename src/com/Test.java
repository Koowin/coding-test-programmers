package com;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(2);
        queue.offer(1);
        queue.offer(5);
        queue.offer(3);

        for(int i : queue){
            System.out.println(i);
        }

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.peek() <= 10);
    }
}
