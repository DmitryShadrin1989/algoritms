package practicum.course_2022.sprint8;
/*
L. Подсчёт префикс-функции

В этой задаче вам необходимо посчитать префикс-функцию для заданной строки.

Формат ввода
На вход подаётся строка, состоящая из строчных латинских букв. Длина строки не превосходит 106.

Формат вывода
Если длина входной строки L, то выведите через пробел L целых неотрицательных чисел —– массив значений префикс-функции исходной строки.

Пример 1
Ввод
abracadabra

Вывод
0 0 0 1 0 1 0 1 2 3 4

Пример 2
Ввод
xxzzxxz

Вывод
0 1 0 0 1 2 3

Пример 3
Ввод
aaaaa

Вывод
0 1 2 3 4
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class L {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String string = reader.readLine();

            int[] pi = new int[string.length()];
            for (int i = 1; i < string.length(); ++i) {
                int j = pi[i-1];
                while (j > 0 && string.charAt(i) != string.charAt(j)) {
                    j = pi[j - 1];
                }
                if (string.charAt(i) == string.charAt(j)) {
                    ++j;
                }
                pi[i] = j;
            }

            StringBuilder result = new StringBuilder();
            for (int i : pi) {
                if (result.length() != 0) {
                    result.append(" ");
                }
                result.append(i);
            }
            System.out.println(result);
        }
    }
}
