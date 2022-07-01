package three;

import java.util.*;

public class S81303 {
    private static final char UP_SYMBOL = 'U';
    private static final char DOWN_SYMBOL = 'D';
    private static final char DELETE_SYMBOL = 'C';

    public String solution(int n, int k, String[] cmd) {
        Record cursor = initRecord(n, k);

        for(String s : cmd) {
            switch(s.charAt(0)) {
                case UP_SYMBOL:
                    cursor = Record.cursorUp(cursor, Integer.parseInt(s.substring(2)));
                    break;
                case DOWN_SYMBOL:
                    cursor = Record.cursorDown(cursor, Integer.parseInt(s.substring(2)));
                    break;
                case DELETE_SYMBOL:
                    cursor = cursor.delete();
                    break;
                default:
                    Record.undo();
                    break;
            }
        }

        return getAnswer(n);
    }

    private String getAnswer(int n) {
        char X = 'X';
        char O = 'O';

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++) {
            sb.append(O);
        }

        for(Record r : Record.stack) {
            sb.setCharAt(r.index, X);
        }
        return sb.toString();
    }

    private Record initRecord(int n, int k) {
        Record root = new Record(0);
        Record r = root;

        for(int i=1;i<n;i++) {
            r = r.addRecord(i);
        }

        return Record.cursorDown(root, k);
    }

    private static class Record {
        private static Deque<Record> stack = new LinkedList<>();

        private final int index;
        private Record prev;
        private Record next;

        private Record(int index) {
            this.index = index;
        }

        private Record addRecord(int n) {
            Record r = new Record(n);
            this.next = r;
            r.prev = this;

            return r;
        }

        private Record delete() {
            stack.push(this);

            if(prev != null) {
                prev.next = this.next;
            }
            if(next != null) {
                next.prev = this.prev;
                return next;
            }
            return prev;
        }

        private static Record cursorUp(Record r, int n) {
            for(int i=0;i<n;i++) {
                r = r.prev;
            }
            return r;
        }

        private static Record cursorDown(Record r, int n) {
            for(int i=0;i<n;i++) {
                r = r.next;
            }
            return r;
        }

        private static void undo() {
            Record r = stack.pop();
            if(r.prev != null) {
                r.prev.next = r;
            }
            if(r.next != null) {
                r.next.prev = r;
            }
        }
    }
}