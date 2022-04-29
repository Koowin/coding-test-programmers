/*
    오픈채팅방
    https://programmers.co.kr/learn/courses/30/lessons/42888
 */

package two;

import java.util.*;

public class S42888 {
    private Map<String, User> userMap = new HashMap<>();
    private List<MsgLog> msgLogs = new ArrayList<>();

    public String[] solution(String[] record) {
        for (String msg : record) {
            msgProcess(msg);
        }
        String[] answer = new String[msgLogs.size()];
        int index = 0;
        for (MsgLog log : msgLogs) {
            answer[index++] = log.toString();
        }
        return answer;
    }

    private void msgProcess(String msg) {
        String[] arr = msg.split(" ");
        char c = msg.charAt(0);
        if (c == 'E') {
            enterUser(arr[1], arr[2]);
        } else if (c == 'L') {
            leaveUser(arr[1]);
        } else {
            changeName(arr[1], arr[2]);
        }
    }

    private void enterUser(String userId, String userName) {
        User user = userMap.get(userId);
        if (user == null) {
            user = new User(userName);
            userMap.put(userId, user);
        } else {
            changeName(userId, userName);
        }
        msgLogs.add(new MsgLog(user, true));
    }

    private void leaveUser(String userId) {
        User user = userMap.get(userId);
        msgLogs.add(new MsgLog(user, false));
    }

    private void changeName(String userId, String userName) {
        User user = userMap.get(userId);
        user.name = userName;
    }

    static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }
    }

    static class MsgLog {
        private static final String[] inOutMsg = {"님이 들어왔습니다.", "님이 나갔습니다."};
        private final User user;
        private final boolean isIn;

        public MsgLog(User user, boolean isIn) {
            this.user = user;
            this.isIn = isIn;
        }

        @Override
        public String toString() {
            return isIn ? user.name + inOutMsg[0] : user.name + inOutMsg[1];
        }
    }
}
