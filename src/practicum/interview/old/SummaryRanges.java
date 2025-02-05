package practicum.interview.old;

/*
1) int[] nums = {1,2,3,5,6,8} -> {"1->3", "5->6", "8"}

 */


import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public static void main(String[] args) {
        int[] nums = {1,2,3,5,6,8};
        System.out.print("|");
        List<String> result = summaryRanges(nums);
        for (String s : result) {
            System.out.print(s + "|");
        }
    }

    public static List<String> summaryRanges(int[] nums) {
        if (nums.length == 1) {
            return List.of(String.valueOf(nums[0]));
        }
        List<String> result = new ArrayList<>();
        int current = 0;
        int next = 1;
        while(current < nums.length) {
            int left = nums[current];
            int right = nums[current];
            while(next < nums.length && (nums[next] - nums[current]) == 1) {
                right = nums[next];
                current++;
                next++;
            }
            if(left < right) {
                result.add(String.valueOf(left) + "->" + String.valueOf(right));
            } else  {
                result.add(String.valueOf(left));
            }
            current++;
            next++;
        }
        return result;
    }
}
