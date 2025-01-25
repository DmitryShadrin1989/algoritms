package practicum.course_2022.sprint7;
/*
K. Гороскопы

В мире последовательностей нет гороскопов. Поэтому когда две последовательности хотят понять,
могут ли они счастливо жить вместе, они оценивают свою совместимость как длину их наибольшей общей подпоследовательности.

Подпоследовательность получается из последовательности удалением некоторого (возможно, нулевого) числа элементов.
То есть элементы сохраняют свой относительный порядок, но не обязаны изначально идти подряд.

Найдите наибольшую общую подпоследовательность двух одиноких последовательностей и выведите её!

Формат ввода
В первой строке дано число n — количество элементов в первой последовательности (1 ≤ n ≤ 1000).
Во второй строке даны n чисел ai (0 ≤ |ai| ≤ 109) — элементы первой последовательности.
Аналогично в третьей строке дано m (1 ≤ m ≤ 1000) — число элементов второй последовательности.
В четвертой строке даны элементы второй последовательности через пробел bi (0 ≤ |bi| ≤ 109).

Формат вывода
Сначала выведите длину найденной наибольшей общей подпоследовательности,
во второй строке выведите индексы элементов первой последовательности, которые в ней участвуют,
в третьей строке — индексы элементов второй последовательности.
Нумерация индексов с единицы, индексы должны идти в корректном порядке.

Если возможных НОП несколько, то выведите любую.

Пример 1
Ввод
5
4 9 2 4 6
7
9 4 0 0 2 8 4



Вывод
3
1 3 4
2 5 7

Пример 2
Ввод
4
1 1 1 1
2
2 2

Вывод
0

Пример 3
Ввод
8
1 2 1 9 1 2 1 9
5
9 9 1 9 9

Вывод
3
3 4 8
3 4 5

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class K {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            String[] sA = reader.readLine().split(" ");
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(sA[i]);
            }

            int m = Integer.parseInt(reader.readLine());
            String[] sB = reader.readLine().split(" ");
            int[] b = new int[m];
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(sB[i]);
            }

            int dp[][] = new int[m+1][n+1];

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (a[j-1] == b[i-1]) {
                        dp[i][j] = 1 + dp[i-1][j-1];
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }

            int s = 0;

            StringBuilder res_a = new StringBuilder();
            StringBuilder res_b = new StringBuilder();

            int a_pos = n;
            int b_pos = m;
            while (a_pos > 0 && b_pos > 0) {
                if (a[a_pos - 1] == b[b_pos - 1]) {
                    a_pos--;
                    b_pos--;

                    if (res_a.length() > 0) {
                        res_a.append(" ");
                        res_b.append(" ");
                    }
                    res_a.append(a_pos+1);
                    res_b.append(b_pos+1);
                } else {
                    if (dp[b_pos - 1][a_pos] < dp[b_pos][a_pos - 1]) {
                        a_pos--;
                    } else {
                        b_pos--;
                    }
                }
            }
            System.out.println(dp[m][n]);
            System.out.println(res_a.reverse());
            System.out.println(res_b.reverse());
        }
    }
}
