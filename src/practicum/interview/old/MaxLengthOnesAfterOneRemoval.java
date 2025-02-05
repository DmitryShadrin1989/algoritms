package practicum.interview.old;

public class MaxLengthOnesAfterOneRemoval {

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 1, 0, 1};
        System.out.println(longestOnesAfterRemoval(nums));
    }

    public static int longestOnesAfterRemoval(int[] nums) {
        int left = 0;
        int right = 0;
        int countZero = 0;
        int max = 0;
        while (right < nums.length) {
            int rightValue = nums[right];
            if (rightValue == 0) {
                countZero++;
            }
            while (countZero > 1) {
                int leftValue = nums[left];
                if (leftValue == 0) {
                    countZero--;
                }
                left++;
            }
            max = Math.max(max, right - left);
            right++;
        }
        return max;
    }
}
