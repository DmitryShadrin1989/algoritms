package practicum.interview.old;

/*
1) nums = {0,1,0,3,12} -> {1,3,12,0,0}
2) nums = {0} -> {0}
3) nums = {0,0,0,0,12} -> {12,0,0,0,0}
4) nums = {0,0,0} -> {0,0,0}
5) nums = {1,3,12} -> {1,3,12}

 */

public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums = {0};
        moveZeroes(nums);
        System.out.print("|");
        for (int num : nums) {
            System.out.print(num + "|");
        }
    }

    public static void moveZeroes(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int borderIdx = 0;
        int curIndex = 1;
        while (curIndex < nums.length) {
            if (nums[borderIdx] != 0) {
                borderIdx++;
                curIndex++;
                continue;
            }

            if (nums[curIndex] != 0) {
                nums[borderIdx] = nums[curIndex];
                nums[curIndex] = 0;
                borderIdx++;
            }
            curIndex++;
        }
    }

    public static void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int insertPos = 0;
        for (int num: nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }
}
