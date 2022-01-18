/*
    오픈채팅방
    https://programmers.co.kr/learn/courses/30/lessons/42888
 */

package two;

import java.util.*;

public class S42888 {
    private HashMap<String, String> nameOfUid = new HashMap<>(100000);
    private ArrayList<MsgLog> msgLogs = new ArrayList<>();

    public String[] solution(String[] record) {
        for (String message : record) {
            String[] arr = message.split(" ");
            switch (arr[0].charAt(0)) {
                case 'E':   //Enter
                    msgLogs.add(new MsgLog(true, arr[1]));
                    nameOfUid.put(arr[1], arr[2]);
                    break;
                case 'L':   //Leave
                    msgLogs.add(new MsgLog(false, arr[1]));
                    break;
                default:    //Change
                    nameOfUid.put(arr[1], arr[2]);
                    break;
            }
        }
        int size = msgLogs.size();
        String[] answer = new String[size];

        for (int i = 0; i < size; i++) {
            answer[i] = msgLogs.get(i).toString();
        }

        return answer;
    }

    class MsgLog {
        private final boolean isEnter;
        private final String uid;

        MsgLog(boolean isEnter, String uid) {
            this.isEnter = isEnter;
            this.uid = uid;
        }

        @Override
        public String toString() {
            String returnVal = nameOfUid.get(this.uid);
            final String[] actions = {"님이 들어왔습니다.", "님이 나갔습니다."};

            if (this.isEnter) {
                return returnVal + actions[0];
            } else {
                return returnVal + actions[1];
            }
        }
    }
}
