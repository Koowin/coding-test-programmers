package com;

import java.util.*;
import java.util.stream.*;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> list = new LinkedList<>();

        int i = 1;
        int j = 2;
        String format = "%02d:%02d";
        System.out.println(String.format(format, i, j));

        Queue<Integer> heap = new PriorityQueue<Integer>(Collections.reverseOrder());
        heap.remove(1);

        Deque<Integer> deque = new LinkedList<>();

    }
}