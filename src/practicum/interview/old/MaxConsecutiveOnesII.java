package practicum.interview.old;

/*
Description
Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.

1)
Input: nums = [1,0,1,1,0]
Output: 4

2)
Input: nums = [1,0,1,1,0,1]
Output: 4

 */

public class MaxConsecutiveOnesII {

    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int left = 0;
        int right = 1;
        int maxDistance = 0;
        int currDistance = 1;
        int countZeroes = nums[left] == 0 ? 1 : 0;
        while (right < nums.length && left < right) {
            int rightVal = nums[right];
            if (rightVal == 0) {
                ++countZeroes;
            }
            if (countZeroes > 1) {
                if (nums[left] == 0) {
                    countZeroes--;
                }
                left++;
                currDistance = 1;
                continue;
            }
            ++currDistance;
            maxDistance = Math.max(maxDistance, currDistance);
            right++;
        }

        return maxDistance;
    }

    public static void main(String[] args) {
        int[] nums = {1,0,1,1,0,1};
        MaxConsecutiveOnesII maxConsecutiveOnesII = new MaxConsecutiveOnesII();
        System.out.println(maxConsecutiveOnesII.findMaxConsecutiveOnes(nums));
    }
}
