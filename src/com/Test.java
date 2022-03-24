package com;

import java.util.*;
import java.util.stream.*;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args){
        boolean[] test1 = new boolean[] {true, false, true};
        boolean[] test2;
        test2 = Arrays.copyOf(test1, test1.length);
        test2[1] = true;

        System.out.println(Arrays.toString(test1));
        System.out.println(Arrays.toString(test2));
    }
}