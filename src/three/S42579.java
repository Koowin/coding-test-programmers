/*
    https://programmers.co.kr/learn/courses/30/lessons/42579
    베스트앨범
 */

package three;

import java.util.*;

public class S42579 {
    private Map<String, GenreGroup> genreGroups = new HashMap<>();

    public int[] solution(String[] genres, int[] plays) {
        for (int i=0;i<genres.length;i++) {
            GenreGroup g = genreGroups.get(genres[i]);
            if(g == null) {
                g = new GenreGroup();
                g.addSong(i, plays[i]);
                genreGroups.put(genres[i], g);
            }
            else {
                g.addSong(i, plays[i]);
            }
        }

        List<GenreGroup> genreGroupList = new ArrayList<>(genreGroups.values());
        Collections.sort(genreGroupList);

        List<Integer> bestAlbum = new ArrayList<>();
        for (GenreGroup g : genreGroupList) {
            g.appendPopularSongs(bestAlbum);
        }

        return bestAlbum.stream().mapToInt(i -> i).toArray();
    }
}

class GenreGroup implements Comparable<GenreGroup>{
    private int totalPlayCount = 0;
    List<Song> songList = new ArrayList<>();

    public void addSong(int index, int playCount) {
        totalPlayCount += playCount;
        songList.add(new Song(index, playCount));
    }

    public void appendPopularSongs(List<Integer> indexList) {
        Collections.sort(songList);
        indexList.add(songList.get(0).index);
        if (songList.size() > 1) {
            indexList.add(songList.get(1).index);
        }
    }

    @Override
    public int compareTo(GenreGroup o) {
        return Integer.compare(o.totalPlayCount, totalPlayCount);
    }

    private static class Song implements Comparable<Song>{
        private final int index;
        private final int playCount;

        private Song(int index, int playCount) {
            this.index = index;
            this.playCount = playCount;
        }

        @Override
        public int compareTo(Song o) {
            return playCount != o.playCount ? Integer.compare(o.playCount, playCount)
                    : Integer.compare(index, o.index);
        }
    }
}
