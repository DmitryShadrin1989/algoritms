package practicum.interview.old;

import java.util.HashSet;
import java.util.Set;

/*
1)
i: jewels = "aA", stones = "aAAbbbb"
o: 3

2)
i: jewels = "z", stones = "ZZ"
o: 0

*/

public class JewelsAndStones {

    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> set = new HashSet<>();
        for(Character ch : jewels.toCharArray()) {
            set.add(ch);
        }
        int count = 0;
        for(Character ch : stones.toCharArray()) {
            if (set.contains(ch)) {
                count++;
            }
        }
        return count;
    }
}
