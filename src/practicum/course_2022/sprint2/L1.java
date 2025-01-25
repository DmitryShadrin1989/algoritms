package practicum.course_2022.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class L1 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] s = reader.readLine().split(" ");
            System.out.println(fib(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
        }
    }

    public static int fib(int index, int k) {
        if (index == 0) {
            return 1;
        }
        int n0 = 1;
        int n1 = 1;
        for (int i = 2; i <= index; i++) {
            int n2 = (n0 + n1)% (int) Math.pow(10, k);
            n0 = n1;
            n1 = n2;
        }
        return n1;
    }
}
