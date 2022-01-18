/*
    하샤드 수
    https://programmers.co.kr/learn/courses/30/lessons/12947
 */

package one;

public class S12947 {
    public boolean solution(int x) {
        String[] arr = Integer.toString(x).split("");
        int s = 0;

        for (String str : arr) {
            s += Integer.parseInt(str);
        }

        return x % s == 0 ? true : false;
    }
}
