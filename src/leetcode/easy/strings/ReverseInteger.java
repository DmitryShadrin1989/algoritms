package leetcode.easy.strings;

/*
1) x = 123 -> 321
2) x = -123 -> -321
3) x = 120 -> 21
4) x = 1534236469 -> 0
*/

public class ReverseInteger {

    public static void main(String[] args) {
        int x = 1534236469;
        System.out.println(reverse(x));
    }

    public static int reverse(int x) {
        char[] chars = String.valueOf(x).toCharArray();
        int i =  x < 0 ? 1 : 0;
        int j = chars.length - 1;
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        if (!isValidResult(chars)) {
            return 0;
        }
        return Integer.parseInt(String.valueOf(chars));
    }

    private static boolean isValidResult(char[] chars) {
        boolean negativeValue = chars[0] == '-';
        if ((negativeValue && chars.length < 11) || (!negativeValue && chars.length < 10)) {
            return true;
        }
        if ((negativeValue && chars.length > 11) || (!negativeValue && chars.length > 10)) {
            return false;
        }
        if (negativeValue) {
            String s = String.valueOf(chars, 1, chars.length - 2);
            if (Integer.parseInt(s) > Integer.MIN_VALUE / 10 || Integer.parseInt(String.valueOf(chars[chars.length-1])) > 8) {
                return false;
            }
        }
        if (!negativeValue) {
            String s = String.valueOf(chars, 0, chars.length - 1);
            if (Integer.parseInt(s) > Integer.MAX_VALUE / 10 || Integer.parseInt(String.valueOf(chars[chars.length-1])) > 7) {
                return false;
            }
        }
        return true;
    }
}
