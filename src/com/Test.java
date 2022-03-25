package com;

import java.util.*;
import java.util.stream.*;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args){
        Deque<Integer> stack = new LinkedList<>();

        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack);
        System.out.println(stack.peek());
        for(int i : stack){
            System.out.println(i);
        }

        for(int i = 0, size = stack.size() / 2; i < size ; i++){
            stack.pop();
        }
        System.out.println(stack.peek());
    }
}