/*
    https://programmers.co.kr/learn/courses/30/lessons/43164
    여행경로
 */

package three;

import java.util.*;

public class S43164 {
    public String[] solution(String[][] tickets) {
        Map<String, Airport> airports = new HashMap<>();

        for (String[] ticket : tickets) {
            Airport parent = airports.get(ticket[0]);
            Airport child = airports.get(ticket[1]);

            if (parent == null) {
                parent = new Airport(ticket[0]);
                airports.put(ticket[0], parent);
            }
            if (child == null) {
                child = new Airport(ticket[1]);
                airports.put(ticket[1], child);
            }

            parent.addChild(child);
        }

        for (Airport a : airports.values()) {
            a.sortChildren();
        }

        Airport root = airports.get("ICN");
        root.ticketCount = tickets.length;

        String[] answer = new String[tickets.length + 1];
        root.findPath(0, answer);
        return answer;
    }

    static class Airport implements Comparable<Airport>{
        private static int ticketCount;
        private final String name;
        private List<Airport> children = new ArrayList<>();
        private boolean[] isVisit;

        private Airport(String name) {
            this.name = name;
        }

        private void addChild(Airport c) {
            children.add(c);
        }

        private void sortChildren() {
            Collections.sort(children);
            isVisit = new boolean[children.size()];
        }

        private boolean findPath(int count, String[] path) {
            path[count] = name;

            if (count == ticketCount) {
                return true;
            }

            for (int i=0;i<isVisit.length;i++) {
                if(!isVisit[i]) {
                    isVisit[i] = true;
                    if (children.get(i).findPath(count + 1, path)) {
                        return true;
                    }
                    isVisit[i] = false;
                }
            }
            return false;
        }

        @Override
        public int compareTo(Airport o) {
            return name.compareTo(o.name);
        }
    }
}
