package practice;

import java.util.Arrays;

public class ZeroEx1 {
    public static void main(String[] args) {
        int[] arr = new int[] {1, 3, 4, 0, 0, 7, 10};

        int countZero = 0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i]==0) {
                countZero++;
            } else {
                if (countZero != 0) {
                    arr[i-countZero] = arr[i];
                    arr[i] = 0;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
