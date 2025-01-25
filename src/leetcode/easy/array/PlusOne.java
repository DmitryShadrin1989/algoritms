package leetcode.easy.array;

/*
1) digist = {1,2,3} -> {1,2,4}
2) digist = {4,3,2,1} -> {4,3,2,2}
3) digist = {9} -> {1,0}

*/

public class PlusOne {

    public static void main(String[] args) {

        int[] digist = {999};
        int[] result = plusOne(digist);
        for (int num : result) {
            System.out.print(num + "|");
        }
    }

    public static int[] plusOne(int[] digits) {

        boolean needUp = true;
        for (int i = digits.length-1; i >= 0 && needUp; i--) {
            int value = digits[i];
            ++value;
            if (value == 10) {
                digits[i] = 0;
            } else {
                digits[i] = value;
                needUp = false;
            }
        }
        if (needUp) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                newDigits[i+1] = digits[i];
            }
            return newDigits;
        }
        return digits;
    }
}
