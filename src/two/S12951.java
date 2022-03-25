/*
    JadenCase 문자열 만들기
    https://programmers.co.kr/learn/courses/30/lessons/12951
 */

package two;

public class S12951 {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder(s.toLowerCase());
        sb.insert(0, ' ');

        for(int i=0, size = sb.length();i<size;i++){
            if(sb.charAt(i) == ' '){
                while(i < size && sb.charAt(i) == ' '){
                    i++;
                }
                if(i >= size){
                    break;
                }
                char c = getUpperCase(sb.charAt(i));
                sb.setCharAt(i, c);
            }
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }

    private char getUpperCase(char c){
        if(c >= 'a' && c <= 'z'){
            return (char) (c - 32);
        }
        return c;
    }
}
