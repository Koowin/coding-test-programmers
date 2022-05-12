package com;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> list = new LinkedList<>();
        Deque<Integer> deque = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        String str = " 123";
        System.out.println(str.indexOf(" ", 0));
    }

    static class UnionFind {
        int[] id;
        int[] size;
        int count;

        private UnionFind(int count) {
            this.count = count;
            id = new int[count];
            size = new int[count];
            for (int i=0;i<count;i++) {
                id[i] = i;
                size[i] = 1;
            }
        }

        private int find(int p) {
            while(p != id[p]) {
                p = id[p];
            }
            return p;
        }

        private void union(int p, int q) {
            int i = find(p);
            int j = find(q);

            if (i == j) {
                return;
            }

            if (size[i] < size[j]) {
                id[i] = j;
                size[j] += size[i];
            } else {
                id[j] = i;
                size[i] += size[j];
            }
            count--;
        }

        private int getCount() {
            return count;
        }
    }
}