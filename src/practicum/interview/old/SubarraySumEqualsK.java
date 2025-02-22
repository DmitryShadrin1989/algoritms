package practicum.interview.old;

/*
todo Сделать замтку
1)
Input: nums = [1,1,1], k = 2
Output: 2

2)
Input: nums = [1,2,3], k = 3
Output: 2

3)
Input: nums = [1,2,3,2,1,1,1,2], k = 3
Output: 5

4)
Input: nums = [-1,-1,1], k = 0
Output: 5

5)
Input: nums = [28,54,7,-70,22,65,-6], k = 100
Output: 1
 */

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    public static void main(String[] args) {
        int[] nums = {28,54,7,-70,22,65,-6};
        int k = 100;
        System.out.println(subarraySum(nums, k));
    }

    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int currentSum = 0;
        map.put(currentSum, 1);
        for (int num : nums) {
            currentSum += num;
            if (map.containsKey(currentSum - k)) {
                count += map.get(currentSum - k);
            }
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }
        return count;
    }
}
