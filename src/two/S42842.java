/*
    카펫
    https://programmers.co.kr/learn/courses/30/lessons/42842
 */
package two;

public class S42842 {
    public int[] solution(int brown, int yellow) {
        Rectangle r = new Rectangle(brown, yellow);
        return r.getAnswer();
    }

    static class Rectangle{
        private final int plus, mul;
        private Rectangle(int brown, int yellow){
            plus = (brown + 4) / 2;
            mul = brown + yellow;
        }

        private int[] getAnswer(){
            int h=1;
            int w=plus - h;
            while(true){
                if(w*h == mul){
                    break;
                }
                h++;
                w--;
            }

            return new int[] {w, h};
        }
    }
}
