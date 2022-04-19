/*
    https://programmers.co.kr/learn/courses/30/lessons/42893
    매칭 점수
 */
package three;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S42893 {
    public static void main(String[] args) {
        String[] input = {
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"
        };
        S42893 s = new S42893();
        System.out.println(s.solution("Muzi", input));
    }

    HashMap<String, Page> pageHashMap = new HashMap<>();

    public int solution(String word, String[] pages) {
        for (int i = 0; i < pages.length; i++) {
            pages[i] = pages[i].toLowerCase();
        }

        List<Page> pageList = new ArrayList<>();

        int index = 0;
        for (String page : pages) {
            String pageName = findPageName(page);
            int count = wordCount(word.toLowerCase(), page);
            Page p = new Page(index++, pageName, count);
            pageHashMap.put(pageName, p);
            pageList.add(p);
        }

        for (int i = 0; i < pages.length; i++) {
            Page parent = pageList.get(i);
            List<String> links = findLinkedPages(pages[i]);
            for (String linkedPage : links) {
                Page child = pageHashMap.get(linkedPage);
                parent.addLink(child);
            }
        }

        for (Page p : pageList) {
            p.addScoreToLinks();
        }

        for (Page p : pageList) {
            p.setTotalScore();
        }

        return pageList.stream().min((Page::compareTo)).get().index;
    }


    private String findPageName(String page) {
        Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"/>");
        Matcher matcher = pattern.matcher(page);

        matcher.find();
        String str = matcher.group();
        String ret = str.split("\"")[3];
        return ret;
    }

    private int wordCount(String word, String page) {
        String p = "([^a-z]*)("+word+")[^a-z]";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(page);
        return (int) matcher.results().count();
    }

    private List<String> findLinkedPages(String page) {
        List<String> ret = new ArrayList<>();
        Pattern pattern = Pattern.compile("<a href=\"https://(\\S*)\">");
        Matcher matcher = pattern.matcher(page);
        while (matcher.find()) {
            String link = matcher.group();
            link = link.split("\"")[1];
            ret.add(link);
        }
        return ret;
    }

    static class Page implements Comparable<Page> {
        private final int index;
        private final String name;
        private final double baseScore;
        private List<Page> links = new ArrayList<>();
        private double linkScore = 0;
        private double totalScore;

        private Page(int index, String name, double baseScore) {
            this.index = index;
            this.name = name;
            this.baseScore = baseScore;
        }

        private void addLink(Page p) {
            links.add(p);
        }

        private void addScoreToLinks() {
            if (links.size() == 0) {
                return;
            }
            double adder = baseScore / links.size();
            for (Page p : links) {
                if (p != null) {
                    p.linkScore += adder;
                }
            }
        }

        private void setTotalScore() {
            totalScore = baseScore + linkScore;
        }

        @Override
        public int compareTo(Page o) {
            return totalScore != o.totalScore ? Double.compare(o.totalScore, totalScore) : Integer.compare(index, o.index);
        }

        @Override
        public String toString() {
            return String.format("%s [%f, %f, %f]", name, baseScore, linkScore, totalScore);
        }
    }
}