package leetcode.easy.array;

/*
1) nums = {2,7,11,15} target = 9 -> {0,1}
2) nums = {3,2,4} target = 6 -> {1,2}
3) nums = {3,3} target = 6 -> {0,1}
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {3,3};
        int target = 6;
        int[] result = twoSum(nums, target);
        System.out.print("|");
        for (int num : result) {
            System.out.print(num + "|");
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        if (nums.length == 2) {
            return new int[]{0,1};
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            List<Integer> idxs;
            if (map.containsKey(value)) {
                idxs = map.get(value);
            } else {
                idxs = new ArrayList<>();
                map.put(value, idxs);
            }
            idxs.add(i);
        }

        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            int searchValue = target - value;
            if (map.containsKey(searchValue)) {
                List<Integer> idxs = map.get(searchValue);
                for (int idx : idxs) {
                    if (idx != i) {
                        result[0] = i;
                        result[1] = idx;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
