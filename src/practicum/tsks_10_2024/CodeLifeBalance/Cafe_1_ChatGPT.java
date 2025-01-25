package practicum.tsks_10_2024.CodeLifeBalance;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
1. Кафе

Около Петиного университета недавно открылось новое кафе, в котором действует следующая система скидок:
при каждой покупке более чем на 100 рублей покупатель получает купон, дающий право на один бесплатный
обед (при покупке на сумму 100 рублей и меньше такой купон покупатель не получает).
Однажды Пете на глаза попался прейскурант на ближайшие N дней. Внимательно его изучив, он решил, что будет обедать в
этом кафе все N дней, причем каждый день он будет покупать в кафе ровно один обед. Однако стипендия у Пети небольшая,
и поэтому он хочет по максимуму использовать предоставляемую систему скидок так, чтобы его суммарные затраты были
минимальны.
Требуется найти минимально возможную суммарную стоимость обедов и номера дней, в которые Пете следует воспользоваться
купонами.

Формат ввода
В первой строке входного файла записано целое число N (0≤N≤100). В каждой из последующих N строк записано одно целое
число, обозначающее стоимость обеда в рублях на соответствующий день. Стоимость — неотрицательное целое число, не
превосходящее 300.

Формат вывода
В первой строке выдайте минимальную возможную суммарную стоимость обедов. Во второй строке выдайте два числа K1 и K2 —
количество купонов, которые останутся неиспользованными у Пети после этих N дней и количество использованных им купонов
соответственно.
В последующих K2 строках выдайте в возрастающем порядке номера дней, когда Пете следует воспользоваться купонами.
Если существует несколько решений с минимальной суммарной стоимостью, то выдайте то из них, в котором значение K1
максимально (на случай, если Петя когда-нибудь ещё решит заглянуть в это кафе). Если таких решений несколько, выведите
любое из них.

Тест №1
Ввод
5
35
40
101
59
63

Вывод
235
0 1
5

Тест №2
Ввод
9
70
101
10
101
20
101
63
99
101

Вывод
235
0 1
5


 */

public class Cafe_1_ChatGPT {

    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(reader.readLine());
            int[] prices = new int[N];
            for (int i = 0; i < N; i++) {
                int costOfLunch = Integer.parseInt(reader.readLine());
                prices[i] = costOfLunch;
            }

            // Максимальное количество купонов
            int[][] dp = new int[N + 1][N + 1]; // dp[i][j] - минимальная стоимость за первые i дней с j купонами
            for (int[] row : dp) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
            dp[0][0] = 0; // на 0 дней с 0 купонами сумма равна 0

            // Хранение информации, использовал ли купон
            boolean[][] usedCoupon = new boolean[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                int cost = prices[i - 1];
                for (int j = 0; j <= i; j++) {
                    // Если Петя не использует купон
                    if (j > 0) {
                        // Петя получает купон, если обед дороже 100 рублей
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + cost);
                    } else {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + cost);
                    }

                    // Если Петя использует купон
                    if (cost <= 100 && j + 1 <= i) {
                        if (dp[i - 1][j + 1] < Integer.MAX_VALUE) {
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1]);
                            usedCoupon[i][j] = true;
                        }
                    }
                }
            }

            // Поиск решения с максимальным количеством оставшихся купонов
            int minCost = Integer.MAX_VALUE;
            int remainingCoupons = 0;
            for (int j = 0; j <= N; j++) {
                if (dp[N][j] < minCost) {
                    minCost = dp[N][j];
                    remainingCoupons = j;
                }
            }

            // Восстановление дней, когда использовались купоны
            List<Integer> couponDays = new ArrayList<>();
            int coupons = remainingCoupons;
            for (int i = N; i > 0; i--) {
                if (usedCoupon[i][coupons]) {
                    couponDays.add(i);
                    coupons++;
                } else if (prices[i - 1] > 100) {
                    coupons--;
                }
            }

            // Вывод результата
            System.out.println(minCost);
            System.out.println(remainingCoupons + " " + couponDays.size());
            couponDays.sort(Integer::compareTo);
            for (int day : couponDays) {
                System.out.println(day);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
