package com;

import java.util.*;
import java.util.stream.*;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args){
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("123", 123);
        List<Integer> testList = (List<Integer>) testMap.values();
        System.out.println(testList);
        for(Map.Entry<String, Integer> e : testMap.entrySet()){

        }
    }
}