/*
    스킬트리
    https://programmers.co.kr/learn/courses/30/lessons/49993
 */
package two;

public class S49993 {
    String skill;

    public int solution(String skill, String[] skill_trees) {
        this.skill = skill;

        int count = 0;
        for(String str : skill_trees){
            if(isPossible(str)){
                count++;
            }
        }
        return count;
    }

    private boolean isPossible(String skillTree){
        char[] arr = skillTree.toCharArray();
        int index = 0;

        for(char c : arr){
            int i = skill.indexOf(c);
            if(i != -1){
                if(i == index){
                    index++;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }
}
