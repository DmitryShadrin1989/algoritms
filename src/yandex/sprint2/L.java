package yandex.sprint2;
/*
L. Фибоначчи по модулю
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class L {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] s = reader.readLine().split(" ");
            BigInteger bigInteger = fib(BigInteger.valueOf(Integer.parseInt(s[0])));
            System.out.println(bigInteger.mod(BigInteger.TEN.pow(Integer.parseInt(s[1]))));
        }
    }

    public static BigInteger fib(BigInteger index) {
        if (index.compareTo(BigInteger.ZERO) == 0) {
            return BigInteger.ZERO;
        }
        var n0 = BigInteger.ONE;
        var n1 = BigInteger.ONE;
        for (var i = BigInteger.TWO; i.compareTo(index) <= 0; i = i.add(BigInteger.ONE)) {
            var n2 = n0.add(n1);
            n0 = n1;
            n1 = n2;
        }
        return n1;
    }
}
