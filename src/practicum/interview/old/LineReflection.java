package practicum.interview.old;

/*
1)
Input: points = [[1,1],[-1,1]]
Output: true

2)
Input: points = [[1,1],[-1,-1]]
Output: false

 */

import java.util.HashSet;
import java.util.Set;

public class LineReflection {

    public static void main(String[] args) {
        int[][] points = {{1,1}, {-1,-1}};
        System.out.println(isReflected(points));
    }

    public static boolean isReflected(int[][] points) {

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        Set<String> pointSet = new HashSet<>();
        for (int[] point : points) {
            minX = Math.min(minX, point[0]);
            maxX = Math.max(maxX, point[0]);
            pointSet.add(point[0] + ";" + point[1]);
        }
        double midX = (minX + maxX) / 2.0;

        for (int[] point : points) {
            int mirrorX = (int) (midX*2) - point[0];
            if (!pointSet.contains(mirrorX + ";" + point[1])) {
                return false;
            }
        }
        return true;
    }
}
