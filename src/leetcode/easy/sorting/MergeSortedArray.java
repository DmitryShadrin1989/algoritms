package leetcode.easy.sorting;

/*
1)
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]

2)
Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]

3)
Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]

 */

public class MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = {0};
        int m = 0;
        int[] nums2 = {1};
        int n = 1;

        merge(nums1, m, nums2, n);

        System.out.print("|");
        for (int num : nums1) {
            System.out.print(num + "|");
        }
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                nums1[i] = 0;
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
    }

}
