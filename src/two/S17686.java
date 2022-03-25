/*
    https://programmers.co.kr/learn/courses/30/lessons/17686
    [3차] 파일명 정렬
 */
package two;

import java.util.*;

public class S17686 {
    public String[] solution(String[] files) {
        List<File> fileList = new ArrayList<>(files.length);
        for(int i =0;i<files.length;i++){
            File f = new File(files[i], i);
            fileList.add(f);
        }

        Collections.sort(fileList);
        String[] answer = new String[files.length];
        int i = 0;
        for(File f : fileList){
            answer[i++] = f.toString();
        }
        return answer;
    }

    static class File implements Comparable<File>{
        private final String name;
        private final String HEAD;
        private final int NUMBER;
        private final int index;

        private File(String name, int index){
            this.index = index;
            this.name = name;

            int i = 0;
            for(int size = name.length();i<size;i++){
                char c = name.charAt(i);
                if(c >= '0' && c <= '9'){
                    break;
                }
            }
            HEAD = name.substring(0, i).toLowerCase();
            NUMBER = findNumber(name.substring(i, name.length()));
        }

        private int findNumber(String str){
            int i = 0;
            for(;i<str.length();i++){
                char c = str.charAt(i);
                if(c < '0' || c > '9'){
                    break;
                }
            }
            return Integer.parseInt(str.substring(0, i));
        }

        @Override
        public String toString(){
            return name;
        }

        @Override
        public int compareTo(File f){
            if(HEAD.equals(f.HEAD)){
                if(NUMBER == f.NUMBER){
                    return Integer.compare(index, f.index);
                }
                return Integer.compare(NUMBER, f.NUMBER);
            }
            return HEAD.compareTo(f.HEAD);
        }
    }
}
