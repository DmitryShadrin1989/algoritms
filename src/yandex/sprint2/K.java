package yandex.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Фибоначи. Рекурсивное.
 */
public class K {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int i = Integer.parseInt(reader.readLine());
            System.out.println(fib(i));
        }
    }

    public static int fib(int n) {
        if (n <= 1) {
            return 1;
        }
        return fib(n-2) + fib(n-1);
    }
}
