package practicum.course_2022.sprint1.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int k = Integer.parseInt(reader.readLine());
            int[] ints = new int[10];
            char[] chars;
            for (int i=0; i<4; i++) {
                chars = reader.readLine().toCharArray();
                for (int j = 0; j < 4; j++) {
                    char ch = chars[j];
                    if (ch != '.') {
                        ints[Integer.parseInt(String.valueOf(ch))]++;
                    }
                }
            }
            int result = 0;
            for (int t=0; t<=9; t++) {
                if (ints[t]!=0 && k*2>=ints[t]) {
                     result++;
                }
            }
            System.out.println(result);
        }
    }
}
