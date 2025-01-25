package leetcode.easy.strings;

/*
1) s = "A man, a plan, a canal: Panama" -> true
2) s = "race a car" -> false
3) s = " " -> true
 */

public class ValidPalindrome {

    public static void main(String[] args) {
        String s = " ";
        System.out.println(isPalindrome(s));
    }

    public static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char left = s.charAt(i);
            boolean isNormalLeft = checkChar(left);
            char right = s.charAt(j);
            boolean isNormalRight = checkChar(right);

            if (isNormalLeft && isNormalRight) {
                if (convertToLowerCase(left) != convertToLowerCase(right)) {
                    return false;
                }
                i++;
                j--;
                continue;
            }
            if (!isNormalLeft) {
                i++;
            }
            if (!isNormalRight) {
                j--;
            }
        }
        return true;
    }

    public static char convertToLowerCase(char ch) {
        if (ch >= 65 && ch <= 90) {
            return (char) (ch + 32);
        }
        return ch;
    }

    public static boolean checkChar(char ch) {
        if ((ch >= 48 && ch <= 57) || (ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)) {
            return true;
        }
        return false;
    }
}
