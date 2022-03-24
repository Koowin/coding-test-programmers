/*
    다음 큰 숫자
    https://programmers.co.kr/learn/courses/30/lessons/12911
 */
package two;

public class S12911 {
    public int solution(int n) {
        int m = n;
        int r = 0;
        while(m % 2 == 0){
            m /= 2;
            r++;
        }
        int l = r;
        while(m % 2 == 1){
            m /= 2;
            l++;
        }

        int answer = n>>l;

        answer++;
        answer <<= l;

        answer += (1<<(l-r-1));
        answer--;

        return answer;
    }
}