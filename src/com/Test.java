package com;

import java.util.*;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("123");
        sb.insert(0, '0');

        System.out.println(sb);
    }
}