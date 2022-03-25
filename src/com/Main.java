package com;

import java.util.*;
import two.*;

public class Main {
    public static void main(String[] args) {
        String input = "[](){}";
        S76502 s = new S76502();
        System.out.println(s.solution(input));
    }

    private static void printBracketToBrace(String input){
        input = input.replaceAll("[\\[]", "{");
        input = input.replaceAll("[]]", "}");

        System.out.println(input);
    }
}
