/*
    신규 아이디 추천
    https://programmers.co.kr/learn/courses/30/lessons/72410
 */

package one;

public class S72410 {
    public String solution(String new_id) {
        String ret = new_id;
        for (Process process : Process.values()) {
            ret = process.apply(ret);
        }
        return ret;
    }

    enum Process {
        ONE {
            @Override
            public String apply(String string) {
                return string.toLowerCase();
            }
        }, TWO {
            @Override
            public String apply(String string) {
                return string.replaceAll("[^a-z0-9\\-_.]", "");
            }
        }, THREE {
            @Override
            public String apply(String string) {
                return string.replaceAll("\\.{2,}", ".");
            }
        }, FOUR {
            @Override
            public String apply(String string) {
                return string.replaceAll("^\\.+", "").replaceAll("\\.+$", "");
            }
        }, FIVE {
            @Override
            public String apply(String string) {
                return string.isEmpty() ? "a" : string;
            }
        }, SIX {
            @Override
            public String apply(String string) {
                return string.length() >= 16 ? string.substring(0, 15).replaceAll("\\.+$", "") : string;
            }
        }, SEVEN {
            @Override
            public String apply(String string) {
                int len = string.length();
                if (len > 2) {
                    return string;
                }
                char c = string.charAt(len - 1);
                for (int i = len; i < 3; i++) {
                    string += c;
                }
                return string;
            }
        };

        public abstract String apply(String string);
    }
}
