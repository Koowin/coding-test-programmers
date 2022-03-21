package two;

import java.util.Arrays;

public class S87946 {
    private Dungeon[] dungeons;
    private boolean[] isClosed;
    private int answer = 0;

    public int solution(int k, int[][] d) {
        dungeons = new Dungeon[d.length];
        isClosed = new boolean[d.length];

        for (int i = 0; i < d.length; i++) {
            dungeons[i] = new Dungeon(d[i][0], d[i][1]);
        }
        dfs(k, 0);
        return answer;
    }

    private void dfs(int tiredness, int count) {
        boolean noVisitFlag = true;

        for(int i=0;i<dungeons.length;i++){
            if(!isClosed[i] && dungeons[i].clearable(tiredness)){
                noVisitFlag = false;
                isClosed[i] = true;
                dfs(dungeons[i].getTirednessAfterClear(tiredness), count+1);
                isClosed[i] = false;
            }
        }

        if(noVisitFlag){
            answer = Math.max(answer, count);
        }
    }

    static class Dungeon {
        private final int need;
        private final int cost;

        private Dungeon(int need, int cost) {
            this.need = need;
            this.cost = cost;
        }

        private boolean clearable(int tiredness) {
            return tiredness >= need;
        }

        private int getTirednessAfterClear(int tiredness) {
            return tiredness - cost;
        }
    }
}
