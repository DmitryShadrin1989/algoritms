package practicum.interview.old;

/*
1)
Input: s = "abcabcbb"
Output: 3

2)
Input: s = "bbbbb"
Output: 1

3)
Input: s = "pwwkew"
Output: 3

 */

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 1;
        int left = -1;
        for(int right = 0; right < s.length(); right++) {
            Character ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.get(ch) > 1) {
                ++left;
                Character leftCh = s.charAt(left);
                map.put(leftCh, map.get(leftCh) - 1);
            }
            maxLength = Math.max(maxLength, right - left);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abc";
        LongestSubstringWithoutRepeatingCharacters longestSubstringWithoutRepeatingCharacters = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s));
    }
}
