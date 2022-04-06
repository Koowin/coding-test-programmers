/*
    https://programmers.co.kr/learn/courses/30/lessons/72411
    메뉴 리뉴얼
 */

package two;

import java.util.*;

public class S72411 {
    private Menu[] orders;
    private List<Integer> answerList = new ArrayList<>();

    public static void main(String[] args) {
        S72411 s = new S72411();
        String[] input1 = new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] input2 = new int[]{2, 3, 4};

        String[] result = s.solution(input1, input2);
        System.out.println(Arrays.toString(result));
    }

    public String[] solution(String[] orders, int[] course) {
        makeOrders(orders);

        for (int menuCount : course) {
            findAndAddAnswer(menuCount);
        }

        String[] ret = new String[answerList.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = Menu.intToStringMenu(answerList.get(i));
        }
        Arrays.sort(ret);
        return ret;
    }

    private void makeOrders(String[] orders) {
        this.orders = new Menu[orders.length];

        for (int i = 0; i < orders.length; i++) {
            this.orders[i] = new Menu(orders[i]);
        }
    }

    private void findAndAddAnswer(int menuCount) {
        Set<Integer> subMenuSet = new HashSet<>();

        for (Menu m : orders) {
            m.addSubMenuToSet(subMenuSet, menuCount);
        }

        addMaxCountSubMenus(subMenuSet);
    }

    private void addMaxCountSubMenus(Set<Integer> subMenuSet) {
        int max = 2;
        Set<Integer> answerCandidate = new HashSet<>();
        for (int subMenu : subMenuSet) {
            int count = 0;
            for (Menu m : orders) {
                if (Menu.isContain(m, subMenu)) {
                    count++;
                }
            }

            if (count > max) {
                answerCandidate.clear();
                answerCandidate.add(subMenu);
                max = count;
            } else if (count == max) {
                answerCandidate.add(subMenu);
            }
        }
        answerList.addAll(answerCandidate);
    }

    private static class Menu {
        private final int menu;

        private Menu(String strMenu) {
            menu = stringToIntMenu(strMenu);
        }

        private static int stringToIntMenu(String strMenu) {
            char A = 'A';
            int ret = 0;
            for (int i = 0; i < strMenu.length(); i++) {
                int diff = strMenu.charAt(i) - A;
                ret += 1 << diff;
            }
            return ret;
        }

        private static String intToStringMenu(int intMenu) {
            char c = 'A';
            StringBuilder stringBuilder = new StringBuilder();

            while (intMenu > 0) {
                if (intMenu % 2 == 1) {
                    stringBuilder.append(c);
                }
                c++;
                intMenu /= 2;
            }
            return stringBuilder.toString();
        }

        private static boolean isContain(Menu m, int subMenu) {
            return (m.menu & subMenu) == subMenu;
        }

        private void addSubMenuToSet(Set<Integer> subMenuSet, int count) {
            for (int subMenu = menu; subMenu > 0; subMenu = (subMenu - 1) & menu) {
                if (Integer.bitCount(subMenu) == count) {
                    subMenuSet.add(subMenu);
                }
            }
        }
    }
}
