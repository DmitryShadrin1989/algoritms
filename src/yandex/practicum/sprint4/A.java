package yandex.practicum.sprint4;
/*
A. Полиномиальный хеш
Алле очень понравился алгоритм вычисления полиномиального хеша. Помогите ей написать функцию, вычисляющую хеш строки s.
В данной задаче необходимо использовать в качестве значений отдельных символов их коды в таблице ASCII.

Полиномиальный хеш считается по формуле:

В первой строке дано число a (1 ≤ a ≤ 1000) –— основание, по которому считается хеш.

Во второй строке дано число m (1 ≤ m ≤ 109) –— модуль.

В третьей строке дана строка s (0 ≤ |s| ≤ 106), состоящая из больших и маленьких латинских букв.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            BigInteger a = BigInteger.valueOf(Long.parseLong(reader.readLine()));
            BigInteger m = BigInteger.valueOf(Long.parseLong(reader.readLine()));
            String s = reader.readLine();

            BigInteger result = BigInteger.ZERO;
            for (int i=0, j=s.length()-1; i<s.length(); i++, j--) {
                //result = result + ((long) s.charAt(i) * (long) Math.pow(a, j));
                result = result.add(BigInteger.valueOf(s.charAt(i)).multiply(a.pow(j)));
            }
            result = result.mod(m);
            System.out.println(result);
        }
    }
}
