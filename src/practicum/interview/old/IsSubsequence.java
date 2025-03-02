package practicum.interview.old;

/*
1)
i: s = "abc", t = "ahbgdc"
o: true

2)
i: s = "axc", t = "ahbgdc"
o: false

3)
i: s = "aaaaaa", t = "bbaaaa"
o: false

4)
i: s = "b", t = "c"
o: false

5)
i: s = "bb", t = "ahbgdc"
o: false

*/

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        if (s.length() > t.length()) {
            return false;
        }

        int sIdx = 0;
        int tIdx = 0;
        while(tIdx < t.length()) {
            char sChar = s.charAt(sIdx);
            char tChar = t.charAt(tIdx);
            if (sChar == tChar) {
                ++sIdx;
            }
            if (sIdx == s.length()) {
                return true;
            }
            tIdx++;
        }
        return false;
    }
}
