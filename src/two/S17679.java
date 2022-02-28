/*
    [1차] 프렌즈4블록
    https://programmers.co.kr/learn/courses/30/lessons/17679
 */
package two;

import java.util.*;

public class S17679 {
    public int solution(int m, int n, String[] board) {
        BoardManager bm = new BoardManager(m,n,board);
        bm.deleteAllPossibleBlocks();
        return bm.getCountOfDeletedBlocks();
    }
}

class BoardManager{
    private final int height;
    private final int width;
    private List<Block>[] blocks;

    public BoardManager(int m, int n, String[] b){
        height = m;
        width = n;

        blocks = new List[n];
        for(int i=0;i<width;i++){
            blocks[i] = new LinkedList<Block>();
        }

        for(int i=0;i<width;i++){
            for(int j=height-1;j>=0;j--){
                blocks[i].add(new Block(b[j].charAt(i)));
            }
        }
    }

    public void deleteAllPossibleBlocks(){
        while(markBlocks()){
            deleteMarkedBlocks();
        }
    }

    private boolean markBlocks(){
        boolean isMarkSomething = false;
        for(int i=0;i<width-1;i++){
            for(int j=0;j<blocks[i].size() - 1;j++){
                if(isDeletable(i, j)){
                    isMarkSomething = true;
                    mark(i, j);
                }
            }
        }
        return isMarkSomething;
    }

    private boolean isDeletable(int i, int j){
        if(blocks[i+1].size() <= j+1){
            return false;
        }
        char c = blocks[i].get(j).c;
        if(blocks[i].get(j+1).c == c && blocks[i+1].get(j).c == c && blocks[i+1].get(j+1).c == c){
            return true;
        }
        return false;
    }

    private void mark(int i, int j){
        blocks[i].get(j).isMarked = true;
        blocks[i].get(j+1).isMarked = true;
        blocks[i+1].get(j).isMarked = true;
        blocks[i+1].get(j+1).isMarked = true;
    }

    private void deleteMarkedBlocks(){
        for(int i=0;i<width;i++){
            Iterator<Block> iter = blocks[i].iterator();
            while(iter.hasNext()){
                if(iter.next().isMarked){
                    iter.remove();
                }
            }
        }
    }

    public int getCountOfDeletedBlocks(){
        int count = height * width;
        for(int i=0;i<width;i++){
            count -= blocks[i].size();
        }
        return count;
    }

    static class Block{
        private final char c;
        private boolean isMarked = false;

        private Block(char c){
            this.c = c;
        }

        @Override
        public String toString(){
            return Character.toString(c);
        }
    }
}
