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

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        for (Iterator<Integer> iter = list.iterator(); iter.hasNext(); ) {
            int i = iter.next();
            System.out.println(i);
            if (i == 3) {
                iter.remove();
            }
            if (i == 4) {
                list.add(5);
            }
        }
    }
}