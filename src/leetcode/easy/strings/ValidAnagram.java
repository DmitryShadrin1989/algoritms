package leetcode.easy.strings;

/*
1) s = "anagram", t = "nagaram" -> true
2) s = "rat", t = "car" -> false

 */

public class ValidAnagram {

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] alps = new int[26];
        for (int i = 0; i < s.length(); i++) {
            alps[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < s.length(); i++) {
            alps[t.charAt(i) - 'a'] -= 1;
            if (alps[t.charAt(i) - 'a'] == -1) {
                return false;
            }
        }
        return true;
    }
}
