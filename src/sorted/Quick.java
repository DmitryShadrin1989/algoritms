package sorted;

import java.util.Arrays;

public class Quick {

    public static int iteration = 0;

    public static void main(String[] args) {
        int [] ints = {10, 2, 4, 76, 80, 3, 25, 85, 13, 9, 650, 256, 500};
        System.out.println("--- Started ---");
        System.out.println(Arrays.toString(ints));

        quickSorted(ints, 0, ints.length-1);

        System.out.println("--- Finish ---");
        System.out.println(Arrays.toString(ints));
    }

    public static void quickSorted(int[] ints, int from, int to) {
        if(from < to) {
            //partition
            iteration++;
            int leftMarker = from;
            int rightMarker = to;
            int pilot = ints[(from + to)/2];
            while(leftMarker <= rightMarker) {
                while(ints[leftMarker]<pilot) {
                    leftMarker++;
                }
                while(ints[rightMarker]>pilot) {
                    rightMarker--;
                }

                //swap
                if (leftMarker <= rightMarker) {
                    int tmp = ints[leftMarker];
                    ints[leftMarker] = ints[rightMarker];
                    ints[rightMarker] = tmp;

                    leftMarker++;
                    rightMarker--;
                }
            }
            System.out.println("--- iterations " + iteration + " ---" + " pilot=" + pilot);
            System.out.println(Arrays.toString(ints));

            quickSorted(ints, from, leftMarker-1);
            quickSorted(ints, leftMarker, to);
        }
    }
}





