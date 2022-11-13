package sorted;

import java.util.Arrays;

public class Bubble {
    public static void main(String[] args) {
        int [] ints = {10, 2, 4, 76, 80,3,25};
        System.out.println(Arrays.toString(bubbleSorted(ints)));
    }

    public static int[] bubbleSorted(int[] ints) {
        boolean nextIteration = true;

        while (nextIteration) {
            nextIteration = false;
            for (int i=1; i< ints.length; i++) {
                if (ints[i-1]>ints[i]) {
                    nextIteration = true;
                    int tmp = ints[i-1];
                    ints[i-1] = ints[i];
                    ints[i] = tmp;
                }
            }
        }
        return ints;
    }
}
