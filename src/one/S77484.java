/*
    프로그래머스: 로또의 최고 순위와 최저 순위
    https://programmers.co.kr/learn/courses/30/lessons/77484
 */

package one;

import java.util.*;

public class S77484 {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {0, 0};
        int count = 0;
        int zero_count = 0;
        Arrays.sort(lottos);
        Arrays.sort(win_nums);
        for(int num : lottos){
            if(num != 0){
                for(int win_num : win_nums){
                    if(num == win_num){
                        count++;
                        break;
                    }
                }
            }
            else{
                zero_count++;
            }
        }
        answer[0] = Math.min(7 - (count+zero_count), 6);
        answer[1] = Math.min(7 - count, 6);
        return answer;
    }
}
