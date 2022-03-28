package com;

import java.util.*;
import java.util.stream.*;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> list = new LinkedList<>();
        Deque<Integer> deque = new LinkedList<>();
        Set<Integer> set = new HashSet<>();



        int i = 1;
        int j = 2;
        String format = "%02d:%02d";
        System.out.println(String.format(format, i, j));

        Queue<Integer> heap = new PriorityQueue<Integer>(Collections.reverseOrder());
        heap.remove(1);

        set.add(2);
        set.add(4);
        set.add(6);
        set.add(8);

        Iterator<Integer> iter = set.iterator();
        while(iter.hasNext()){
            if(iter.next() % 2 == 0){
                System.out.println(iter.toString());
                iter.remove();
            }
        }
        System.out.println(set);
    }
}