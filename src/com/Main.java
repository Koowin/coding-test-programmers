package com;

import java.util.*;
import two.*;

public class Main {
    public static void main(String[] args) {
        printBracketToBrace("\t[[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]");
    }

    private static void printBracketToBrace(String input){
        input = input.replaceAll("[\\[]", "{");
        input = input.replaceAll("[]]", "}");

        System.out.println(input);
    }
}
