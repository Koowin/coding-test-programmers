package com;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> list = new LinkedList<>();
        Deque<Integer> deque = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        System.out.println(map.putIfAbsent("1", 1));
        System.out.println(map.computeIfAbsent("2", k -> 2));

        Iterator<Integer> iter = list.iterator();

        iter.remove();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {

        }
    }

}