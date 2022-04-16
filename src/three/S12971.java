/*
    https://programmers.co.kr/learn/courses/30/lessons/12971
    스티커 모으기(2)
 */
package three;

public class S12971 {
    public int solution(int sticker[]) {
        int n = sticker.length;
        if(n <= 3) {
            int max = 0;
            for(int i : sticker) {
                max = Math.max(max, i);
            }
            return max;
        }
        int[] whenPickFirst = new int[n-1];
        int[] notPickFirst = new int[n-1];

        whenPickFirst[0] = sticker[0];
        whenPickFirst[1] = Math.max(sticker[0], sticker[1]);

        notPickFirst[0] = sticker[1];
        notPickFirst[1] = Math.max(sticker[1], sticker[2]);

        for(int i=2;i< n - 1;i++) {
            whenPickFirst[i] = Math.max(whenPickFirst[i-2] + sticker[i], whenPickFirst[i-1]);
            notPickFirst[i] = Math.max(notPickFirst[i-2] + sticker[i + 1], notPickFirst[i-1]);
        }

        return Math.max(whenPickFirst[n-2], notPickFirst[n-2]);
    }
}