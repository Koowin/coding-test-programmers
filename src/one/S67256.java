/*
    키패드 누르기
    https://programmers.co.kr/learn/courses/30/lessons/67256
 */

package one;

public class S67256 {
    public String solution(int[] numbers, String hand) {
        StringBuilder stringBuilder = new StringBuilder();
        Fingers fingers = new Fingers(hand);
        for (int i : numbers) {
            stringBuilder.append(fingers.move(i));
        }
        return stringBuilder.toString();
    }

    static class KeyPad {
        private final int r;
        private final int c;

        public KeyPad(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Fingers {
        private static final char LEFT = 'L';
        private static final char RIGHT = 'R';

        private KeyPad[] pads = new KeyPad[10];
        private KeyPad leftFinger = new KeyPad(3, 0);
        private KeyPad rightFinger = new KeyPad(3, 2);

        private final boolean priorityLeftHand;

        private Fingers(String priority) {
            if (priority.equals("left")) {
                priorityLeftHand = true;
            } else {
                priorityLeftHand = false;
            }
            initKeyPads();
        }

        private void initKeyPads() {
            pads[0] = new KeyPad(3, 1);
            for (int i = 0; i < 9; i++) {
                int r = i / 3;
                int c = i % 3;
                pads[i + 1] = new KeyPad(r, c);
            }
        }

        private char move(int n) {
            if (isMoveLeft(n)) {
                leftFinger = pads[n];
                return LEFT;
            } else {
                rightFinger = pads[n];
                return RIGHT;
            }
        }

        private boolean isMoveLeft(int n) {
            KeyPad target = pads[n];
            if (target.c == 1) {
                int leftDistance = getDistance(leftFinger, target);
                int rightDistance = getDistance(rightFinger, target);
                if (leftDistance > rightDistance) {
                    return false;
                } else if (leftDistance < rightDistance) {
                    return true;
                }
                return priorityLeftHand;
            }
            return target.c == 0;
        }

        private int getDistance(KeyPad from, KeyPad to) {
            int ret = 0;
            ret += Math.abs(from.r - to.r);
            ret += Math.abs(from.c - to.c);
            return ret;
        }
    }
}