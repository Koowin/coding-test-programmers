/*
    [3차] 방금그곡
    https://programmers.co.kr/learn/courses/30/lessons/17683
 */
package one;

import java.util.*;

public class S17683 {
    public String solution(String m, String[] musicinfos) {
        List<Music> list = new ArrayList<>();
        m = Music.sharpParser(m);
        System.out.println("m: " + m);
        int index=0;
        for(String str : musicinfos){
            Music music = new Music(str, index++);
            list.add(music);
        }

        Collections.sort(list);

        for(Music music : list){
            if(music.isMatch(m)){
                return music.getName();
            }
        }

        return "(None)";
    }

    static class Music implements Comparable<Music>{
        private final String name;
        private final int runTime;
        private final String sequence;
        private final int index;

        private Music(String str, int index){
            String[] arr = str.split(",");
            this.runTime = getMinute(arr[0], arr[1]);
            this.name = arr[2];
            this.sequence = sharpParser(arr[3]);
            this.index = index;
        }

        private static String sharpParser(String str){
            StringBuilder sb = new StringBuilder(str);
            for(int i = 0; i< sb.length();i++){
                if(sb.charAt(i) == '#'){
                    sb.setCharAt(i-1, (char) (sb.charAt(i-1) + 32));
                    sb.deleteCharAt(i);
                }
            }
            return sb.toString();
        }

        private int getMinute(String start, String end){
            String[] sArr = start.split(":");
            String[] eArr = end.split(":");

            int hour = Integer.parseInt(eArr[0]) - Integer.parseInt(sArr[0]);
            int minute = Integer.parseInt(eArr[1]) - Integer.parseInt(sArr[1]);

            if(minute < 0){
                minute += 60;
                hour--;
            }

            return hour * 60 + minute;
        }

        private boolean isMatch(String m){
            String s = getRunSequence();
            return s.contains(m);
        }

        private String getRunSequence(){
            if(runTime <= sequence.length()){
                return sequence.substring(0,runTime);
            }
            else{
                int loopCount = runTime / sequence.length();
                int addIndex = runTime % sequence.length();
                StringBuilder sb = new StringBuilder();
                for(int i=0;i<loopCount;i++){
                    sb.append(sequence);
                }
                sb.append(sequence.substring(0,addIndex));
                return sb.toString();
            }
        }

        private String getName(){
            return name;
        }

        @Override
        public int compareTo(Music m){
            if(runTime == m.runTime){
                return index < m.index ? -1 : 1;
            }
            return runTime < m.runTime ? 1 : -1;
        }

        @Override
        public String toString(){
            return "[" + name + ", " + runTime + ", " + sequence + "]";
        }
    }
}
