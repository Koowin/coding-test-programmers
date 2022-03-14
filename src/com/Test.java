package com;

import java.util.*;
import java.util.stream.*;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args){

    }
    private static String sharpParser(String str){
        StringBuilder sb = new StringBuilder(str);
        for(int i = 0; i< sb.length();i++){
            if(sb.charAt(i) == '#'){
                sb.setCharAt(i-1, (char) (sb.charAt(i-1) + 32));
                sb.deleteCharAt(i);
            }
        }
        return sb.toString();
    }
}