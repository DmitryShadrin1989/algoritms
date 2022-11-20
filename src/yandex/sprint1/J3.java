package yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class J3 {
    public static void main(String[] args) throws IOException {
    try(
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
        int a = Integer.parseInt(reader.readLine());

        int max = (int) Math.round(Math.sqrt(a));
        boolean[] isPrime = new boolean[max];
        getPrimes(isPrime, max);

        ArrayList<Integer> result = new ArrayList<>();
        while (a>1) {

            if (a % 2 == 0) {
                result.add(2);
                a = a/2;
                continue;
            }
            if (a % 3 == 0) {
                result.add(3);
                a = a/3;
                continue;
            }

            int divider = a;
            for (int i = 5; i < max; i += 2) {
                if (isPrime[i]) {
                    if (a % i == 0) {
                        divider = i;
                        break;
                    }
                }
            }
            result.add(divider);
            a = a/divider;
        }
        Collections.sort(result);
        StringBuilder builder = new StringBuilder();
        builder.append(result.remove(0));
        result.forEach(e-> builder.append(" ").append(e));
        System.out.println(builder);
    }
}

    private static void getPrimes(boolean[] isPrime, int max) {
        Arrays.fill(isPrime, true);
        for (int i = 2; i < max; i++) {
            if (isPrime[i]) {
                for (int j = 2 * i; j < max; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}
