/*
    H-Index
    https://programmers.co.kr/learn/courses/30/lessons/42747
 */
package two;

import java.util.*;

public class S42747 {
    public int solution(int[] citations) {
        Hindex h = new Hindex(citations);
        h.sort();
        return h.getAnswer();
    }

    static class Hindex{
        int[] papers;

        private Hindex(int[] papers){
            this.papers = papers;
        }

        private void sort(){
            Arrays.sort(papers);
        }

        private int getAnswer(){
            int h=papers.length;
            int i=papers.length-1;

            for(;h>=0;h--){
                while(i >= 0 && papers[i] >= h){
                    i--;
                }
                if(papers.length - i - 1 >= h){
                    break;
                }
            }

            return h;
        }
    }
}
