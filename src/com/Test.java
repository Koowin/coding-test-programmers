package com;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Integer a = new Integer(123);
        System.out.println(a.hashCode());
        a = 456;
        System.out.println(a.getClass());

    }
}
