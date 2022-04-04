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

        int i = 7;
        System.out.println(i & 1<<2);
    }
}