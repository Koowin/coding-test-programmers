/*
    단체사진 찍기
    https://programmers.co.kr/learn/courses/30/lessons/1835
 */
package two;

import java.util.*;

public class S1835 {
    public int solution(int n, String[] data) {
        Sequence s = new Sequence(data);
        s.dfs(0,new ArrayList<Character>());
        return s.getAnswer();
    }
    static class Sequence{
        private int answer = 0;
        private final String[] restrictions;
        private final Character[] members = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

        Sequence(String[] data){
            this.restrictions = data;
        }

        private void dfs(int count, ArrayList<Character> seq){
            if(seq.size() == 8){
                checkRestriction(seq);
                return;
            }

            for(Character member : members){
                if(!seq.contains(member)){
                    ArrayList<Character> ret = (ArrayList<Character>) seq.clone();
                    ret.add(member);
                    dfs(count+1, ret);
                }
            }
        }

        private void checkRestriction(ArrayList<Character> seq){
            for(String restriction : restrictions){
                char a = restriction.charAt(0);
                char b = restriction.charAt(2);
                char op = restriction.charAt(3);
                int gap = restriction.charAt(4) - '0';
                int ai = seq.indexOf(a);
                int bi = seq.indexOf(b);
                int realGap = Math.abs(ai - bi) - 1;

                if(op == '>'){
                    if(realGap <= gap){
                        return;
                    }
                }else if(op == '<'){
                    if(realGap >= gap){
                        return;
                    }
                }else if(realGap != gap){
                    return;
                }
            }
            answer++;
        }


        private int getAnswer(){
            return answer;
        }
    }
}
