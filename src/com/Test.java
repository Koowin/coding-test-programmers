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


    }

    static class Test1 {
        private int i = 1;
    }

    static class Test2 {
        private void foo() {
            Test1 t = new Test1();
            System.out.println(t.i);
        }
    }
}