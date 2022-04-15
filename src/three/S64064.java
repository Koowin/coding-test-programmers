/*
    https://programmers.co.kr/learn/courses/30/lessons/64064
    불량 사용자
 */
package three;

import java.util.HashSet;
import java.util.Set;

public class S64064 {
    public static void main(String[] args) {
        String[] userId = {"12345", "12453", "aaaaa"};
        String[] bannedId = {"*****", "*****"};
        S64064 s = new S64064();

        System.out.println(s.solution(userId, bannedId));
    }

    private static final char STAR = '*';

    private int userSize;
    private int[] possibleSet;
    private Set<Integer> answer = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        userSize = user_id.length;
        possibleSet = new int[banned_id.length];
        for (int i = 0; i < banned_id.length; i++) {
            for (int j = 0; j < user_id.length; j++) {
                if (isMatch(user_id[j], banned_id[i])) {
                    possibleSet[i] |= 1<<j;
                }
            }
        }
        combination(0, 0);

        return answer.size();
    }

    private boolean isMatch(String user, String banned) {
        if (user.length() != banned.length()) {
            return false;
        }
        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) != STAR && user.charAt(i) != banned.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private void combination(int depth, int used) {
        if (depth == possibleSet.length) {
            answer.add(used);
            return;
        }
        for (int i = 0, offset = 1; i < userSize; i++, offset <<= 1) {
            if ((possibleSet[depth] & offset) == offset && (used & offset) != offset) {
                combination(depth + 1, used | offset);
            }
        }
    }
}
