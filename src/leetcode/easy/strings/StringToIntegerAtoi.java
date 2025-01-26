package leetcode.easy.strings;

/*
1) s = "42" -> 42
2) s = "-042" -> -42
3) s = "1337c0d3" -> 1337
4) s = "0-1" -> 0
5) s = "words and 987" -> 0
6) s = "4193  " -> 4193
7) "-91283472332" ->
8) "91283472332" ->
9) "+-12" -> 0
10) "  +0000000000012345678" -> 12345678

 */

public class StringToIntegerAtoi {

    public static void main(String[] args) {
        String s = "words and 987";
        System.out.println(myAtoi(s));
    }

    public static int myAtoi(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        for (char ch : chars) {
            if (sb.isEmpty()) {
                if (ch == ' ') {
                    continue;
                }
                if (ch == '-' || ch == '+') {
                    sb.append(ch);
                    continue;
                }
            }
            if ((ch < '0' || ch > '9')) {
                break;
            }
            if (ch == '0' && leadingZero) {
                if (sb.isEmpty()) {
                    sb.append('+');
                }
                continue;
            } else if (leadingZero) {
                leadingZero = false;
            }
            if (sb.isEmpty()) {
                sb.append('+');
            }
            sb.append(ch);
        }

        String numberAsString = sb.toString();
        if (numberAsString.isEmpty() ||
                (numberAsString.length() == 1 && (numberAsString.charAt(0) == '-' || numberAsString.charAt(0) == '+'))) {
            return 0;
        }

        if (numberAsString.length() > 10) {
            if (numberAsString.length() > 11) {
                return numberAsString.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            int partValue = Integer.parseInt(numberAsString.substring(0, numberAsString.length() - 1));
            if (partValue < Integer.MIN_VALUE / 10 ||
                    (partValue == Integer.MIN_VALUE / 10 && numberAsString.charAt(10) > '8')) {
                return Integer.MIN_VALUE;
            }
            if (partValue > Integer.MAX_VALUE / 10 ||
                    (partValue == Integer.MAX_VALUE / 10 && numberAsString.charAt(10) > '7')) {
                return Integer.MAX_VALUE;
            }
        }
        return Integer.parseInt(numberAsString);
    }
}
