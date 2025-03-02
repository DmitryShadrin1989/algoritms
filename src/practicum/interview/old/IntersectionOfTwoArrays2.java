package practicum.interview.old;
/*
1) nums1 = {1,2,2,1};
   nums2 = {2,2}; -> [2,2]

1) nums1 = {4,9,5};
   nums2 = {9,4,9,8,4}; -> [4,9]
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IntersectionOfTwoArrays2 {

    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        int[] inters = intersect(nums1, nums2);
        for (int num : inters) {
            System.out.print(num + "|");
        }
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        ArrayList<Integer> inters = new ArrayList<>();
        for (int num : nums2) {
            Integer val = map.get(num);
            if (val != null && val != 0) {
                --val;
                map.put(num, val);
                inters.add(num);
            }
        }
        return inters.stream().mapToInt(Integer::intValue).toArray();
    }
}
