package leetcode.easy.array;

/*
Тесты:

1) nums = {1,2,2,3,3,4} -> nums = {1,2,3,4,200,200} k = 4
2) nums = {1,2,2,2,3,3,3,4,4} -> nums = {1,2,3,4,200,200,200,200} k = 4
3) nums = {0,0,2,3,3,4} -> nums = {0,2,3,4,200,200} k = 4
4) nums = {1} -> nums = {1} k = 1
5) nums = {1,2,3,4,5,6} -> nums = {1,2,3,4,5,6} k = 6

 */

public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] nums = {1,2,2,2,3,3,3,4,4};

        System.out.println(removeDuplicates(nums));

        for (int num : nums) {
            System.out.print(num + "|");
        }
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        int k = nums.length;
        int prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (prev == nums[i]) {
                k -= 1;
                nums[i] = 200;
            } else {
                prev = nums[i];
            }
        }

        int prevIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            int prevVal = nums[prevIndex];
            int curVal = nums[i];

            if (curVal == 200 && prevVal != 200) {
                prevIndex = i;
            } else if (curVal != 200 && prevVal == 200) {
                nums[prevIndex] = curVal;
                nums[i] = 200;
                prevIndex++;
            }
        }

        return k;
    }
}
