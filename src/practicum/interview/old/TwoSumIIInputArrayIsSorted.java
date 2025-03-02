package practicum.interview.old;

/*
Example 1:

Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
Example 2:

Input: numbers = [2,3,4], target = 6
Output: [1,3]
Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
Example 3:

Input: numbers = [-1,0], target = -1
Output: [1,2]
Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].

 */


public class TwoSumIIInputArrayIsSorted {

    public int[] twoSum(int[] numbers, int target) {

        int i = 0;
        int j = numbers.length - 1;
        int[] result = new int[2];
        while (i < j) {
            int sumValues = numbers[i] + numbers[j];
            if (sumValues == target) {
                result[0] = i + 1;
                result[1] = j + 1;
                return result;
            } else if (sumValues > target) {
                j--;
            } else {
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] numbers = {2,7,11,15};
        int target = 9;

        TwoSumIIInputArrayIsSorted twoSumIIInputArrayIsSorted = new TwoSumIIInputArrayIsSorted();
        int[] result = twoSumIIInputArrayIsSorted.twoSum(numbers, target);
        System.out.print("|");
        for (int num : result) {
            System.out.print(num + "|");
        }
    }
}
