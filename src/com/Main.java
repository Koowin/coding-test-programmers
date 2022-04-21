package com;

import java.util.*;
import two.*;

public class Main {
    public static void main(String[] args) {
        printBracketToBrace("[[8,5],[6,7],[4,1]]");
    }

    private static void printBracketToBrace(String input){
        input = input.replaceAll("[\\[]", "{");
        input = input.replaceAll("[]]", "}");

        System.out.println(input);
    }
}
