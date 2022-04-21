/*
    https://programmers.co.kr/learn/courses/30/lessons/1830
    브라이언의 고민
 */
package three;

import java.util.*;

public class S1830 {
    public static void main(String[] args) {
        S1830 s = new S1830();
        System.out.println(s.solution("HELLO WORLD"));
    }


    private static final char SMALL_A = 'a';
    private static final char SPACE = ' ';
    private static final String INVALID = "invalid";

    private String sentence;
    List<List<Integer>> charList;

    public String solution(String sentence) {
        this.sentence = sentence;
        initMap();
        return getOriginalString();
    }

    private static boolean isLowCase(char c) {
        return c >= SMALL_A;
    }

    private void initMap() {
        Map<Character, List<Integer>> charMap = new LinkedHashMap<>();
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (isLowCase(c)) {
                List<Integer> list = charMap.get(c);
                if (list == null) {
                    list = new ArrayList<>();
                    charMap.put(c, list);
                }
                list.add(i);
            }
        }
        charList = new ArrayList<>(charMap.values());
    }

    private String getOriginalString() {
        StringBuilder stringBuilder = new StringBuilder();
        int lastCharIndex = 0;
        for (int i = 0; i < charList.size(); i++) {
            RuleType type = distinguishType(i);
            List<Integer> list = charList.get(i);
            int to = charList.get(i).get(0);

            switch (type) {
                case ONE:
                    to--;
                    if (lastCharIndex > to) {
                        return INVALID;
                    }
                    if (!addNoRuledWordIfExist(stringBuilder, lastCharIndex, to)) {
                        return INVALID;
                    }
                    addWordByRuleOne(stringBuilder, list);
                    lastCharIndex = list.get(list.size() - 1) + 2;
                    break;
                case TWO:
                    if (!addNoRuledWordIfExist(stringBuilder, lastCharIndex, to)) {
                        return INVALID;
                    }
                    addWordByRuleTwo(stringBuilder, list);
                    lastCharIndex = list.get(1) + 1;
                    break;
                case BOTH:
                    if (!addNoRuledWordIfExist(stringBuilder, lastCharIndex, to)) {
                        return INVALID;
                    }
                    addWordByRuleOne(stringBuilder, charList.get(i+1));
                    lastCharIndex = list.get(1) + 1;
                    i++;
                    break;
                case INVALID:
                    return INVALID;
            }
        }
        if (!addNoRuledWordIfExist(stringBuilder, lastCharIndex, sentence.length())) {
            return INVALID;
        }
        return stringBuilder.toString().trim();
    }

    private RuleType distinguishType(int index) {
        List<Integer> list = charList.get(index);
        if (list.size() == 2) {
            int start = list.get(0);
            int end = list.get(1);
            if (start + 1== end) {
                return RuleType.INVALID;
            }
            if (index + 1 >= charList.size()) {
                return RuleType.TWO;
            }
            List<Integer> list2 = charList.get(index + 1);
            int start2 = list2.get(0);
            int end2 = list2.get(list2.size() - 1);
            if (start2 < end) {
                if (end2 < end) {
                    int n = list.get(0) + 2;
                    for (int i : list2) {
                        if (i != n) {
                            return RuleType.INVALID;
                        }
                        n += 2;
                    }
                    return RuleType.BOTH;
                }
                return RuleType.INVALID;
            }
            return RuleType.TWO;
        }

        if (list.get(0) == 0 || list.get(list.size() -1) == sentence.length() - 1) {
            return RuleType.INVALID;
        }

        int n = list.get(0);
        for (int i : list) {
            if (n != i) {
                return RuleType.INVALID;
            }
            n += 2;
        }
        return RuleType.ONE;
    }

    private boolean addNoRuledWordIfExist(StringBuilder stringBuilder, int from, int to) {
        if (from < to) {
            String str = sentence.substring(from, to);
            stringBuilder.append(str);
            stringBuilder.append(SPACE);
            if (str.indexOf(SPACE) != -1) {
                return false;
            }
            return true;
        }
        return true;
    }

    private void addWordByRuleOne(StringBuilder stringBuilder, List<Integer> list) {
        stringBuilder.append(sentence.charAt(list.get(0) - 1));
        for (int i : list) {
            stringBuilder.append(sentence.charAt(i + 1));
        }
        stringBuilder.append(SPACE);
    }

    private void addWordByRuleTwo(StringBuilder stringBuilder, List<Integer> list) {
        stringBuilder.append(sentence, list.get(0) + 1, list.get(1));
        stringBuilder.append(SPACE);
    }

    enum RuleType {
        ONE, TWO, BOTH, INVALID
    }
}
