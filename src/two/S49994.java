/*
    방문 길이
    https://programmers.co.kr/learn/courses/30/lessons/49994
 */
package two;

import java.util.*;

public class S49994 {
    public int solution(String dirs) {
        int x = 0, y = 0;
        final int MIN_POINT = -5;
        final int MAX_POINT = 5;

        char[] arr = dirs.toCharArray();
        Set<OneMove> set = new HashSet<>();

        for(char c : arr){
            switch(c){
                case 'U':
                    if(y < MAX_POINT){
                        OneMove o = new OneMove(x, y, true);
                        set.add(o);
                        y++;
                    }
                    break;
                case 'D':
                    if(y > MIN_POINT){
                        OneMove o = new OneMove(x, y-1, true);
                        set.add(o);
                        y--;
                    }
                    break;
                case 'R':
                    if(x < MAX_POINT){
                        OneMove o = new OneMove(x, y, false);
                        set.add(o);
                        x++;
                    }
                    break;
                case 'L':
                    if(x > MIN_POINT){
                        OneMove o = new OneMove(x-1, y, false);
                        set.add(o);
                        x--;
                    }
                    break;
            }
        }

        return set.size();
    }

    static class OneMove{
        private final int x, y;
        private final boolean isUp;

        private OneMove(int x, int y, boolean isUp){
            this.x = x;
            this.y = y;
            this.isUp = isUp;
        }

        @Override
        public boolean equals(Object o){
            if(!(o instanceof OneMove)){
                return false;
            }
            OneMove m = (OneMove) o;
            return (x == m.x && y == m.y && isUp == m.isUp);
        }

        @Override
        public int hashCode(){
            int ret = x * 16 + y;
            if(isUp){
                return ret + 16*16;
            }
            else{
                return ret;
            }
        }
    }
}
