/*
    https://programmers.co.kr/learn/courses/30/lessons/64064
    불량 사용자
 */
package three;

import java.util.HashSet;
import java.util.Set;

public class S64064 {
    private int userCount;
    private int[] banCandidates;
    private Set<Integer> combinationSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        userCount = user_id.length;
        banCandidates = new int[banned_id.length];

        for(int i=0;i<banned_id.length;i++) {
            for(int j=0;j<user_id.length;j++) {
                if(isCandidate(user_id[j], banned_id[i])) {
                    banCandidates[i] += (1 << j);
                }
            }
        }

        cur(0, 0);

        return combinationSet.size();
    }

    private static final char WILD_CARD = '*';
    private static boolean isCandidate(String user, String ban) {
        if (user.length() != ban.length()) {
            return false;
        }

        for(int i=0;i<user.length();i++) {
            if(ban.charAt(i) != WILD_CARD && user.charAt(i) != ban.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    private void cur(int index, int combination) {
        if(index == banCandidates.length) {
            combinationSet.add(combination);
            return;
        }

        for(int i=0, offset=1;i<userCount;i++, offset <<= 1) {
            if( (banCandidates[index] & offset) != 0 && (combination & offset) == 0 ) {
                cur(index + 1, combination | offset);
            }
        }
    }
}
