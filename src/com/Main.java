package com;

import java.util.*;
import two.*;

public class Main {
    public static void main(String[] args) {
        String input = "";
        //printBracketToBrace(input);

        S42586 s = new S42586();
        int[] answer = s.solution(new int[] {93, 30, 55}, new int[] {1, 30, 5});
        System.out.println(Arrays.toString(answer));
    }

    private static void printBracketToBrace(String input){
        input = input.replaceAll("[\\[]", "[");
        input = input.replaceAll("[]]", "]");

        System.out.println(input);
    }
}
