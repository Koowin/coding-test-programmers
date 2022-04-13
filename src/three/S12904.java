/*
    https://programmers.co.kr/learn/courses/30/lessons/12904
    가장 긴 팰린드롬
 */
package three;

public class S12904 {
    char[] arr;
    int[][] cache;

    public int solution(String s) {
        arr = s.toCharArray();
        cache = new int[arr.length][arr.length + 1];
        palindrome(0, arr.length);
        return cache[0][arr.length];
    }

    private void palindrome(int s, int length) {
        if (cache[s][length] != 0) {
            return;
        }
        if (isPalindrome(s, length)) {
            cache[s][length] = length;
            return;
        }
        palindrome(s, length - 1);
        palindrome(s + 1, length - 1);
        cache[s][length] = Math.max(cache[s][length - 1], cache[s + 1][length - 1]);
    }

    private boolean isPalindrome(int s, int length) {
        int i = s;
        int j = i + length - 1;

        while (i < j) {
            if (arr[i++] != arr[j--]) {
                return false;
            }
        }
        return true;
    }
}
