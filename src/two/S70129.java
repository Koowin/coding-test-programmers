/*
    이진 변환 반복하기
    https://programmers.co.kr/learn/courses/30/lessons/70129
 */
package two;

public class S70129 {
    int count = 0;
    int zeroCount = 0;

    public int[] solution(String s) {
        while(!s.equals("1")){
            s = modify(s);
        }
        return new int[] {count, zeroCount};
    }

    private String modify(String s){
        count++;
        String ret = s.replaceAll("0", "");
        zeroCount += (s.length() - ret.length());

        return Integer.toBinaryString(ret.length());
    }
}
