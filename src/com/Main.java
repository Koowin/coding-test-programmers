package com;

import java.util.*;
import two.*;

public class Main {
    public static void main(String[] args) {
        printBracketToBrace(
                "[[2,2],[1,4],[3,2]]");
    }

    private static void printBracketToBrace(String input){
        input = input.replaceAll("[\\[]", "{");
        input = input.replaceAll("[]]", "}");

        System.out.println(input);
    }
}
