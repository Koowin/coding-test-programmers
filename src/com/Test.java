package com;

import java.util.*;
import java.util.stream.*;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args){
        String s = "2.0s";

        System.out.println(s.replaceAll("0*s$", ""));
    }
}