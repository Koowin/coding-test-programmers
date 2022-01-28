package com;

import java.util.*;
import two.*;

public class Main {
    public static void main(String[] args) {
        String input = "[[1, 1, 1, 0], [1, 2, 2, 0], [1, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 3], [0, 0, 0, 3]]";
        //printBracketToBrace(input);

        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        S1829 s = new S1829();
        int[] answer = s.solution(6,4,picture);
        for(int i:answer){
            System.out.println(i);
        }
    }

    private static void printBracketToBrace(String input){
        input = input.replaceAll("[\\[]", "{");
        input = input.replaceAll("[]]", "}");

        System.out.println(input);
    }
}
