package com;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        String[] abc = new String[] {"119", "97674223", "1195524421"};
        Arrays.sort(abc, (o1, o2) -> {
            if (o1.length() > o2.length()) {
                return 1;
            } else if (o1.length() < o2.length()) {
                return -1;
            }
            return 0;
        });

        for(String s : abc){
            System.out.println(s);
        }
    }
}
