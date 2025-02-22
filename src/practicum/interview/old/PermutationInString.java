package practicum.interview.old;

/*
1)
Input: s1 = "ab", s2 = "eidbaooo"
Output: true

2)
Input: s1 = "ab", s2 = "eidboaoo"
Output: false

 */

import java.util.Arrays;

public class PermutationInString {

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2));
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] etalon = new int[26];
        int[] subLine = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            etalon[s1.charAt(i) - 'a'] += 1;
            subLine[s2.charAt(i) - 'a'] += 1;
        }
        if (Arrays.equals(etalon, subLine)) {
            return true;
        }
        subLine[s2.charAt(0) - 'a'] -= 1;
        int left = 1;
        int right = s1.length();
        while (right < s2.length()) {
            subLine[s2.charAt(right) - 'a'] += 1;
            if (Arrays.equals(etalon, subLine)) {
                return true;
            }
            subLine[s2.charAt(left) - 'a'] -= 1;
            left++;
            right++;
        }
        return false;
    }
}
