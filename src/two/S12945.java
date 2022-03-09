/*
    피보나치 수
    https://programmers.co.kr/learn/courses/30/lessons/12945
 */
package two;

public class S12945 {
    public int solution(int n) {
        FibonacciFinder finder = new FibonacciFinder(n);
        return finder.getAnswer();
    }

    static class FibonacciFinder{
        private static final int DIVIDER = 1234567;
        private int[] value;

        public FibonacciFinder(int n){
            value = new int[n+1];
            value[1] = 1;
        }

        public int getAnswer(){
            int n = value.length;
            for(int i=2;i<n;i++){
                value[i] = (value[i-2] + value[i-1]) % DIVIDER;
            }
            return value[n-1];
        }
    }
}
