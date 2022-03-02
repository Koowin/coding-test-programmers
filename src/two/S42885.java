/*
    구명보트
    https://programmers.co.kr/learn/courses/30/lessons/42885
 */
package two;

public class S42885 {
    public int solution(int[] people, int limit) {
        int[] remainPeople = new int[241];
        int remainPeopleCount = 0;
        int maxWeight=0;

        for(int p : people){
            remainPeople[p]++;
            remainPeopleCount++;
        }

        for(int i=240;i>=40;i--){
            if(remainPeople[i] > 0){
                maxWeight = i;
                break;
            }
        }

        int answer = 0;

        while(remainPeopleCount != 0){
            int n = limit - maxWeight;
            remainPeople[maxWeight]--;
            remainPeopleCount--;

            for(int i = n;i>=40;i--){
                if(remainPeople[i] > 0){
                    remainPeople[i]--;
                    remainPeopleCount--;
                    break;
                }
            }

            if(remainPeople[maxWeight] == 0){
                for(int i=maxWeight-1;i>=40;i--){
                    if(remainPeople[i] > 0){
                        maxWeight = i;
                        break;
                    }
                }
            }
            answer++;
        }

        return answer;
    }
}
