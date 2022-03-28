/*
    https://programmers.co.kr/learn/courses/30/lessons/87390
    n^2 배열 자르기
 */
package two;

public class S87390 {
    public int[] solution(int n, long left, long right) {
        ArrayManager am = new ArrayManager(n, left, right);
        am.fillList();
        return am.getList();
    }

    static class ArrayManager{
        private final int size;
        private int[] list;
        private int nextIndex = 0;

        private final int leftRow;
        private final int leftColumn;
        private final int rightRow;
        private final int rightColumn;

        private ArrayManager(int n, long left, long right) {
            size = n;
            int listSize = (int) (right - left + 1);
            list = new int[listSize];

            leftRow = (int) (left / n);
            leftColumn = (int) (left % n);
            rightRow = (int) (right / n);
            rightColumn = (int) (right % n);
        }

        private void fillList() {
            if (leftRow == rightRow) {
                fillRow(leftRow, leftColumn, rightColumn + 1);
            }
            else {
                fillRow(leftRow, leftColumn, size);
                for (int i = leftRow + 1; i < rightRow; i++) {
                    fillRow(i, 0, size);
                }
                fillRow(rightRow, 0, rightColumn + 1);
            }
        }

        private void fillRow(int rowIndex, int from, int to) {
            for (int i = from; i < to; i++) {
                list[nextIndex++] = Math.max(rowIndex, i) + 1;
            }
        }

        private int[] getList() {
            return list;
        }
    }
}
