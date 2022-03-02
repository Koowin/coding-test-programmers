/*
    다리를 지나는 트럭
    https://programmers.co.kr/learn/courses/30/lessons/42583
 */
package two;

import java.util.*;

public class S42583 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        BridgeController bc = new BridgeController(bridge_length, weight, truck_weights);

        return bc.sendAllTrucks();
    }
}

class BridgeController{
    private Bridge b;
    private int count = 0;
    private int[] truckWeights;
    private int nextTruckIndex = 0;

    public BridgeController(int bridge_length, int weight, int[] truck_weights){
        b = new Bridge(bridge_length, weight);
        truckWeights = truck_weights;
    }

    public int sendAllTrucks(){
        while(nextTruckIndex < truckWeights.length){
            if(b.isWeightAcceptable(truckWeights[nextTruckIndex])){
                b.addWeight(truckWeights[nextTruckIndex++]);
            }
            else{
                b.addWeight(0);
            }

            count++;
        }
        while(b.weight != 0){
            b.addWeight(0);
            count++;
        }
        return count;
    }

    static class Bridge{
        private final int allowableWeight;
        private Queue<Integer> trucks = new LinkedList<>();
        private int weight = 0;

        private Bridge(int length, int allowableWeight){
            this.allowableWeight = allowableWeight;
            for(int i=0;i<length;i++){
                trucks.add(0);
            }
        }

        private void addWeight(int w){
            weight += w;
            trucks.add(w);
            weight -= trucks.poll();
        }

        private boolean isWeightAcceptable(int nextTruckWeight){
            return weight - trucks.peek() + nextTruckWeight <= allowableWeight;
        }
    }
}
