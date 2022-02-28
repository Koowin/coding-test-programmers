package com;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<Character> list = new LinkedList<>();
        list.add('1');
        list.add('2');
        list.add('2');
        list.add('3');

        Iterator<Character> iter = list.iterator();
        while(iter.hasNext()){
            if(iter.next() == '2'){
                iter.remove();
            }
        }
        System.out.println(list);
    }
}
