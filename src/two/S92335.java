package two;

import java.util.*;

public class S92335 {
    public int solution(int n, int k) {
        NumberGenerator ng = new NumberGenerator(n,k);
        ng.generateAllNumbers();
        List<Long> numList = ng.getNumList();

        int answer = 0;

        for(Long i : numList){
            if(isPrime(i)){
                answer++;
            }
        }

        return answer;
    }

    private static boolean isPrime(long n){
        if(n==2){
            return true;
        }
        else if(n < 2 || n % 2 == 0){
            return false;
        }
        for(long i=3, size=(long)Math.sqrt(n); i<=size;i+=2){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    static class NumberGenerator{
        private final int k;
        private int n;
        private List<Long> numList = new ArrayList<>();

        private NumberGenerator(int n, int k){
            this.n = n;
            this.k = k;
        }

        private void generateAllNumbers(){
            while(n % k == 0){
                n /= k;
            }
            while(n > 0){
                numList.add(generateNumber());
            }
        }

        private long generateNumber(){
            long mul = 1;
            long num = 0;

            int r = n % k;

            while(r != 0){
                num += mul * r;
                mul *= 10;
                n /= k;
                r = n % k;
            }

            while(r == 0 && n > 0){
                n /= k;
                r = n % k;
            }
            return num;
        }

        private List<Long> getNumList(){
            return numList;
        }
    }
}