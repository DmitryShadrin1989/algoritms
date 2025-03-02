package practicum.interview.old;

/*
1)
i: intervals = [[1,3],[2,6],[8,10],[15,18]]
o: [[1,6],[8,10],[15,18]]

2)
i: intervals = [[1,4],[4,5]]
o: [[1,5]]

3)
i: intervals = [[1,4],[4,5],[5,6]]
o: [[1,6]]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> mergedIntervalsList = new ArrayList<>();
        int beginInterval = intervals[0][0];
        int endInterval = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= endInterval) {
               if (intervals[i][1] > endInterval) {
                   endInterval = intervals[i][1];
               }
               continue;
            }
            int[] closedInterval = {beginInterval, endInterval};
            mergedIntervalsList.add(closedInterval);
            beginInterval = intervals[i][0];
            endInterval = intervals[i][1];
        }
        int[] closedInterval = {beginInterval, endInterval};
        mergedIntervalsList.add(closedInterval);
        return mergedIntervalsList.toArray(new int[mergedIntervalsList.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 4}, {2, 3}, {8, 10}, {15, 18}};
        MergeIntervals mergeIntervals = new MergeIntervals();
        int[][] mergedIntervals = mergeIntervals.merge(intervals);
        System.out.print("|");
        for (int[] interval : mergedIntervals) {
            System.out.print(interval[0] + "," + interval[1] + "|");
        }
    }
}