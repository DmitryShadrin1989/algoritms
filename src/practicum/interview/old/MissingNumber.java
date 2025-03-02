package practicum.interview.old;

/*
1)
i: nums = [3,0,1]
o: 2

2)
i: [0,1]
o: 2

3)
i: [9,6,4,2,3,5,7,0,1]
o: 8

*/

public class MissingNumber {

    public int missingNumber(int[] nums) {
        int expectedSum = 0;
        int actualSum = 0;
        for(int i = 0; i < nums.length; i++) {
            expectedSum += i+1;
            actualSum += nums[i];
        }
        return expectedSum - actualSum;
    }
}
