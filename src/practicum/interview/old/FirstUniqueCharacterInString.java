package practicum.interview.old;

/*
1) s = "leetcode" -> 0
2) s = "loveleetcode" -> 2
3) s = "aabb" -> -1
 */

import java.util.HashMap;

public class FirstUniqueCharacterInString {

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(firstUniqCharWithAlps2(s));
    }

    public static int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    public static int firstUniqCharWithAlps(String s) {
        int n = s.length();
        int[] alps = new int[26];

        for (int i = 0; i < n; i++){
            alps[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < n; i++){
            if (alps[s.charAt(i) - 'a'] == 1)
                return i;
        }

        return -1;
    }

    public static int firstUniqCharWithAlps2(String s) {
        int[] counts = new int[26];
        for(char ch : s.toCharArray()) {
            counts[ch - 'a'] += 1;
        }

        for(int i = 0; i < s.length(); i++) {
            if (counts[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
