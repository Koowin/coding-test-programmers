/*
    점프와 순간이동
    https://programmers.co.kr/learn/courses/30/lessons/12980
 */
package two;

public class S12980 {
    public int solution(int n) {
        int count=0;
        while(n>0){
            count += n%2;
            n/=2;
        }
        return count;
    }
}
