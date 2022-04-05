package two;

import java.util.*;

public class S67257 {

    public static void main(String[] args) {
        S67257 s = new S67257();
        long result = s.solution("100-200*300-500+20");
        System.out.println(result);
    }

    public long solution(String expression) {
        MaxResultFinder finder = new MaxResultFinder(expression);
        long result = finder.getMaxResult();
        return result;
    }

    private enum Operation {
        PLUS("+", 0) {
            public long apply(long x, long y) {
                return x + y;
            }
        },
        MINUS("-", 1) {
            public long apply(long x, long y) {
                return x - y;
            }
        },
        MULTIPLY("*", 2) {
            public long apply(long x, long y) {
                return x * y;
            }
        };

        private final String symbol;
        private final int id;

        Operation(String symbol, int id) {
            this.symbol = symbol;
            this.id = id;
        }

        public static Operation findBySymbol(String s) {
            for (Operation op : Operation.values()) {
                if (op.symbol.equals(s)) {
                    return op;
                }
            }
            return null;
        }

        public static Operation findById(int id) {
            for (Operation op : Operation.values()) {
                if (op.id == id) {
                    return op;
                }
            }
            return null;
        }

        public abstract long apply(long x, long y);
    }

    static class MaxResultFinder {
        private List<Long> numberList = new LinkedList<>();
        private List<Operation> operationList = new LinkedList<>();
        private long maxResult = Long.MIN_VALUE;
        private boolean[] isUsed = new boolean[3];

        private MaxResultFinder(String expression) {
            parseExpression(expression);
        }

        private void parseExpression(String expression) {
            String[] numberStringArray = expression.split("[-+*]");
            for (String str : numberStringArray) {
                numberList.add(Long.parseLong(str));
            }

            String[] operationArray = expression.split("\\d+");
            for (int i = 1; i < operationArray.length; i++) {
                String symbol = operationArray[i];
                operationList.add(Operation.findBySymbol(symbol));
            }
        }

        private long getMaxResult() {
            findResult(numberList, operationList);
            return maxResult;
        }

        private void findResult(List<Long> numberList, List<Operation> operationList) {
            if (numberList.size() == 1) {
                maxResult = Math.max(maxResult, Math.abs(numberList.get(0)));
                return;
            }

            for (int i = 0; i < 3; i++) {
                if (!isUsed[i]) {
                    isUsed[i] = true;
                    List<Long> newNumberList = new LinkedList<>();
                    List<Operation> newOperationList = new LinkedList<>();

                    newNumberList.addAll(numberList);
                    newOperationList.addAll(operationList);

                    applyOperation(i, newNumberList, newOperationList);
                    findResult(newNumberList, newOperationList);
                    isUsed[i] = false;
                }
            }
        }

        private void applyOperation(int operationId, List<Long> numberList, List<Operation> operationList) {
            Operation targetOp = Operation.findById(operationId);

            Iterator<Operation> iter = operationList.iterator();
            int i = 0;
            while (iter.hasNext()) {
                Operation op = iter.next();
                if (targetOp == op) {
                    long newNumber = op.apply(numberList.get(i), numberList.get(i + 1));
                    numberList.remove(i + 1);
                    numberList.set(i, newNumber);
                    iter.remove();
                }
                else {
                    i++;
                }
            }
        }
    }
}
