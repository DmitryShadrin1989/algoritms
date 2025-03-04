package practicum.interview.old;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {

    public boolean checkSubarraySum(int[] nums, int k) {

        Map<Integer, Integer> remainderIndexMap = new HashMap<>();
        remainderIndexMap.put(0, -1); // Для учета случая, если сумма от начала кратна k

        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int remainder = prefixSum % k;

            // Java mod может давать отрицательные значения, корректируем
            if (remainder < 0) {
                remainder += k;
            }

            if (remainderIndexMap.containsKey(remainder)) {
                if (i - remainderIndexMap.get(remainder) > 1) {
                    return true;
                }
            } else {
                remainderIndexMap.put(remainder, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ContinuousSubarraySum solution = new ContinuousSubarraySum();
        System.out.println(solution.checkSubarraySum(new int[]{23,2,4,6,7}, 6)); // true
        System.out.println(solution.checkSubarraySum(new int[]{23,2,6,4,7}, 6)); // true
        System.out.println(solution.checkSubarraySum(new int[]{23,2,6,4,7}, 13)); // false
    }
}
