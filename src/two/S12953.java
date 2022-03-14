/*
    N개의 최소공배수
    https://programmers.co.kr/learn/courses/30/lessons/12953
 */
package two;

public class S12953 {
    public int solution(int[] arr) {
        int answer = 1;
        for(int i : arr){
            answer = getLCM(answer, i);
        }
        return answer;
    }

    private int getLCM(int a, int b){
        int gcd;
        if(a > b){
            gcd = getGCD(a, b);
        }
        else{
            gcd = getGCD(b, a);
        }

        return a * (b/gcd);
    }

    private int getGCD(int l, int s){
        int r = l%s;
        if(r == 0){
            return s;
        }
        return getGCD(s, r);
    }
}
