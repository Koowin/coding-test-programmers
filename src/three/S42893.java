package three;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S42893 {
    List<Page> pageList = new ArrayList<>();

    public int solution(String word, String[] pages) {
        Page.word = word;

        for (int i = 0; i < pages.length; i++) {
            Page p = new Page(i, pages[i]);
            pageList.add(p);
        }

        for (Page p : pageList) {
            double score = (double) p.baseScore / p.link.size();
            for (Page target : p.link) {
                target.addLinkScore(score);
            }
        }
        for (Page p : pageList) {
            p.calTotalScore();
        }
        Collections.sort(pageList);

        return pageList.get(0).index;
    }

    static class Page implements Comparable<Page> {
        private static String word;
        private final int index;
        private final String name;
        private final int baseScore;

        private List<Page> link = new ArrayList<>();
        private double linkScore = 0;

        private double totalScore = 0;

        private Page(int index, String page) {
            this.index = index;
            this.name = findName(page);
            this.baseScore = findBaseScore(page);
            findLinks(page);
        }

        private String findName(String page) {
            return "";
        }

        private int findBaseScore(String page) {
            Pattern pattern = Pattern.compile(word);
            Matcher matcher = pattern.matcher(page);
            return (int) matcher.results().count();
        }

        private void findLinks(String page) {

        }

        private void addLinkScore(double score) {
            linkScore += score;
        }

        private void calTotalScore() {
            totalScore = baseScore + linkScore;
        }

        @Override
        public int compareTo(Page o) {
            return totalScore != o.totalScore ? Double.compare(o.totalScore, totalScore) : Integer.compare(index, o.index);
        }
    }
}