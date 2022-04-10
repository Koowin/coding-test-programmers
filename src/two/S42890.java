/*
    https://programmers.co.kr/learn/courses/30/lessons/42890
    후보키
 */
package two;

import java.util.*;

public class S42890 {
    public int solution(String[][] relation) {
        Database db = new Database(relation);
        return db.getCandidateKeyCount();
    }

    static class Database {
        long[][] database;

        private Database(String[][] relation) {
            parseData(relation);
        }

        private void parseData(String[][] relation) {
            database = new long[relation.length][relation[0].length];
            for (int col = 0; col < database[0].length; col++) {
                Map<String, Integer> map = new HashMap<>();
                int nextId = 0;
                for (int row = 0; row < database.length; row++) {
                    Integer id = map.get(relation[row][col]);
                    if (id == null) {
                        id = nextId++;
                        map.put(relation[row][col], id);
                    }
                    database[row][col] = id;
                }
            }

            int shiftOffset = 5;
            for (int col = 1; col < database[0].length; col++) {
                for (int row = 0; row < database.length; row++) {
                    database[row][col] <<= shiftOffset;
                }
                shiftOffset += 5;
            }
        }

        private int getCandidateKeyCount() {
            Set<Integer> candidateKeys = new HashSet<>();

            int len = 1 << database[0].length;
            for (int i = 0; i < len; i++) {
                if (isUniqueness(i)) {
                    candidateKeys.add(i);
                }
            }
            minimality(candidateKeys);
            return candidateKeys.size();
        }

        private boolean isUniqueness(int key) {
            Set<Long> set = new HashSet<>();
            long[] rows = new long[database.length];
            int col = 1;
            for (int i = 0; i < 8; i++) {
                if ((key & col) != 0) {
                    for (int j = 0; j < database.length; j++) {
                        rows[j] |= database[j][i];
                    }
                }
                col <<= 1;
            }

            for (long l : rows) {
                if (!set.add(l)) {
                    return false;
                }
            }
            return true;
        }

        private void minimality(Set<Integer> set) {
            for (Iterator<Integer> i = set.iterator(); i.hasNext(); ) {
                int key = i.next();
                for (int o : set) {
                    if (key != o && (key & o) == o) {
                        i.remove();
                        break;
                    }
                }
            }
        }
    }
}