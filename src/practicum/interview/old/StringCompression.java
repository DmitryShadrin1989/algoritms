package practicum.interview.old;

/*
1)
Input: chars = ['a','a','b','b','c','c','c']
Output: 6, ['a','2','b','2','c','3']

2)
Input: chars = ['a']
Output: 1, ['a']

3)
Input: chars = ['a','b','b','b','b','b','b','b','b','b','b','b','b']
Output: 4, ['a','b','1','2']

3)
Input: chars = ['o','o','o','o','o','o','o','o','o','o']
Output: 3, ['o','1','0']

 */

public class StringCompression {

    public static void main(String[] args) {
        char[] chars = {'a','a'};
        System.out.println(compress2(chars));
    }

    public static int compress(char[] chars) {
        if (chars.length == 1) {
            return 1;
        }

        int left = 0;
        int right = 1;
        int count = 1;
        while (right < chars.length) {
            if (chars[left] == chars[right]) {
                count++;
                right++;
                continue;
            }
            if (count == 1) {
                left++;
                right++;
                continue;
            }
            fillCount(chars, left, right, count);
            left = right;
            right++;
            count = 1;

        }
        fillCount(chars, left, right, count);

        left = 1;
        right = 1;
        while (right < chars.length) {
            if (chars[left] != ' ') {
                left++;
                right++;
                continue;
            }
            if (chars[right] == ' ') {
                right++;
                continue;
            }
            chars[left] = chars[right];
            chars[right] = ' ';
            left++;
            right++;
        }
        return left;
    }

    private static void fillCount(char[] chars, int left, int  right, int count) {
        String countStr = String.valueOf(count);
        int strIdx = 0;
        left++;
        while (left != right) {
            if (strIdx < countStr.length()) {
                chars[left] = countStr.charAt(strIdx);
                strIdx++;
            } else {
                chars[left] = ' ';
            }
            left++;
        }
    }

    public static int compress2(char[] chars) {
        if (chars.length == 0 || chars.length == 1) {
            return chars.length;
        }

        int left = 0;
        int right = 1;
        int count = 1;
        while(right < chars.length) {
            if (chars[left] == chars[right]) {
                count++;
                right++;
                continue;
            }

            char[] countChars = String.valueOf(count).toCharArray();
            int countIdx = 0;
            ++left;
            while(left < right) {
                if (countIdx < countChars.length) {
                    chars[left] = countChars[countIdx];
                    countIdx++;
                } else {
                    chars[left] = ' ';
                }
                left++;
            }
            count = 1;
            right++;
        }

        char[] countChars = String.valueOf(count).toCharArray();
        int countIdx = 0;
        ++left;
        while(left < right) {
            if (countIdx < countChars.length) {
                chars[left] = countChars[countIdx];
                countIdx++;
            } else {
                chars[left] = ' ';
            }
            left++;
        }

        left = 0;
        right = 0;
        while (right < chars.length) {
            if (chars[left] != ' ') {
                left++;
                right++;
                continue;
            }

            if (chars[right] != ' ') {
                chars[left] = chars[right];
                chars[right] = ' ';
                left++;
            }
            right++;
        }
        return left;
    }
}
