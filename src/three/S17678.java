/*
    https://programmers.co.kr/learn/courses/30/lessons/17678
    [1차] 셔틀버스
 */
package three;

import java.util.*;

public class S17678 {
    public String solution(int n, int t, int m, String[] timetable) {
        BusStop bs = new BusStop(n, t, m);

        int[] intTimeTable = Arrays.stream(timetable).mapToInt(S17678::parseTimeStringToInt).sorted().toArray();

        for (int time : intTimeTable) {
            if (!bs.addPassenger(time)) {
                break;
            }
        }

        int intAnswerTime = bs.getMostLateTimeToTakeBus();


        return parseTimeIntToString(intAnswerTime);
    }

    private static int parseTimeStringToInt(String strTime) {
        String[] arr = strTime.split(":");
        int hour = Integer.parseInt(arr[0]);
        int min = Integer.parseInt(arr[1]);

        return hour * 60 + min;
    }

    private static String parseTimeIntToString(int intTime) {
        int hour = intTime / 60;
        int min = intTime % 60;

        return String.format("%02d:%02d", hour, min);
    }

}

class BusStop {
    private ShuttleBus[] busList;
    private int busIndex = 0;

    public BusStop(int n, int t, int m) {
        int busArriveTime = 9 * 60;

        busList = new ShuttleBus[n];
        for (int i = 0; i < n; i++) {
            busList[i] = new ShuttleBus(m, busArriveTime);
            busArriveTime += t;
        }
    }

    public boolean addPassenger(int passengerTime){
        if (busList[busIndex].canTakeBus(passengerTime)) {
            busList[busIndex].addPassenger(passengerTime);
            return true;
        }
        while (++busIndex < busList.length && !busList[busIndex].canTakeBus(passengerTime));
        if (busIndex < busList.length) {
            busList[busIndex].addPassenger(passengerTime);
            return true;
        }
        return false;
    }

    public int getMostLateTimeToTakeBus () {
        int time = 0;

        ShuttleBus b = busList[busList.length - 1];

        if (b.isHaveEmptySeat()) {
            time = b.busArriveTime;
        }
        else {
            time = b.lastPassengerTime - 1;
        }

        return time;
    }

    static class ShuttleBus {
        private final int passengerLimit;
        private final int busArriveTime;
        private int passengerCount = 0;
        private int lastPassengerTime = 0;

        ShuttleBus(int passengerLimit, int busArriveTime) {
            this.passengerLimit = passengerLimit;
            this.busArriveTime = busArriveTime;
        }

        private boolean canTakeBus(int passengerTime) {
            return passengerTime <= busArriveTime && passengerCount < passengerLimit;
        }

        private void addPassenger(int passengerTime) {
            passengerCount++;
            lastPassengerTime = passengerTime;
        }

        private boolean isHaveEmptySeat() {
            return passengerCount < passengerLimit;
        }
    }
}
