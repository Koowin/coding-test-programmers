/*
    기능개발
    https://programmers.co.kr/learn/courses/30/lessons/42586
 */

package two;

import java.util.*;

public class S42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        Release r = new Release();
        for(int i=0, size = speeds.length;i<size;i++){
            r.addWork(progresses[i], speeds[i]);
        }
        int[] answer = new int[r.releaseDate.size()-1];
        for(int i=0, size=answer.length;i<size;i++){
            answer[i] = r.workCount[r.releaseDate.elementAt(i+1)];
        }
        return answer;
    }
    class Release{
        Stack<Integer> releaseDate = new Stack<>();
        int[] workCount = new int[100];

        Release(){
            releaseDate.push(0);
        }

        private void addWork(int progress, int speed){
            int remainProgress = 100 - progress;
            int day = remainProgress / speed;
            if(remainProgress % speed != 0){
                day++;
            }

            if(releaseDate.peek() < day){
                releaseDate.push(day);
            }
            else{
                day = releaseDate.peek();
            }
            workCount[day]++;
        }
    }
}
