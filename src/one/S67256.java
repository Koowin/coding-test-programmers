/*
    키패드 누르기
    https://programmers.co.kr/learn/courses/30/lessons/67256
 */

package one;

public class S67256 {
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        boolean isRight = false;
        if(hand.equals("right")){
            isRight = true;
        }
        Point[] points = new Point[12];
        for(int i=0;i<9;i++){
            points[i+1] = new Point(i%3, i/3);
        }
        points[0] = new Point(1, 3);
        points[10] = new Point(0, 3);
        points[11] = new Point(2, 3);
        int l = 10;
        int r = 11;
        for(int number : numbers){
            if(number != 0 && number % 3 == 1){
                l = number;
                sb.append('L');
            }
            else if(number != 0 && number % 3 == 0){
                r = number;
                sb.append('R');
            }
            else{
                int diff_l = points[l].getDistance(points[number]);
                int diff_r = points[r].getDistance(points[number]);

                if(diff_l < diff_r){
                    l = number;
                    sb.append('L');
                }
                else if(diff_l >diff_r){
                    r = number;
                    sb.append('R');
                }
                else{
                    if(isRight){
                        r = number;
                        sb.append('R');
                    }
                    else{
                        l = number;
                        sb.append('L');
                    }
                }
            }
        }
        return sb.toString();
    }
    private class Point{
        public int x;
        public int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getDistance(Point target){
            return Math.abs(this.x - target.x) + Math.abs(this.y - target.y);
        }
    }
}