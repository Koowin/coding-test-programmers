/*
    [카카오 인턴] 동굴 탐험
    https://programmers.co.kr/learn/courses/30/lessons/67260
 */
package four;

import java.util.*;

public class S67260 {
    private Room[] rooms;

    public boolean solution(int n, int[][] path, int[][] order) {
        rooms = new Room[n];
        for (int i = 0; i < n; i++) {
            rooms[i] = new Room(i);
        }

        for (int[] p : path) {
            Room.addPath(rooms[p[0]], rooms[p[1]]);
        }

        for (int[] o : order) {
            Room.lockRoom(rooms[o[0]], rooms[o[1]]);
        }

        rooms[0].visit();

        return Room.closedRoom.isEmpty();
    }

    static class Room {
        private static Set<Room> closedRoom = new HashSet<>();
        private final int index;
        private List<Room> path = new ArrayList<>();
        private boolean isVisited = false;
        private boolean isClosed = false;
        private Room unlockRoom = null;

        public Room(int index) {
            this.index = index;
        }

        private static void addPath(Room r1, Room r2) {
            r1.path.add(r2);
            r2.path.add(r1);
        }

        private static void lockRoom(Room key, Room lock) {
            key.unlockRoom = lock;
            lock.isClosed = true;
        }

        private void visit() {
            if (isClosed) {
                closedRoom.add(this);
                return;
            }
            isVisited = true;
            if (unlockRoom != null) {
                unlockRoom.isClosed = false;
                if (closedRoom.remove(unlockRoom)) {
                    unlockRoom.visit();
                }
            }
            for (Room r : path) {
                if(!r.isVisited) {
                    r.visit();
                }
            }
        }

        @Override
        public String toString() {
            return Integer.toString(index);
        }

        @Override
        public int hashCode() {
            return index;
        }
    }
}