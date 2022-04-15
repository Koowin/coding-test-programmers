/*
    https://programmers.co.kr/learn/courses/30/lessons/42895
    N으로 표현
 */
package three;

import java.util.*;

public class S42895 {
    public static void main(String[] args) {
        S42895 s = new S42895();
        System.out.println(s.solution(2, -1));
    }
    Set<Integer> cache = new HashSet<>();
    Set<Integer>[] set = new Set[9];

    public int solution(int N, int number) {
        if (number == N) {
            return 1;
        }
        init(N);
        for (int i = 2; i <= 8; i++) {
            for (int j = 1; j < i; j++) {
                int k = i - j;
                for (int n1 : set[j]) {
                    for (int n2 : set[k]) {
                        for (Operator op : Operator.values()) {
                            int applied = op.apply(n1, n2);
                            if (applied > 0 && !cache.contains(applied)) {
                                set[i].add(applied);
                            }
                        }
                    }
                }

            }
            if (set[i].contains(number)) {
                return i;
            }
            cache.addAll(set[i]);
        }
        return -1;
    }

    private void init(int N) {
        cache.add(N);
        for (int i = 1; i < 9; i++) {
            set[i] = new HashSet<>();
            set[i].add(straightNumber(N, i));
        }
    }

    private int straightNumber(int N, int index) {
        int ret = N;
        for (int i = 1; i < index; i++) {
            ret *= 10;
            ret += N;
        }
        return ret;
    }

    enum Operator {
        PLUS{
            @Override
            public int apply(int a, int b) {
                return a + b;
            }
        },
        MINUS{
            @Override
            public int apply(int a, int b) {
                return a - b;
            }
        },
        BACK_MINUS{
            @Override
            public int apply(int a, int b) {
                return b - a;
            }
        },
        MULTIPLY{
            @Override
            public int apply(int a, int b) {
                return a * b;
            }
        },
        DIVIDE{
            @Override
            public int apply(int a, int b) {
                return a / b;
            }
        },
        BACK_DIVIDE{
            @Override
            public int apply(int a, int b) {
                return b / a;
            }
        };
        public abstract int apply(int a, int b);
    }
}
