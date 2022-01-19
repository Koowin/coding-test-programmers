package com;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        LinkedList<Integer> list = new LinkedList<>();
        list.add(13);
        list.add(1);
        list.add(4);
        Collections.sort(list, Collections.reverseOrder());
        int num = list.remove();
        System.out.println(num);
    }
}
