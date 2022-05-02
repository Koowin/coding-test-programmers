/*
    [3차] 자동완성
    https://programmers.co.kr/learn/courses/30/lessons/17685
 */
package four;

public class S17685 {
    public int solution(String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            root.addWord(word, 0);
        }
        root.findAnswer(0);
        return Trie.answerCount;
    }

    static class Trie {
        private static int answerCount = 0;
        private int wordCount = 0;
        private boolean isWordEnd = false;
        private Trie[] children = new Trie[26];

        private static int parseInt(char c) {
            return c - 'a';
        }

        private void addWord(String word, int index) {
            wordCount++;
            if (word.length() == index) {
                isWordEnd = true;
                return;
            }
            int i = parseInt(word.charAt(index));
            if (children[i] == null) {
                children[i] = new Trie();
            }
            children[i].addWord(word, index + 1);
        }

        private void findAnswer(int count) {
            if (wordCount == 1) {
                answerCount += count;
                return;
            }
            if (isWordEnd) {
                answerCount += count;
            }
            for (int i = 0; i < 26; i++) {
                if (children[i] != null) {
                    children[i].findAnswer(count + 1);
                }
            }
        }
    }
}
