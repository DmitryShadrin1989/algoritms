package leetcode.easy.array;

import java.util.Arrays;

public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = {2,14,18,22,22};

        System.out.println(containsDuplicate(nums));
    }

    public static boolean containsDuplicate(int[] nums) {
        if (nums.length == 1) {
            return false;
        }

        Arrays.sort(nums);
        int prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (prev == nums[i]) {
                return true;
            }
            prev = nums[i];
        }
        return false;
    }
}
