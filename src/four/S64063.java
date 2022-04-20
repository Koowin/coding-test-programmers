/*
    https://programmers.co.kr/learn/courses/30/lessons/64063
    호텔 방 배정
 */
package four;

import java.util.HashMap;
import java.util.Map;

public class S64063 {
    Map<Long, Long> roomMap = new HashMap<>();
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        int index = 0;
        for (long l : room_number) {
            answer[index++] = find(l);
        }
        return answer;
    }

    private long find(long request){
        Long room = roomMap.get(request);
        if (room == null) {
            roomMap.put(request, request + 1);
            return request;
        }
        room = find(room);
        roomMap.put(request, room + 1);
        return room;
    }
}
