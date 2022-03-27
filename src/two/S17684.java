/*
    https://programmers.co.kr/learn/courses/30/lessons/17684
    [3차] 압축
 */
package two;

import java.util.*;

public class S17684 {
    public int[] solution(String msg){
        LZW lzw = new LZW(msg);
        return lzw.getAnswer();
    }
}

class LZW{
    Node root = new Node(0);
    private final int[] intStream;
    private List<Integer> compressed = new ArrayList<>();
    private int nextNodeIndex = 27;
    private int streamIndex = 0;

    public LZW(String strStream){
        init();
        intStream = parseStrStreamToIntStream(strStream);
    }

    private void init(){
        for(int i=0;i<26;i++){
            root.addNode(i, new Node(i+1));
        }
    }

    private static int[] parseStrStreamToIntStream(String strStream){
        int[] ret = new int[strStream.length()];
        for(int i = 0;i <ret.length;i++){
            ret[i] = strStream.charAt(i) - 'A';
        }
        return ret;
    }

    public int[] getAnswer(){
        while(compress());
        return compressed.stream().mapToInt(i->i).toArray();
    }

    private boolean compress(){
        Node n = root;
        while(streamIndex < intStream.length && n.children[intStream[streamIndex]] != null){
            n = n.children[intStream[streamIndex++]];
        }
        compressed.add(n.index);
        if(streamIndex == intStream.length){
            return false;
        }
        n.addNode(intStream[streamIndex], new Node(nextNodeIndex++));

        return true;
    }

    static class Node{
        private final int index;
        private Node[] children = new Node[26];

        private Node(int index){
            this.index = index;
        }

        private void addNode(int i, Node n){
            children[i] = n;
        }
    }
}
