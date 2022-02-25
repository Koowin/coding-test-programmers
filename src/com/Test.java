package com;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Deque<Integer> test = new LinkedList<>();
        test.offer(1);
        test.offer(2);
        test.offer(3);
        System.out.println(test);
        for(Integer i : test){
            System.out.println(i);
        }

        List<Integer>[] list= new ArrayList[3];
    }
}
