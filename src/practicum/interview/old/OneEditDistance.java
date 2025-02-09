package practicum.interview.old;

/*
1)
Input: s = "ab", t = "acb"
Output: true

2)
Input: s = "", t = ""
Output: false

3)
Input: s = "abc", t = "abc"
Output: false

4)
Input: s = "aaa", t = "abb"
Output: false

5)
Input: s = "abc", t = "abcabc"
Output: false

5)
Input: s = "abc", t = "abbc"
Output: true


 */

public class OneEditDistance {

    public static void main(String[] args) {
        String s = "";
        String t = "";
        System.out.println(isOneEditDistance(s, t));

    }

    public static boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }

        int editsCount = 0;

        if (s.length() == t.length()) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    ++editsCount;
                }
                if (editsCount > 1) {
                    return false;
                }
            }
            return editsCount == 1;
        }


        String shortStr;
        String longStr;
        if (s.length() > t.length()) {
            longStr = s;
            shortStr = t;
        } else {
            longStr = t;
            shortStr = s;
        }
        int i = 0;
        int j = 0;
        while (i < shortStr.length()) {
            if (shortStr.charAt(i) != longStr.charAt(j)) {
                ++editsCount;
                if (editsCount > 1) {
                    return false;
                }
                j++;
                continue;
            }
            i++;
            j++;
        }
        return true;
    }
}
