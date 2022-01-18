package com;

import two.*;

public class Main {
    public static void main(String[] args) {
        String input;

        //printBracketToBrace(input);
    }

    private static void printBracketToBrace(String input){
        input = input.replaceAll("[\\[]", "[");
        input = input.replaceAll("[]]", "]");

        System.out.println(input);
    }
}
