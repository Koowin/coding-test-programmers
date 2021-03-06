/*
    메뉴 리뉴얼
    https://programmers.co.kr/learn/courses/30/lessons/72411
 */

package two;

import java.util.*;

public class S72411 {

    public String[] solution(String[] orders, int[] course) {
        Menu.initMenuList(course);
        for (String order : orders) {
            for (Menu m : Menu.menuList) {
                m.makeSubMenu(order);
            }
        }
        List<String> answer = new ArrayList<>();
        for (Menu m : Menu.menuList) {
            m.addMaxSubMenus(answer);
        }
        Collections.sort(answer);
        String[] ret = new String[answer.size()];
        int i = 0;
        for (String str : answer) {
            ret[i++] = str;
        }
        return ret;
    }

    static class Menu {
        private static List<Menu> menuList = new ArrayList<>();

        private final int menuCount;
        private Map<Integer, Integer> menuMap = new HashMap<>();

        private Menu(int menuCount) {
            this.menuCount = menuCount;
        }

        private static void initMenuList(int[] course) {
            for (int i : course) {
                menuList.add(new Menu(i));
            }
        }

        private static int parseInt(String order) {
            int ret = 0;
            for (int i = 0; i < order.length(); i++) {
                ret |= 1 << (order.charAt(i) - 'A');
            }
            return ret;
        }

        private static String parseString(int order) {
            StringBuilder ret = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if ((order & 1 << i) != 0) {
                    ret.append((char) ('A' + i));
                }
            }
            return ret.toString();
        }

        private void makeSubMenu(String order) {
            int intOrder = parseInt(order);
            subMenu(intOrder, -1, 0, 0);
        }

        private void subMenu(int order, int lastSelected, int set, int count) {
            if (count == this.menuCount) {
                menuMap.put(set, menuMap.getOrDefault(set, 0) + 1);
                return;
            }
            for (int i = lastSelected + 1; i < 26; i++) {
                if ((order & 1 << i) != 0) {
                    subMenu(order, i, set | 1 << i, count + 1);
                }
            }
        }

        private void addMaxSubMenus(List<String> answer) {
            int max = 1;
            for (int i : menuMap.values()) {
                if (max < i) {
                    max = i;
                }
            }
            if (max < 2) {
                return;
            }
            for (Map.Entry<Integer, Integer> e : menuMap.entrySet()) {
                if (e.getValue() == max) {
                    answer.add(parseString(e.getKey()));
                }
            }
        }
    }
}
