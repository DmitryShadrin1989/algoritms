package practicum.tsks_10_2024.CodeLifeBalance;

import java.io.*;
import java.util.*;

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

public class Cafe_1_v2 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        int[][] priceForDays = new int[n + 1][3];
        int totalCost = 0;
        int countCoupons = 0;
        for (int i = 1; i <= n; i++) {
            int costOfLunchPerDay = Integer.parseInt(reader.readLine());

            priceForDays[i][0] = costOfLunchPerDay;

            if (priceForDays[i - 1][0] > 100) {
                priceForDays[i][1] = i - 1;
            } else {
                priceForDays[i][1] = priceForDays[i - 1][1];
            }

            if (costOfLunchPerDay > 100) {
                countCoupons++;
            }

            totalCost += costOfLunchPerDay;
        }

        Integer[] indexesSortedPrice = new Integer[priceForDays.length];
        for (int i = 0; i < priceForDays.length; i++) {
            indexesSortedPrice[i] = i;
        }
        Arrays.sort(indexesSortedPrice, Comparator.comparingInt(i -> priceForDays[i][0]));


        int minCost = totalCost;
        int countUsedCoupons = 0;
        List<Integer> couponUsageDays = new ArrayList<>();
        for (int i = n - 1; i > 0; i--) {
            int index = indexesSortedPrice[i];

            int costOfLunchPerDay = priceForDays[index][0];
            if (costOfLunchPerDay > 100) {
                continue;
            }

            int indexOfNearestCoupon = findFreeCoupon(index, priceForDays);
            if (indexOfNearestCoupon == 0) {
                continue;
            }

            minCost -= costOfLunchPerDay;
            countUsedCoupons++;
            couponUsageDays.add(index);
            priceForDays[indexOfNearestCoupon][2] = 1;
        }

        Collections.sort(couponUsageDays);
        StringBuilder messageCouponUsageDays = new StringBuilder();
        for (Integer index : couponUsageDays) {
            if (messageCouponUsageDays.isEmpty()) {
                messageCouponUsageDays.append(index);
            } else {
                messageCouponUsageDays.append(" ").append(index);
            }
        }

        writer.write(String.valueOf(minCost));
        writer.newLine();
        writer.write(countCoupons - countUsedCoupons + " " + countUsedCoupons);
        writer.newLine();
        writer.write(messageCouponUsageDays.toString());
        writer.newLine();

        reader.close();
        writer.close();
    }

    public static int findFreeCoupon(int index, int[][] priceForDays) {
        int indexOfNearestCoupon = priceForDays[index][1];
        boolean isUsedCoupon = priceForDays[indexOfNearestCoupon][2] == 1;
        while (isUsedCoupon) {
            indexOfNearestCoupon = priceForDays[indexOfNearestCoupon][1];
            isUsedCoupon = priceForDays[indexOfNearestCoupon][2] == 1;
        }
        return indexOfNearestCoupon;
    }
}
