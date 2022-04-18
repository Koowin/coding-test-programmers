/*
    https://programmers.co.kr/learn/courses/30/lessons/68646
    풍선 터트리기
 */
package three;

public class S68646 {
    public int solution(int[] a) {
        int answer = 0;
        int min = Integer.MAX_VALUE;

        int last = 0;

        for(int i = 0;i<a.length;i++){
            if(a[i] < min) {
                answer++;
                min = a[i];
                last = i;
            }
        }

        min = Integer.MAX_VALUE;
        for(int j=a.length-1;j>last;j--) {
            if(a[j] < min) {
                answer++;
                min = a[j];
            }
        }
        return answer;
    }
}
