package com;

import java.util.*;
import two.*;

public class Main {
    public static void main(String[] args) {
        String input = "";
        //printBracketToBrace(input);

        S17680 s = new S17680();
        String[][] testCases = new String[6][];
        testCases[0] = new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        testCases[1] = new String[] {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        testCases[2] = new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        testCases[3] = new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        testCases[4] = new String[] {"Jeju", "Pangyo", "NewYork", "newyork"};
        testCases[5] = new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};

        int[] cacheSizes = new int[6];
        cacheSizes[0] = 3;
        cacheSizes[1] = 3;
        cacheSizes[2] = 2;
        cacheSizes[3] = 5;
        cacheSizes[4] = 2;
        cacheSizes[5] = 0;
        for(int i=0, size = cacheSizes.length;i<size;i++){
            System.out.println(s.solution(cacheSizes[i], testCases[i]));
        }
    }

    private static void printBracketToBrace(String input){
        input = input.replaceAll("[\\[]", "[");
        input = input.replaceAll("[]]", "]");

        System.out.println(input);
    }
}
