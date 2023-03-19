package yandex.practicum.sprint7;
/*
F. Прыжки по лестнице

Алла хочет доказать, что она умеет прыгать вверх по лестнице быстрее всех.
На этот раз соревнования будут проходить на специальной прыгательной лестнице.
С каждой её ступеньки можно прыгнуть вверх на любое расстояние от 1 до k. Число k придумывает Алла.

Гоша не хочет проиграть, поэтому просит вас посчитать количество способов допрыгать от первой ступеньки до n-й.
Изначально все стоят на первой ступеньке.

Формат ввода
В единственной строке даны два числа — n и k (1 ≤ n ≤ 1000, 1 ≤ k ≤ n).

Формат вывода
Выведите количество способов по модулю 10^9 + 7.

Пример 1
Ввод
6 3

Вывод
13

Пример 2
Ввод
7 7

Вывод
32

Пример 2
Ввод
2 2

Вывод
1
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class F {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);

            long[] longs = new long[n];
            longs[n-1] = 1;
            for (int i = n - 2; i >= 0; i--) {
                for (int j = i + 1; j < n && j < i + 1 + k; j++) {
                    longs[i] = (longs[i] + longs[j]) % 1000000007;
                }
            }
            System.out.println(longs[0]);
        }
    }
}
