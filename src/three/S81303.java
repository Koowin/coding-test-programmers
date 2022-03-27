package three;

import java.util.*;

public class S81303 {
    public static void main(String[] args) {
        S81303 s = new S81303();
        System.out.println(s.solution(8, 2, new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
    }

    public String solution(int n, int k, String[] cmd) {
        TableEditor tableEditor = new TableEditor(n, k);
        for (String command : cmd) {
            tableEditor.executeCommand(command);
        }

        return tableEditor.toString();
    }

    static class TableEditor {
        private boolean[] records;
        private int index;
        private int lastIndex;
        private Deque<Integer> deleteStack = new LinkedList<>();

        private TableEditor(int n, int k) {
            records = new boolean[n];
            for (int i = 0; i < n; i++) {
                records[i] = true;
            }
            index = k;
            lastIndex = n - 1;
        }

        private void executeCommand(String command) {
            if (command.length() != 1) {
                int count = Integer.parseInt(command.substring(2, command.length()));
                if (command.charAt(0) == 'D') {
                    downIndex(count);
                } else {
                    upIndex(count);
                }
            } else {
                if (command.charAt(0) == 'C') {
                    delete();
                } else {
                    undoLastDelete();
                }
            }
        }

        private void downIndex(int count) {
            while (count > 0) {
                if (records[++index]) {
                    count--;
                }
            }
        }

        private void upIndex(int count) {
            while (count > 0) {
                if (records[--index]) {
                    count--;
                }
            }
        }

        private void delete() {
            records[index] = false;
            deleteStack.push(index);

            if (index == lastIndex) {
                while (records[--lastIndex]);
                index = lastIndex;
            }
            else {
                downIndex(1);
            }
        }

        private void undoLastDelete() {
            int lastDeletedIndex = deleteStack.pop();
            records[lastDeletedIndex] = true;
            if (lastDeletedIndex > lastIndex) {
                lastIndex = lastDeletedIndex;
            }
        }

        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder();

            char trueChar = 'O';
            char falseChar = 'X';

            for (boolean record : records) {
                if (record) {
                    ret.append(trueChar);
                } else {
                    ret.append(falseChar);
                }
            }

            return ret.toString();
        }
    }
}