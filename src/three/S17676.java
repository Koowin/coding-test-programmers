package three;

public class S17676 {
    Request[] requests;

    public int solution(String[] lines) {
        int answer = 0;

        requests = new Request[lines.length];
        for(int i=0;i<lines.length;i++){
            requests[i] = new Request(lines[i]);
        }

        for(Request r : requests){
            answer = Math.max(answer, getRequestCount(r.endTime));
        }

        return answer;
    }

    private int getRequestCount(int time){
        int count = 0;
        for(Request r : requests){
            if(r.isIn(time)){
                count++;
            }
        }
        return count;
    }

    static class Request{
        private final int startTime;
        private final int endTime;

        private Request(String s){
            String[] arr = s.split(" ");
            this.endTime = timeToInt(arr[1]);
            this.startTime = endTime - milisecondToInt(arr[2]) + 1;
        }

        private static int timeToInt(String s){
            int time = 0;
            String[] arr = s.split("[:.]");
            time += Integer.parseInt(arr[3]);
            time += Integer.parseInt(arr[2]) * 1000;
            time += Integer.parseInt(arr[1]) * 60000;
            time += Integer.parseInt(arr[0]) * 3600000;
            return time;
        }

        private static int milisecondToInt(String s){
            s = s.replaceAll("0*s$", "");
            double d = Double.parseDouble(s);
            d *= 1000;
            return (int) d;
        }

        private boolean isIn(int time){
            if(endTime < time || time + 1000 <= startTime){
                return false;
            }
            return true;
        }
    }
}
