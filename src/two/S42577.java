/*
    전화번호 목록
    https://programmers.co.kr/learn/courses/30/lessons/42577
 */
package two;

import java.util.*;

public class S42577 {
    public boolean solution(String[] phone_book){
        PhoneBookManager pbm = new PhoneBookManager();
        Arrays.sort(phone_book, (o1, o2) -> {
            if (o1.length() > o2.length()){
                return 1;
            }
            else if (o1.length() < o2.length()){
                return -1;
            }
            return 0;
        });
        for(String s : phone_book){
            if(pbm.addPhoneNumber(s)){
                return false;
            }
        }
        return true;
    }

    static class PhoneBookManager {
        Set<String> phoneBook = new HashSet<>();

        boolean addPhoneNumber(String s){
            int len = s.length();
            for(int i=1;i<len;i++){
                if(containChecker(s.substring(0, i))){
                    return true;
                }
            }
            phoneBook.add(s);
            return false;
        }

        boolean containChecker(String s){
            if(phoneBook.contains(s)){
                return true;
            }
            return false;
        }
    }
}
