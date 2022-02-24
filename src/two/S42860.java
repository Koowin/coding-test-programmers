/*
    조이스틱
    https://programmers.co.kr/learn/courses/30/lessons/42860
 */
package two;

public class S42860 {
    boolean[] isSet;

    public int solution(String name) {
        int count = 0;

        char[] arr = name.toCharArray();
        isSet = new boolean[name.length()];
        int notMatchCount = 0;

        for(int i = 0; i< arr.length;i++){
            if(arr[i] != 'A'){
                count += getCharChangeCost(arr[i]);
                notMatchCount++;
            }
            else{
                isSet[i] = true;
            }
        }
        count += getMoveCost();

        return count;
    }

    private int getCharChangeCost(char c){
        int downCost = c - 'A';
        int upCost = 'Z' - c + 1;
        return Math.min(downCost, upCost);
    }

    private int getMoveCost(){
        int minCost = isSet.length - 1;
        for(int i=0, size = isSet.length / 2;i<size;i++){
            int j = i+1;
            for(;j<isSet.length;j++){
                if(!isSet[j]){
                    break;
                }
            }
            if(j == isSet.length){
                minCost = Math.min(minCost, i);
            }
            else{
                minCost = Math.min(minCost, i+i+isSet.length - j);
            }
        }

        for(int i=0, size = isSet.length / 2;i<size;i++){
            int j = isSet.length - i - 1;
            for(;j>=0;j--){
                if(!isSet[j]){
                    break;
                }
            }
            if(j == -1){
                minCost = Math.min(minCost, i);
            }
            else{
                minCost = Math.min(minCost, i+i+j);
            }
        }
        return minCost;
    }
}
