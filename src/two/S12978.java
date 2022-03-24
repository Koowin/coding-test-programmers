/*
    배달
    https://programmers.co.kr/learn/courses/30/lessons/12978
 */

package two;

import java.util.*;

public class S12978 {
    public int solution(int N, int[][] road, int K) {
        VillageMap vm = new VillageMap(N, K);

        for(int[] r : road){
            vm.addRoad(r);
        }

        return vm.getAnswer();
    }

    static class VillageMap{
        private int[][] roads;
        private final int villageCount;
        private final int deliverableTime;

        private int[] timeFromRoot;
        private Set<Integer> visitedVillage = new HashSet<>();
        private Set<Integer> nextVisitPool = new HashSet<>();

        private VillageMap(int villageCount, int deliverableTime){
            this.villageCount = villageCount;
            this.deliverableTime = deliverableTime;

            roads = new int[villageCount][villageCount];
            timeFromRoot = new int[villageCount];

            for(int i=1;i<villageCount;i++){
                timeFromRoot[i] = deliverableTime + 1;
            }
        }

        private void addRoad(int[] road){
            int v1 = road[0] - 1;
            int v2 = road[1] - 1;
            int time = road[2];

            if(time <= deliverableTime){
                if(roads[v1][v2] == 0 || roads[v1][v2] > time){
                    roads[v1][v2] = time;
                    roads[v2][v1] = time;
                }
            }
        }

        private int getAnswer(){
            visitVillage(0);
            while(!nextVisitPool.isEmpty()){
                int nextVillage = getMinTimeVillageFromPool();
                visitVillage(nextVillage);
            }
            return getDeliverableVillageCount();
        }

        private void visitVillage(int villageIndex){
            for(int i=0;i<villageCount;i++){
                if(!visitedVillage.contains(i) && roads[villageIndex][i] > 0){
                    int newTime = timeFromRoot[villageIndex] + roads[villageIndex][i];
                    if(newTime <= deliverableTime){
                        timeFromRoot[i] = Math.min(timeFromRoot[i], newTime);
                        nextVisitPool.add(i);
                    }
                }
            }
            nextVisitPool.remove(villageIndex);
            visitedVillage.add(villageIndex);
        }

        private int getMinTimeVillageFromPool(){
            int minTime = Integer.MAX_VALUE;
            int minIndex = 0;
            for(Integer v : nextVisitPool){
                if(timeFromRoot[v] < minTime){
                    minTime = timeFromRoot[v];
                    minIndex = v;
                }
            }
            return minIndex;
        }

        private int getDeliverableVillageCount(){
            int count = 0;
            for(Integer v : visitedVillage){
                if(timeFromRoot[v] <= deliverableTime){
                    count++;
                }
            }
            return count;
        }
    }
}
