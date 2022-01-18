/*
    평균 구하기
    https://programmers.co.kr/learn/courses/30/lessons/12944
 */

package one;

public class S12944 {
    public double solution(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return (double) sum / arr.length;
    }
}
