package two;

import java.util.*;

public class S17680 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Cache cache = new Cache(cacheSize);

        for (String page : cities) {
            answer += cache.getExecuteTime(page);
        }
        return answer;
    }
}

class Cache {
    private final int cacheSize;
    LinkedList<String> pages = new LinkedList<>();

    Cache(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public int getExecuteTime(String input) {
        final int CACHE_HIT_TIME = 1;
        final int CACHE_MISS_TIME = 5;

        if (findPage(input.toLowerCase())) {
            return CACHE_HIT_TIME;
        }
        return CACHE_MISS_TIME;
    }

    private boolean findPage(String input) {
        if (cacheSize == 0) {
            return false;
        }
        int index = pages.indexOf(input);
        if (index == -1) {
            if (pages.size() == cacheSize) {
                pages.remove(0);
            }
            pages.add(input);
            return false;
        }
        pages.remove(index);
        pages.add(input);
        return true;
    }
}