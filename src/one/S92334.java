/*
    신고 결과 받기
    https://programmers.co.kr/learn/courses/30/lessons/92334
 */

package one;

import java.util.*;

public class S92334 {
    public int[] solution(String[] id_list, String[] report, int k) {
        IdManager idManager = new IdManager();
        ReportManager reportManager = new ReportManager(id_list.length, k);

        for(int i=0, size=id_list.length;i<size;i++){
            idManager.addId(id_list[i], i);
        }

        for(String str : report){
            String[] arr = str.split(" ");
            reportManager.report(idManager.getIndex(arr[0]), idManager.getIndex(arr[1]));
        }

        return reportManager.getAnswer();
    }
    class IdManager{
        HashMap<String, Integer> idToIndex = new HashMap<>();
        private void addId(String id, int index){
            idToIndex.put(id, index);
        }
        private int getIndex(String id){
            return idToIndex.get(id);
        }
    }

    class ReportManager{
        boolean[][] report;
        int criticalPoint;

        ReportManager(int size, int criticalPoint){
            this.report = new boolean[size][size];
            this.criticalPoint = criticalPoint;
        }

        private void report(int from, int to){
            report[from][to] = true;
        }

        private int[] getAnswer(){
            ArrayList<Integer> banUsers = new ArrayList<>();
            int size = report.length;
            for(int i=0;i<size;i++){
                int count = 0;
                for(int j=0;j<size;j++){
                    if(report[j][i]){
                        count++;
                    }
                }
                if(count >= criticalPoint){
                    banUsers.add(i);
                }
            }
            int[] answer = new int[size];
            for(int i=0, size2=answer.length;i<size2;i++){
                for(int index : banUsers){
                    if(report[i][index]){
                        answer[i]++;
                    }
                }
            }
            return answer;
        }
    }
}
