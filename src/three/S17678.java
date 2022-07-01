/*
    https://programmers.co.kr/learn/courses/30/lessons/17678
    [1차] 셔틀버스
 */
package three;

import java.util.*;

public class S17678 {
    private Bus[] busList;
    private List<Crew> crewList = new ArrayList<>();

    public String solution(int n, int t, int m, String[] timetable) {
        initBusList(n, t, m);
        initCrewList(timetable);

        int index = 0;
        for(Crew c : crewList) {
            while(index < n && !busList[index].addCrew(c)){
                index++;
            }
        }

        int answerTime;

        if(busList[n-1].remainSeats > 0) {
            answerTime = busList[n-1].arriveTime;
        } else {
            answerTime = busList[n-1].lastCrew.arriveTime - 1;
        }

        return makeTime(answerTime);
    }

    private void initBusList(int n, int t, int m) {
        busList = new Bus[n];
        Bus.cap = m;

        int time = 540;

        for(int i=0;i<n;i++) {
            Bus b = new Bus(time);
            busList[i] = b;
            time += t;
        }
    }

    private void initCrewList(String[] timetable) {
        for(String str : timetable) {
            crewList.add(new Crew(parseTime(str)));
        }
        Collections.sort(crewList);
    }

    private static final int MIN_PER_HOUR = 60;
    private static int parseTime(String str) {
        String[] arr = str.split(":");
        int ret = 0;
        ret += Integer.parseInt(arr[0]) * MIN_PER_HOUR;
        ret += Integer.parseInt(arr[1]);

        return ret;
    }

    private static final String format = "%02d:%02d";
    private static String makeTime(int t) {
        int hour = t / MIN_PER_HOUR;
        int min = t % MIN_PER_HOUR;
        return String.format(format, hour, min);
    }

    private static class Crew implements Comparable<Crew>{
        private final int arriveTime;

        private Crew(int arriveTime) {
            this.arriveTime = arriveTime;
        }

        @Override
        public int compareTo(Crew c){
            return Integer.compare(arriveTime, c.arriveTime);
        }
    }

    private static class Bus {
        private static int cap;
        private final int arriveTime;
        private int remainSeats;
        private Crew lastCrew;

        private Bus(int arriveTime){
            this.arriveTime = arriveTime;
            remainSeats = cap;
        }

        private boolean addCrew(Crew crew) {
            if(remainSeats > 0 && crew.arriveTime <= arriveTime) {
                remainSeats--;
                lastCrew = crew;
                return true;
            }
            return false;
        }
    }
}
