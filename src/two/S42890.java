package two;

import java.util.*;

public class S42890 {
    public static void main(String[] args) {
        String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        KeyFinder kf = new KeyFinder(relation);
    }
    public int solution(String[][] relation) {
        KeyFinder kf = new KeyFinder(relation);
        return 0;
    }
}

class KeyFinder {
    private int[][] data;
    private final int ROW_SIZE;
    private final int COL_SIZE;

    public KeyFinder(String[][] relation){
        ROW_SIZE = relation.length;
        COL_SIZE = relation[0].length;

        data = new int[ROW_SIZE][COL_SIZE];

        for(int j=0;j<COL_SIZE;j++){
            Map<String, Integer> map = new HashMap<>();
            int index = 0;
            for(int i=0;i<ROW_SIZE;i++){
                if(map.keySet().contains(relation[i][j])){
                    data[i][j] = map.get(relation[i][j]);
                }
                else{
                    data[i][j] = index;
                    map.put(relation[i][j], index++);
                }
            }
        }

        print();
    }

    private void print(){
        for(int[] arr : data){
            for(int i : arr){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    static class ColumnManager {
        private boolean[] isUsed;
        private int isUsedCount = 0;
        private final int COL_SIZE;

        private ColumnManager(int size){
            this.COL_SIZE = size;
        }
    }
}