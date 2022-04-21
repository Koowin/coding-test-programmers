/*
    신고 결과 받기
    https://programmers.co.kr/learn/courses/30/lessons/92334
 */

package one;

import java.util.*;

public class S92334 {
    private Map<String, User> userMap = new HashMap<>();

    public int[] solution(String[] id_list, String[] report, int k) {
        initUserMap(id_list);

        for (String str : report) {
            addReport(str);
        }

        int[] mailCount = new int[id_list.length];
        for (User user : userMap.values()) {
            if (user.isBanned(k)) {
                for (int i : user.reportUserSet) {
                    mailCount[i]++;
                }
            }
        }
        return mailCount;
    }

    private void initUserMap(String[] idList) {
        int i = 0;
        for (String str : idList) {
            userMap.put(str, new User(i++));
        }
    }

    private void addReport(String report) {
        String[] arr = report.split(" ");
        User reportUser = userMap.get(arr[0]);
        User reportedUser = userMap.get(arr[1]);
        reportedUser.reportUserSet.add(reportUser.id);
    }

    static class User {
        private final int id;
        private Set<Integer> reportUserSet = new HashSet<>();

        private User(int id) {
            this.id = id;
        }

        private boolean isBanned(int reportLimit) {
            return reportUserSet.size() >= reportLimit;
        }
    }
}
