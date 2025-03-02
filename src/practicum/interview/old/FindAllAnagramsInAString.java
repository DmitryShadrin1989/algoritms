package practicum.interview.old;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
1)
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]

2)
Input: s = "abab", p = "ab"
Output: [0,1,2]
 */

public class FindAllAnagramsInAString {

    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return List.of();
        }

        int[] pCount = new int[26];
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            pCount[ch - 'a'] += 1;
        }

        int[] winCount = new int[26];
        for (int i = 0; i < p.length(); i++) {
            char ch = s.charAt(i);
            winCount[ch - 'a'] += 1;
        }

        List<Integer> result = new ArrayList<>();
        if (Arrays.equals(pCount, winCount)) {
            result.add(0);
        }

        if (s.length() == p.length()) {
            return result;
        }
        int left = 1;
        int right = p.length();
        while (right < s.length()) {
            char chLeft = s.charAt(left - 1);
            char chRight = s.charAt(right);
            winCount[chLeft - 'a'] -= 1;
            winCount[chRight - 'a'] += 1;
            if (Arrays.equals(pCount, winCount)) {
                result.add(left);
            }
            left++;
            right++;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        FindAllAnagramsInAString findAllAnagramsInAString = new FindAllAnagramsInAString();
        List<Integer> result = findAllAnagramsInAString.findAnagrams(s, p);
        System.out.print("|");
        for (Integer i : result) {
            System.out.print(i + "|");
        }
    }
}
