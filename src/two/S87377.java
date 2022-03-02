/*
    교점에 별 만들기
    https://programmers.co.kr/learn/courses/30/lessons/87377
 */
package two;

import java.util.*;

public class S87377 {
    public String[] solution(int[][] line) {
        StarFinder sf = new StarFinder(line);
        sf.findAllStars();
        return sf.getAnswer();
    }
}

class StarFinder{
    private final int[][] lines;
    private Set<Point> stars = new HashSet<>();
    private long minX, minY, maxX, maxY;

    public StarFinder(int[][] lines){
        this.lines = lines;
        minX = minY = Long.MAX_VALUE;
        maxX = maxY = Long.MIN_VALUE;
    }

    public void findAllStars(){
        int size = lines.length;
        for(int i=0;i<size-1;i++){
            for(int j=i+1;j<size;j++){
                Point p = findCrossPoint(lines[i], lines[j]);
                if(p != null){
                    if(stars.add(p)){
                        renewMinMax(p);
                    }
                }
            }
        }
    }

    private Point findCrossPoint(int[] l1, int[] l2){
        long x = (long)l1[1] * l2[2] - (long)l1[2] * l2[1];
        long y = (long)l1[2] * l2[0] - (long)l1[0] * l2[2];

        long d = (long)l1[0] * l2[1] - (long)l1[1] * l2[0];

        if(d == 0 || x%d != 0 || y%d != 0){
            return null;
        }
        return new Point(x/d, y/d);
    }

    private void renewMinMax(Point p){
        minX = Math.min(minX, p.x);
        minY = Math.min(minY, p.y);
        maxX = Math.max(maxX, p.x);
        maxY = Math.max(maxY, p.y);
    }

    public String[] getAnswer(){
        StringBuilder[] sbArr = new StringBuilder[(int)(maxY - minY) + 1];
        StringBuilder temp = new StringBuilder();
        for(int i=0, size=(int)(maxX - minX + 1);i<size;i++){
            temp.append('.');
        }
        String str = temp.toString();

        for(int i=0;i<sbArr.length;i++){
            sbArr[i] = new StringBuilder(str);
        }

        for(Point p : stars){
            int row = (int)(maxY - p.y);
            int col = (int)(p.x - minX);
            sbArr[row].setCharAt(col, '*');
        }

        String[] answer = new String[sbArr.length];
        for(int i=0;i<answer.length;i++){
            answer[i] = sbArr[i].toString();
        }
        return answer;
    }

    static class Point{
        private final long x, y;
        private Point(long x, long y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o){
            if(!(o instanceof Point)){
                return false;
            }
            Point O = (Point) o;
            return x == O.x && y == O.y;
        }
    }
}
