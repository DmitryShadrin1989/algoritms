package practice;

import java.util.Arrays;

public class BubbleEx1 {
    public static void main(String[] args) {
        int[] array = new int[] {25, 36, 71, 85, 3, 45};
        System.out.println(Arrays.toString(array));
        boolean nextIterable = true;
        while(nextIterable) {
            nextIterable = false;
            for (int i=1; i<array.length; i++) {
                if(array[i-1]>array[i]) {
                    int tmp = array[i];
                    array[i] = array[i-1];
                    array[i-1] = tmp;
                    nextIterable = true;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
