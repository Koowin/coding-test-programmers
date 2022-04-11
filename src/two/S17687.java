/*
    https://programmers.co.kr/learn/courses/30/lessons/17687
    [3차]n진수 게임
 */
package two;

public class S17687 {
    public String solution(int n, int t, int m, int p) {

        Counter c = new Counter(n);
        StringBuilder stringBuilder = new StringBuilder(t);
        stringBuilder.append(c.next(p-1));
        for(int i=1;i<t;i++) {
            stringBuilder.append(c.next(m));
        }
        return stringBuilder.toString();
    }

    static class Counter {
        private final int notation;
        private int number = 0;
        private int nextDigitUpNumber;
        private int digitCount = 1;
        private int digitIndex = 0;

        private Counter(int notation) {
            this.notation = notation;
            nextDigitUpNumber = notation;
        }

        private char next(int n) {
            int newNumber = number + n / digitCount;
            int newDigitIndex = digitIndex + n % digitCount;
            if (newDigitIndex >= digitCount) {
                newDigitIndex -= digitCount;
                newNumber++;
            }
            if (needDigitUp(newNumber)) {
                int left = addUntilDigitUp(n);
                return next(left);
            } else {
                number = newNumber;
                digitIndex = newDigitIndex;
            }
            return getChar();
        }

        private boolean needDigitUp(int n) {
            return n >= nextDigitUpNumber;
        }

        private int addUntilDigitUp(int n) {
            n -= (digitCount - 1 - digitIndex);
            n -= (nextDigitUpNumber - 1 - number) * digitCount;
            digitUp();
            return n - 1;
        }

        private void digitUp() {
            number = nextDigitUpNumber;
            digitIndex = 0;
            nextDigitUpNumber *= notation;
            digitCount++;
        }

        private char getChar() {
            int n = digitCount - digitIndex - 1;
            int ret = number;
            for(int i=0;i<n;i++) {
                ret /= notation;
            }
            ret %= notation;
            return intToChar(ret);
        }

        private char intToChar(int n) {
            if(n < 10) {
                return (char) (n + '0');
            } else {
                n -= 10;
                return (char) (n + 'A');
            }
        }
    }
}
