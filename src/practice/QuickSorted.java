package practice;

import java.util.Arrays;

public class QuickSorted {
    public static void main(String[] args) {
        int[] array = {10, 2, 4, 76, 80, 3, 25, 85, 13, 9, 650, 256, 500};
        System.out.println("--- start ---");
        System.out.println(Arrays.toString(array));
        sorted(array, 0, array.length-1);
        System.out.println("--- finish ---");
        System.out.println(Arrays.toString(array));
    }

    public static void sorted(int[] array, int from, int to) {
       if (from<to) {
           int leftMarker = from;
           int rightMarker = to;
           int pilot = array[(from+to)/2];

           while (leftMarker<rightMarker) {
               while (array[leftMarker]<pilot) {
                   leftMarker++;
               }
               while (array[rightMarker]>pilot) {
                   rightMarker--;
               }
               //swap
               if (leftMarker<=rightMarker) {
                   int tmp = array[leftMarker];
                   array[leftMarker] = array[rightMarker];
                   array[rightMarker] = tmp;

                   leftMarker++;
                   rightMarker--;
               }
           }
           sorted(array, from, leftMarker-1);
           sorted(array, leftMarker, to);
       }
    }
}
