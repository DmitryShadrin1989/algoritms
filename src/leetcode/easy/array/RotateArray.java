package leetcode.easy.array;

/*
1) nums = [1,2,3,4,5,6,7], k = 3 -> [5,6,7,1,2,3,4]
2) nums = [-1,-100,3,99], k = 2 -> [3,99,-1,-100]
3) nums = [1,2,3,4,5,6,7,8,9,10,11,12,13], k = 3 -> [11,12,13,1,2,3,4,5,6,7,8,9,10]
*/

public class RotateArray {
    public static void main(String[] args) {
        int[] nums = {1,2};
        int k = 3;
        rotate(nums, k);
        for (int num : nums) {
            System.out.print(num + "|");
        }
    }

    public static void rotate(int[] nums, int k) {
        if (k == 0 || nums.length == 1 || k == nums.length) {
            return;
        }

        if (k > nums.length) {
            k = k%nums.length;
        }

        swap(nums, 0, nums.length - 1);
        swap(nums, 0,  k - 1);
        swap(nums, k, nums.length - 1);
    }

    public static void swap(int[] nums, int i, int j) {
        while (i<j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
