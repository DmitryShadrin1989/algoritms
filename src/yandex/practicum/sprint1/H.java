package yandex.practicum.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Сложение двоичных чисел
public class H {
    public static int buf = 0;
    public static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s1 = reader.readLine();
            String s2 = reader.readLine();

            char[] maxArr;
            char[] minArr;

            if(s1.length()>s2.length()) {
                maxArr = s1.toCharArray();
                minArr = s2.toCharArray();
            } else {
                maxArr = s2.toCharArray();
                minArr = s1.toCharArray();
            }
            int maxLength = maxArr.length;
            int minLength = minArr.length;
            int lengthDifference = maxLength - minLength;

            for (int i=maxLength-1, j=0; i>=0; i--, j++) {
                int a = maxArr[i]=='1'?1:0;
                if(j<minLength) {
                    int b = minArr[i-lengthDifference]=='1'?1:0;
                    sumBinary(a, b);
                } else {
                    sumBinary(a, 0);
                }
            }
            if (buf == 1) result.insert(0, 1);
        }
        System.out.println(result);
    }

    public static void sumBinary(int a, int b) {
        int temp = a+b+buf;
        if (temp <= 1) {
            result.insert(0, temp);
            buf = 0;
        } else if (temp == 2) {
            result.insert(0, 0);
            buf = 1;
        } else if (temp == 3) {
            result.insert(0, 1);
            buf = 1;
        }
    }
}
