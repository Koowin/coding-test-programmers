/*
    [1차] 비밀지도
    https://programmers.co.kr/learn/courses/30/lessons/17681
 */
package one;

public class S17681 {
    private static final char[] CHARS = {' ', '#'};

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            answer[i] = parseString(arr1[i] | arr2[i], n);
        }
        return answer;
    }

    private String parseString(int n, int len) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1 << (len - 1); i > 0; i >>= 1) {
            if ((n & i) == 0) {
                stringBuilder.append(CHARS[0]);
            } else {
                stringBuilder.append(CHARS[1]);
            }
        }
        return stringBuilder.toString();
    }
}
