package practice;

import java.util.Arrays;

public class InsertSortEx1 {
    public static void main(String[] args) {
        int[] array = new int[] {64, 42, 73, 41, 32, 53, 16, 24, 57, 42, 74, 55, 36};
        System.out.println(Arrays.toString(array));

      for(int i=1; i<array.length; i++) {
          int current= array[i];
          int j = i;
          while(j>0 && array[j-1]>current) {
              array[j] = array[j-1];
              j--;
          }
          array[j] = current;
      }
        System.out.println(Arrays.toString(array));

    }
}
