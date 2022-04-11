package three;

import java.util.*;

public class S81303 {
    private Row cursor;
    Deque<Row> deleteStack = new LinkedList<>();

    public String solution(int n, int k, String[] cmd) {
        makeLinkedRow(n);
        down(k);

        for (String command : cmd) {
            executeCommand(command);
        }

        return rowToString(n);
    }

    private void makeLinkedRow(int n) {
        cursor = new Row(0);
        Row r = cursor;
        for (int i = 1; i < n; i++) {
            Row newRow = new Row(i);
            r.add(newRow);
            r = newRow;
        }
    }

    private void executeCommand(String command) {
        char c = command.charAt(0);
        switch(c){
            case 'U':
                up(Integer.parseInt(command.substring(2)));
                break;
            case 'D':
                down(Integer.parseInt(command.substring(2)));
                break;
            case 'C':
                delete();
                break;
            case 'Z':
                undo();
                break;
        }
    }

    private void up(int len) {
        for (int i = 0; i < len; i++) {
            cursor = cursor.prev;
        }
    }

    private void down(int len) {
        for (int i = 0; i < len; i++) {
            cursor = cursor.next;
        }
    }

    private void delete() {
        deleteStack.push(cursor);
        cursor = cursor.delete();
    }

    private void undo() {
        Row r = deleteStack.pop();
        r.undo();
    }

    public String rowToString(int n) {
        StringBuilder stringBuilder = new StringBuilder(n);
        while (cursor.prev != null) {
            cursor = cursor.prev;
        }

        char O = 'O';
        char X = 'X';

        for (int i = 0; i < n; i++) {
            if (cursor != null && i == cursor.index) {
                cursor = cursor.next;
                stringBuilder.append(O);
            } else {
                stringBuilder.append(X);
            }
        }
        return stringBuilder.toString();
    }

    static class Row {
        private final int index;
        private Row prev;
        private Row next;

        private Row(int index) {
            this.index = index;
        }

        private void add(Row r) {
            next = r;
            r.prev = this;
        }

        private Row delete() {
            Row ret;
            if (prev == null) {
                ret = next;
                next.prev = null;
            } else if (next == null) {
                ret = prev;
                prev.next = null;
            } else {
                ret = next;
                prev.next = next;
                next.prev = prev;
            }
            return ret;
        }

        private void undo() {
            if (prev != null) {
                prev.next = this;
            }
            if (next != null) {
                next.prev = this;
            }
        }
    }
}