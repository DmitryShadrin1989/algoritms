package practicum.interview.old;

/*
1)
i: s = "leetcode"
o: 2

2)
i: s = "abbcccddddeeeeedcba"
o: 5

3)
i: s = "abbbcddiii"
o: 3
 */

public class ConsecutiveCharacters {
    public int maxPower(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return s.length();
        }
        int max = 1;
        int countSub = 1;
        int prev = 0;
        for(int curr = 1; curr < s.length(); prev++, curr++) {
            if (s.charAt(prev) == s.charAt(curr)) {
                ++countSub;
                max = Math.max(max, countSub);
            } else {
                countSub = 1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        ConsecutiveCharacters consecutiveCharacters = new ConsecutiveCharacters();
        consecutiveCharacters.maxPower(s);
    }
}
