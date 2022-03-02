/*
    2개 이하로 다른 비트
    https://programmers.co.kr/learn/courses/30/lessons/77885
 */
package two;

public class S77885 {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        int i=0;
        for(long n : numbers){
            answer[i++] = getAnswer(n);
        }
        return answer;
    }

    private long getAnswer(long n){
        if(n%2 == 0){
            return n+1;
        }

        StringBuilder sb = new StringBuilder(Long.toBinaryString(n));
        int i = sb.length()-2;
        for(;i>=0;i--){
            if(sb.charAt(i) == '0'){
                sb.setCharAt(i, '1');
                sb.setCharAt(i+1, '0');
                break;
            }
        }
        if(i == -1){
            sb.setCharAt(0, '0');
            sb.insert(0, '1');
        }

        return Long.parseLong(sb.toString(), 2);
    }
}
