package practicum.interview.old;

/*
1)
i: nums = [6,8,9,10,11,12,13,1,2,3,4,5]
o:

2)
i: nums = [12,13,1,2,3,4,5,6,8,9,10,11]
o:

3)
i: [3,4,5,1,2]
o:

 */

public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while(left < right) {
            int mid = (right + left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
