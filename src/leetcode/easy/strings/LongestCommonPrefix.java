package leetcode.easy.strings;

/*
1) Input: strs = ["flower","flow","flight"]
Output: "fl"

2) Input: strs = ["dog","racecar","car"]
Output: ""

3) Input: strs = ["",""]
Output: ""

4) Input: ["ab", "a"]
Output: "a"
 */

public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }

        int idxMinLenStr = 0;
        int minLength = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < minLength) {
                minLength = strs[i].length();
                idxMinLenStr = i;
            }
        }

        String etalon = strs[idxMinLenStr];
        if (etalon.isEmpty()) {
            return "";
        }
        boolean isStop = false;
        int i = 0;
        while (i < minLength && !isStop) {
            char etalonChar = etalon.charAt(i);
            for (String s : strs) {
                if (etalonChar != s.charAt(i)) {
                    isStop = true;
                    break;
                }
            }
            if (!isStop) {
                i++;
            }
        }
        return etalon.substring(0, i);
    }
}
