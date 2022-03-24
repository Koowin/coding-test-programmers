package two;

import java.util.*;

public class S92341 {
    public int[] solution(int[] fees, String[] records) {
        ParkFeeManager pfm = new ParkFeeManager(fees);

        for(String s : records){
            pfm.carEvent(s);
        }



        return pfm.getDayFeeList();
    }
}

class ParkFeeManager{
    private final FeeCalculator fc;
    Map<String, Integer> inTimeLog = new HashMap<>();
    Map<String, CarInfo> carNumberForCarInfo = new HashMap<>();

    public ParkFeeManager(int[] fees){
        fc = new FeeCalculator(fees);
    }

    public void carEvent(String record){
        String[] arr =  record.split(" ");
        int time = parseTimeStringToInt(arr[0]);
        String carNumber = arr[1];

        if(arr[2].equals("IN")){
            carInEvent(time, carNumber);
        }
        else{
            carOutEvent(time, carNumber);
        }
    }

    private int parseTimeStringToInt(String s){
        String[] arr = s.split(":");
        int hour = Integer.parseInt(arr[0]);
        int min = Integer.parseInt(arr[1]);
        return hour * 60 + min;
    }

    private void carInEvent(int inTime, String carNumber){
        inTimeLog.put(carNumber, inTime);
        if(!isCarRegistered(carNumber)){
            CarInfo c = new CarInfo(carNumber);
            carNumberForCarInfo.put(carNumber, c);
        }
    }

    private boolean isCarRegistered(String carNumber){
        return carNumberForCarInfo.keySet().contains(carNumber);
    }

    private void carOutEvent(int outTime, String carNumber){

        int inTime = inTimeLog.get(carNumber);
        inTimeLog.remove(carNumber);
        int stayTime = outTime - inTime;

        CarInfo c = carNumberForCarInfo.get(carNumber);
        c.addTime(stayTime);
    }

    public int[] getDayFeeList(){
        midNightFeeGenerator();

        List<CarInfo> carInfos = new ArrayList<>();

        for(String carNumber : carNumberForCarInfo.keySet()){
            carInfos.add(carNumberForCarInfo.get(carNumber));
        }

        Collections.sort(carInfos);

        int[] ret = new int[carInfos.size()];

        for(int i=0;i<ret.length;i++){
            int fee = fc.getFee(carInfos.get(i).getStayTime());
            ret[i] = fee;
        }

        return ret;
    }

    private void midNightFeeGenerator(){
        int outTime = parseTimeStringToInt("23:59");
        for(String remainCarNumber : inTimeLog.keySet()){
            int inTime = inTimeLog.get(remainCarNumber);
            int stayTime = outTime - inTime;

            CarInfo c = carNumberForCarInfo.get(remainCarNumber);
            c.addTime(stayTime);
        }

    }

    static class FeeCalculator{
        private final int baseTime;
        private final int baseFee;
        private final int unitTime;
        private final int unitFee;

        public FeeCalculator(int[] fees){
            baseTime = fees[0];
            baseFee = fees[1];
            unitTime = fees[2];
            unitFee = fees[3];
        }

        public int getFee(int stayTime){
            if(stayTime <= baseTime){
                return baseFee;
            }
            int fee = baseFee;
            stayTime -= baseTime;

            fee += (stayTime / unitTime) * unitFee;
            if(stayTime % unitTime != 0){
                fee += unitFee;
            }
            return fee;
        }
    }

    static class CarInfo implements Comparable<CarInfo>{
        private final String carNumber;
        private int stayTime;

        private CarInfo(String carNumber){
            this.carNumber = carNumber;
        }

        private int getStayTime(){
            return stayTime;
        }

        private void addTime(int time){
            stayTime += time;
        }

        @Override
        public int compareTo(CarInfo o) {
            return carNumber.compareTo(o.carNumber);
        }
    }
}