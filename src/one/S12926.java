/*
    시저 암호
    https://programmers.co.kr/learn/courses/30/lessons/12926
 */

package one;

public class S12926 {
    public String solution(String s, int n) {
        char[] arr = s.toCharArray();
        for (int i = 0, size = arr.length; i < size; i++) {
            if (arr[i] >= 'a' && arr[i] <= 'z') {
                arr[i] = (char) (arr[i] + n);
                if (arr[i] > 'z') {
                    arr[i] = (char) (arr[i] - 26);
                }
            } else if (arr[i] >= 'A' && arr[i] <= 'Z') {
                arr[i] = (char) (arr[i] + n);
                if (arr[i] > 'Z') {
                    arr[i] = (char) (arr[i] - 26);
                }
            }
        }

        return new String(arr);
    }
}
