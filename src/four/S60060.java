/*
    가사 검색
    https://programmers.co.kr/learn/courses/30/lessons/60060
 */
package four;

import java.util.*;

public class S60060 {
    private static final char WILD_CARD = '?';

    public int[] solution(String[] words, String[] queries) {
        Trie root = new Trie();
        Trie reverseRoot = new Trie();

        for (String word : words) {
            root.addWord(word, 0);
            StringBuilder sb = new StringBuilder(word);
            reverseRoot.addWord(sb.reverse().toString(), 0);
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < answer.length; i++) {
            if (queries[i].charAt(queries[i].length() - 1) == WILD_CARD) {
                answer[i] = root.search(queries[i], 0);
            } else {
                StringBuilder sb = new StringBuilder(queries[i]);
                answer[i] = reverseRoot.search(sb.reverse().toString(), 0);
            }
        }
        return answer;
    }

    private static class Trie {
        private static final char LOW_A = 'a';

        private Trie[] children = new Trie[26];
        private Map<Integer, Integer> lenMap = new HashMap<>();

        private static int getCharIndex(char c) {
            return c - LOW_A;
        }

        private void addWord(String word, int index) {
            int leftLength = word.length() - index;
            lenMap.put(leftLength, lenMap.getOrDefault(leftLength, 0) + 1);

            if (index == word.length()) {
                return;
            }

            int i = getCharIndex(word.charAt(index));
            if (children[i] == null) {
                children[i] = new Trie();
            }
            children[i].addWord(word, index + 1);
        }

        private int search(String word, int index) {
            if (index == word.length()) {
                return lenMap.getOrDefault(0, 0);
            }

            char c = word.charAt(index);
            if (c == WILD_CARD) {
                return lenMap.getOrDefault(word.length() - index, 0);
            }

            int i = getCharIndex(c);
            if(children[i] != null) {
                return children[i].search(word, index + 1);
            }
            return 0;
        }
    }
}
