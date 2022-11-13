package sorted.merge;

import java.sql.Array;
import java.util.Arrays;

public class Merge {

    public static void main(String[] args) {
        int[] array = new int[] {15,78,89,9,12,12546,8,879,16,54,1};
        System.out.println("--- Start ---");
        System.out.println(Arrays.toString(array));
        array = mergeSort(array);
        System.out.println("--- Finish ---");
        System.out.println(Arrays.toString(array));
    }

    public static int[] mergeSort(int[] array) {

        int[] arraySrc = array;
        int[] dest = new int[array.length];

        // Реализуем цикл по подмассивам с шагом Х2
        int size = 1;
        while (size< array.length) {

            // Итерирование по подмассивам с текущим размером
            //for () {
                merge(arraySrc, size , dest);
            //}

            size = size*2;
        }



        return array;
    }

    public static void merge(int [] arraySrc, int size, int[] dest) {


        // Учесть если в одном подмассиве элементов не осталось, то перложить все в из оставшегося в результат

        // Итерирование и перекладка
//        for () {
//
//        }

    }
}
