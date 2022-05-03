/*
    [1차] 추석 트래픽
    https://programmers.co.kr/learn/courses/30/lessons/17676
 */
package three;

public class S17676 {
    private final int ADDER = 999;
    Request[] requests;

    public int solution(String[] lines) {
        requests = new Request[lines.length];
        for (int i = 0; i < lines.length; i++) {
            requests[i] = new Request(lines[i]);
        }

        int maxCount = 0;
        for (int i = 0; i < requests.length - maxCount; i++) {
            int count = 1;
            int e = requests[i].endTime + ADDER;
            for (int j = i + 1; j < requests.length; j++) {
                if (requests[j].startTime <= e) {
                    count++;
                }
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }

    static class Request {
        private static final int M_SEC_PER_HOUR = 3_600_000;
        private static final int M_SEC_PER_MIN = 60_000;
        private static final int M_SEC_PER_SEC = 1_000;

        private final int startTime;
        private final int endTime;

        private Request(String line) {
            String[] arr = line.split(" ");
            endTime = parseEndTime(arr[1]);
            startTime = endTime - parseTakeTime(arr[2]);
        }

        private int parseEndTime(String time) {
            String[] arr = time.split("[:\\.]");
            int ret = 0;
            ret += Integer.parseInt(arr[0]) * M_SEC_PER_HOUR;
            ret += Integer.parseInt(arr[1]) * M_SEC_PER_MIN;
            ret += Integer.parseInt(arr[2]) * M_SEC_PER_SEC;
            ret += Integer.parseInt(arr[3]);
            return ret;
        }

        private int parseTakeTime(String time) {
            String[] arr = time.split("[s\\.]");
            int ret = 0;
            ret += Integer.parseInt(arr[0]) * M_SEC_PER_SEC;
            if (arr.length > 1) {
                ret += Integer.parseInt(arr[1]);
            }
            return ret - 1;
        }
    }
}
