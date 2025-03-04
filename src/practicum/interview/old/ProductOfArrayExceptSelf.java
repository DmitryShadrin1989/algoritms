package practicum.interview.old;

/*
Example 1:
Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
 */

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        result[0] = 1;
        for(int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int rightProduct = 1;
        for(int i = n - 2; i >= 0; i--) {
            rightProduct = rightProduct * nums[i + 1];
            result[i] = result[i] * rightProduct;
        }
        return result;
    }
}
