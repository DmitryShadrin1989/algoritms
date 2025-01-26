package leetcode.easy.strings;

/*
1)
Input: haystack = "sadbutsad", needle = "sad"
Output: 0

2)
Input: haystack = "leetcode", needle = "leeto"
Output: -1

3)
Input: haystack = "kkdkkkad", needle = "kkkad"
Output: 3

4)
Input: haystack = "abbaabbabb", needle = "abbabb"
Output: 4

5)
Input: haystack = "mississippi", needle = "issip"
Output: 4

 */

public class ImplementStrStr {
    public static void main(String[] args) {
        String haystack = "sadbutsad";
        String needle = "sad";
        System.out.println(strStr(haystack, needle));
    }

    public static int strStr(String haystack, String needle) {
        int haystackLength = haystack.length();
        int needleLength = needle.length();
        for (int i = 0; haystackLength - i >= needleLength; i++) {
            int j = 0;
            while (j < needleLength) {
                if (haystack.charAt(i+j) != needle.charAt(j)) {
                    break;
                }
                j++;
            }
            if (j == needleLength) {
                return i;
            }
        }
        return -1;
    }
}
