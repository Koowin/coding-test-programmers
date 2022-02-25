package two;

public class S12985 {
    public int solution(int n, int a, int b)
    {
        a--;
        b--;
        int answer = 1;
        int m = 2;

        while(a/m != b/m){
            answer++;
            m *= 2;
        }
        return answer;
    }
}
