package practicum.interview.old;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
1)
i: s = "ababcbacadefegdehijhklij"
o: [9,7,8]

2)
i: s = "eccbbbbdeca"
o: [10,1]

3)
i: s = "abcab derte sopws z"
o: [5,5,5,1]

*/

public class PartitionLabels {

    public List<Integer> partitionLabels(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return List.of(s.length());
        }

        int[] counts = new int[26];
        for(char ch : s.toCharArray()) {
            counts[ch - 'a'] += 1;
        }

        int left = 0;
        int right = 0;
        Set<Character> set = new HashSet<>();
        int countDoubles = 0;
        List<Integer> result = new ArrayList<>();
        while (right < s.length()) {
            char ch = s.charAt(right);
            counts[ch - 'a'] -= 1;

            if (set.contains(ch)) {
                countDoubles -= 1;
            } else {
                set.add(ch);
                countDoubles += counts[ch - 'a'];
            }

            if (countDoubles == 0) {
                result.add(right - left + 1);
                ++right;
                left = right;
                continue;
            }
            right++;
        }
        return result;
    }
}
