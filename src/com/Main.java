package com;

import java.util.*;
import two.*;

public class Main {
    public static void main(String[] args) {
        String input = "";
        //printBracketToBrace(input);

        S43165 s = new S43165();
        System.out.println(s.solution(new int[] {1, 1, 1, 1, 1}, 3));
    }

    private static void printBracketToBrace(String input){
        input = input.replaceAll("[\\[]", "[");
        input = input.replaceAll("[]]", "]");

        System.out.println(input);
    }
}
