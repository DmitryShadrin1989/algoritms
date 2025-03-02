package practicum.interview.old;

/*
1) int[] nums = {1,2,3,5,6,8} -> {"1->3", "5->6", "8"}

 */


import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public static void main(String[] args) {
        int[] nums = {-2147483648,0,2,3,4,6,8,9};
        System.out.print("|");
        List<String> result = summaryRanges2(nums);
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

    public static List<String> summaryRanges2(int[] nums) {
        if (nums.length == 0) {
            return List.of();
        }
        if (nums.length == 1) {
            return List.of(String.valueOf(nums[0]));
        }
        List<String> result = new ArrayList<>();
        int currIdx = 0;
        int nextIdx = 1;
        int openVal = nums[currIdx];
        while(nextIdx < nums.length) {
            if ((nums[nextIdx] - nums[currIdx]) != 1) {
                if(openVal == nums[currIdx]) {
                    result.add(String.valueOf(openVal));
                } else {
                    result.add(openVal + "->" + nums[currIdx]);
                }
                openVal = nums[nextIdx];
            }
            currIdx++;
            nextIdx++;
        }
        if(openVal == nums[currIdx]) {
            result.add(String.valueOf(openVal));
        } else {
            result.add(openVal + "->" + nums[currIdx]);
        }

        return result;
    }
}
