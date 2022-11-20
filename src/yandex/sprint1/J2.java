package yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class J2 {
    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = Integer.parseInt(reader.readLine());

            List<Integer> primes = getPrimes(a);

            if (primes.isEmpty()) {
                System.out.println(a);
                return;
            }

            ArrayList<Integer> result = new ArrayList<>();
            while (a>1) {
                int divider = a;
                for (Integer i: primes) {
                    if (a%i == 0) {
                        divider = i;
                        break;
                    }
                }
                result.add(divider);
                a = a/divider;
            }
            Collections.sort(result);
            System.out.print(result.remove(0));
            result.forEach(e-> System.out.print(" "+e));
        }
    }

    private static List<Integer> getPrimes(int numer) {
        int max = (int) Math.round(Math.sqrt(numer));
        boolean[] isPrime = new boolean[max];
        Arrays.fill(isPrime, true);

        for (int i = 2; i * i < max; i++) {
            if (isPrime[i]) {
                for (int j = 2 * i; j < max; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i < max; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }
}
