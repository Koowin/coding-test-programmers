/*
    괄호 변환
    https://programmers.co.kr/learn/courses/30/lessons/60058
 */
package two;

public class S60058 {
    public String solution(String p) {
        return dq(p);
    }

    private boolean checkRight(String p){
        int count = 0;
        char[] arr = p.toCharArray();
        for(char c : arr){
            if(c == '('){
                count++;
            }
            else{
                count--;
            }
            if(count < 0){
                return false;
            }
        }
        return true;
    }

    private int getIndexOfV(String p){
        int a=0, b=0;
        char[] arr = p.toCharArray();
        for(int i=0;i<arr.length;i++){
            if(arr[i] == '('){
                a++;
            }
            else{
                b++;
            }
            if(a==b){
                return i+1;
            }
        }
        return arr.length;
    }

    private String dq(String p){
        int len = p.length();
        if(len == 0){
            return "";
        }
        int index = getIndexOfV(p);
        String u = p.substring(0, index);
        String v = p.substring(index, len);

        if(checkRight(u)){
            return u + dq(v);
        }
        else{
            return "(" + dq(v) + ")" + reverseBracket(u.substring(1, u.length()-1));
        }
    }

    private String reverseBracket(String p){
        char L = '(';
        char R = ')';
        char[] arr = p.toCharArray();

        for(int i=0;i<arr.length;i++){
            if(arr[i] == L){
                arr[i] = R;
            }
            else{
                arr[i] = L;
            }
        }
        return new String(arr);
    }
}
