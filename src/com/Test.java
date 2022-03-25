package com;

import java.util.*;
import java.util.stream.*;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args){
        List<Integer> test = new LinkedList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);

        test.remove(3);
        System.out.println(test);
    }
}