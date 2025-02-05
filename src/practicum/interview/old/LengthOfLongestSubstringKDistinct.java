package practicum.interview.old;

/*
Для заданной строки s и целого числа k вернуть длину самой длинной подстроки s, содержащей не более k различных символов.

1)
Input: s = "eceba", k = 2
Output: 3

 */

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstringKDistinct {

    public static void main(String[] args) {
        String s = "eceba";
        int k = 2;
        System.out.println(getLength(s, k));
    }

    public static int getLength(String s, int k) {
        int left = 0;
        int right = 0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }
            max = Math.max(max, right - left +1);
            right++;
        }
        return max;
    }
}
