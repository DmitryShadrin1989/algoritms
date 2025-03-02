package practicum.interview.old;

/*
1)
i: nums = [-4,-1,0,3,10]
o: [0,1,9,16,100]

2)
i: nums = [-7,-3,2,3,11]
o: [4,9,9,49,121]

*/

public class SquaresOfASortedArray {
    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;
        int k = nums.length - 1;
        while(k >= 0) {
            int iSqrNum = (int) Math.pow((double) nums[i], 2D);
            int jSqrNum = (int) Math.pow((double) nums[j], 2D);

            if (iSqrNum > jSqrNum) {
                result[k] = iSqrNum;
                i++;
            } else {
                result[k] = jSqrNum;
                j--;
            }
            k--;
        }
        return result;
    }
}
