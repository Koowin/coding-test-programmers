package two;

public class S12899 {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        String[] numbers = {"4", "1", "2"};

        while (n > 0) {
            int remainder = n % 3;
            answer.append(numbers[remainder]);
            n /= 3;
            if (remainder == 0) {
                n--;
            }
        }
        return answer.reverse().toString();
    }
}
