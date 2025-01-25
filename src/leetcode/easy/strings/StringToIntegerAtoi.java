package leetcode.easy.strings;

/*
1) s = "42" -> 42
2) s = "-042" -> -42
3) s = "1337c0d3" -> 1337
4) s = "0-1" -> 0
) s = "words and 987" -> 0

 */

public class StringToIntegerAtoi {

    public static void main(String[] args) {
        String s = "  +1337c0d3";
        System.out.println(myAtoi(s));
    }

    public static int myAtoi(String s) {
        int beginIndex = 0;
        int endIndex = 0;
        boolean isPositive = true;
        while (endIndex < s.length() - 1) {
            char ch = s.charAt(endIndex);
            if (ch == ' ') {
                endIndex++;
                continue;
            }
            if (ch == '+' || ch == '-') {
                isPositive = ch == '+';
                beginIndex = endIndex;
                endIndex++;
                continue;
            }
            if (ch < '0' || ch > '9') {
                break;
            }
            endIndex++;
        }
        String subString;
        if (s.length() == endIndex + 1) {
            subString = s.substring(beginIndex);
        } else {
            subString = s.substring(beginIndex, endIndex);
        }

        return 0;
    }
}
