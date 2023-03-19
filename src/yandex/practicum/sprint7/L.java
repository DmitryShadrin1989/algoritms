package yandex.practicum.sprint7;
/*
L. Золото лепреконов

Лепреконы в данной задаче появились по соображениям общей морали, так как грабить банки — нехорошо.

Вам удалось заключить неплохую сделку с лепреконами, поэтому они пустили вас в своё хранилище золотых слитков.
Все слитки имеют единую пробу, то есть стоимость 1 грамма золота в двух разных слитках одинакова.
В хранилище есть n слитков, вес i-го слитка равен wi кг. У вас есть рюкзак, вместимость которого M килограмм.

Выясните максимальную суммарную массу золотых слитков, которую вы сможете унести.

Формат ввода
В первой строке дано число слитков —– натуральное число n (1 ≤ n ≤ 1000) и вместимость рюкзака –
— целое число M (0 ≤ M ≤ 104). Во второй строке записано n натуральных чисел wi (1 ≤ wi ≤ 104) -— массы слитков.

Формат вывода
Выведите единственное число — максимальную массу, которую можно забрать с собой.

Пример 1
Ввод
5 15
3 8 1 2 5

Вывод
15

Пример 2
Ввод
5 19
10 10 7 7 4

Вывод
18
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class L {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = reader.readLine().split(" ");
            int n = Integer.parseInt(strings[0]);
            int m = Integer.parseInt(strings[1]);

            int[] weights = new int[n];
            String[] s = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                weights[i] = Integer.parseInt(s[i]);
            }

            int[] dp = new int[m + 1];
            dp[0] = 1;
            int max = 0;
            for (int weight : weights) {
                for (int i = m; i >= weight; --i) {
                    if (dp[i - weight] == 1) {
                        dp[i] = 1;
                        max = Math.max(max, i);
                    }
                }
            }
            System.out.println(max);
        }
    }
}
