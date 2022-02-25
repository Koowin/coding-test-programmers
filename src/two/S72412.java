/*
    순위 검색
    https://programmers.co.kr/learn/courses/30/lessons/72412
 */
package two;

import java.util.*;

public class S72412 {
    public int[] solution(String[] info, String[] query){
        int[] answer = new int[query.length];

        ApplicantsManager am = new ApplicantsManager();
        for(String str : info){
            am.addApplicant(str);
        }
        am.sort();
        int i=0;
        for(String str : query){
            answer[i++] = am.executeQuery(str);
        }
        return answer;
    }

    static class ApplicantsManager {
        private static final int CPP = 0;
        private static final int JAVA = 8;
        private static final int PYTHON = 16;

        private static final int BACKEND = 0;
        private static final int FRONTEND = 4;

        private static final int JUNIOR = 0;
        private static final int SENIOR = 2;

        private static final int CHICKEN = 0;
        private static final int PIZZA = 1;

        private List<Integer>[] applicants = new ArrayList[24];

        ApplicantsManager(){
            for(int i=0;i<24;i++){
                applicants[i] = new ArrayList<>();
            }
        }

        public void addApplicant(String str) {
            String[] arr = str.split(" ");
            int type = getType(arr);
            applicants[type].add(Integer.parseInt(arr[4]));
        }

        private int getType(String[] arr){
            int type = 0;
            switch(arr[0].charAt(0)){
                case 'c':
                    type += CPP;
                    break;
                case 'j':
                    type += JAVA;
                    break;
                default:
                    type += PYTHON;
                    break;
            }

            if(arr[1].charAt(0) == 'b'){
                type += BACKEND;
            }
            else{
                type += FRONTEND;
            }

            if(arr[2].charAt(0) == 'j'){
                type += JUNIOR;
            }
            else{
                type += SENIOR;
            }

            if(arr[3].charAt(0) == 'c'){
                type += CHICKEN;
            }
            else{
                type += PIZZA;
            }
            return type;
        }

        public void sort(){
            for(int i=0;i<24;i++){
                Collections.sort(applicants[i]);
            }
        }

        public int executeQuery(String query){
            String[] arr = query.split(" ");
            List<Integer> searchList = getSearchList(arr);

            int count = 0;
            for(Integer i : searchList){
                count += getCount(i, Integer.parseInt(arr[7]));
            }
            return count;
        }

        private List<Integer> getSearchList(String[] arr){
            List<Integer> searchList = new ArrayList<>();
            switch(arr[6].charAt(0)){
                case 'c':
                    searchList.add(CHICKEN);
                    break;
                case 'p':
                    searchList.add(PIZZA);
                    break;
                default:
                    searchList.add(CHICKEN);
                    searchList.add(PIZZA);
                    break;
            }

            switch(arr[4].charAt(0)){
                case 'j':
                    break;
                case 's':
                    for(int i=0, size= searchList.size();i<size;i++){
                        searchList.set(i, searchList.get(i) + SENIOR);
                    }
                    break;
                default:
                    int size = searchList.size();
                    for(int i=0;i<size;i++){
                        searchList.add(searchList.get(i));
                    }
                    for(int i=0; i<size;i++){
                        searchList.set(i, searchList.get(i) + SENIOR);
                    }
                    break;
            }

            switch(arr[2].charAt(0)){
                case 'b':
                    break;
                case 'f':
                    for(int i=0, size= searchList.size();i<size;i++){
                        searchList.set(i, searchList.get(i) + FRONTEND);
                    }
                    break;
                default:
                    int size = searchList.size();
                    for(int i=0;i<size;i++){
                        searchList.add(searchList.get(i));
                    }
                    for(int i=0; i<size;i++){
                        searchList.set(i, searchList.get(i) + FRONTEND);
                    }
                    break;
            }

            switch(arr[0].charAt(0)){
                case 'c':
                    break;
                case 'j':
                    for(int i=0, size= searchList.size();i<size;i++){
                        searchList.set(i, searchList.get(i) + JAVA);
                    }
                    break;
                case 'p':
                    for(int i=0, size= searchList.size();i<size;i++){
                        searchList.set(i, searchList.get(i) + PYTHON);
                    }
                    break;
                default:
                    int size = searchList.size();
                    for(int i=0;i<size;i++){
                        searchList.add(searchList.get(i));
                    }
                    for(int i=0;i<size;i++){
                        searchList.add(searchList.get(i));
                    }
                    for(int i=0; i<size;i++){
                        searchList.set(i, searchList.get(i) + JAVA);
                    }
                    for(int i=size; i<size*2;i++){
                        searchList.set(i, searchList.get(i) + PYTHON);
                    }
                    break;
            }
            return searchList;
        }

        private int getCount(int index, int score){
            List<Integer> list = applicants[index];
            int i=0;
            for(Integer s : list){
                if(s >= score){
                    break;
                }
                i++;
            }
            return list.size() - i;
        }
    }
}
