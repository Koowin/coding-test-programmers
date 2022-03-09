/*
    숫자의 표현
    https://programmers.co.kr/learn/courses/30/lessons/12924
 */
package two;

public class S12924 {
    public int solution(int n) {
        int answer = 1;

        int i=2;
        int sum = 1;
        while(i * (i+1) <= n * 2){
            int t = n - sum;
            if(t % i == 0){
                System.out.println(i);
                answer++;
            }
            sum += i;
            i++;
        }

        return answer;
    }
}
