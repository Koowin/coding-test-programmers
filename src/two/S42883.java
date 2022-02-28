/*
    큰 수 만들기
    https://programmers.co.kr/learn/courses/30/lessons/42883
 */

package two;

public class S42883 {
    private int record = 0;
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder(number);
        for(int i=0;i<k;i++){
            sb = deleteOne(sb);
        }
        return sb.toString();
    }

    private StringBuilder deleteOne(StringBuilder sb){
        int i=record;
        for(int size=sb.length()-1;i<size;i++){
            if(sb.charAt(i) < sb.charAt(i+1)){
                record = Math.max(i-1, 0);
                break;
            }
        }
        sb.deleteCharAt(i);

        return sb;
    }
}
