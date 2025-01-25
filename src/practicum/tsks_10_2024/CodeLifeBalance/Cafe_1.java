package practicum.tsks_10_2024.CodeLifeBalance;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

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
484
1 3
5 7 8


 */

public class Cafe_1 {

    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine());
            int[][] days = new int[n][2];
            int minimumTotalCost = 0;
            for (int i = 0; i < n; i++) {
                int costOfLunch = Integer.parseInt(reader.readLine());
                minimumTotalCost+= costOfLunch;
                days[i][0] = costOfLunch;
                if (costOfLunch > 100) {
                    if (i == 0) {
                        days[i][1] = 1;
                    } else {
                        days[i][1] = days[i-1][1] + 1;
                    }
                } else {
                    if (i == 0) {
                        days[i][1] = 0;
                    } else {
                        days[i][1] = days[i-1][1];
                    }
                }
            }

            Integer[] indexes = new Integer[days.length];
            for (int i = 0; i < days.length; i++) {
                indexes[i] = i;
            }
            Arrays.sort(indexes, Comparator.comparingInt(i -> days[i][0]));

            int countCoupons = days[n-1][1];
            int usedCoupons = 0;
            StringBuilder needUseCoupon = new StringBuilder();
            for (int i = n-1; i >= 0; i--) {
                if (usedCoupons == countCoupons) {
                    break;
                }

                int index = indexes[i];
                int costOfLunch = days[index][0];

                if (costOfLunch > 100) {
                    continue;
                }

                int stockCoupons = days[index][1];
                if (stockCoupons <= 0) {
                    continue;
                }

                minimumTotalCost -= costOfLunch;

                usedCoupons++;

                if (needUseCoupon.isEmpty()) {
                    needUseCoupon.append(index+1);
                } else {
                    needUseCoupon.append(" ").append(index+1);
                }

                for (int j = index; j < n; j++) {
                    days[index][1] = days[index][1] - 1;
                }
            }

            System.out.println(minimumTotalCost);
            System.out.println(countCoupons - usedCoupons + " " + usedCoupons);
            System.out.println(needUseCoupon);


            int a = 0;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
