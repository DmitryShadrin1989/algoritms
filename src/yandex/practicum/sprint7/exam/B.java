package yandex.practicum.sprint7.exam;
/*
B. Одинаковые суммы

На Алгосах устроили турнир по настольному теннису.
Гоша выиграл n партий, получив при этом некоторое количество очков за каждую из них.

Гоше стало интересно, можно ли разбить все заработанные им во время турнира очки на две части так,
чтобы сумма в них была одинаковой.

Формат ввода
В первой строке записано целое число n (0 ≤ n ≤ 300) –— количество выигранных партий.

Во второй строке через пробел записано n целых неотрицательных чисел,
каждое из которых не превосходит 300 –— заработанные в партиях очки.

Формат вывода
Нужно вывести True, если произвести такое разбиение возможно, иначе —– False

Пример 1
Ввод
4
1 5 7 1

Вывод
True

Пример 2
Ввод
3
2 10 9

Вывод
False
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            String[] s = reader.readLine().split(" ");
            int[] points = new int[n];
            int fullAmount = 0;
            for (int i = 0; i < n; i++) {
                int point = Integer.parseInt(s[i]);
                points[i] = point;
                fullAmount += point;
            }
            if (fullAmount % 2 != 0) {
                System.out.println("False");
                return;
            }

            int halfAmount = fullAmount / 2;
            int[] dp = new int[halfAmount + 1];
            dp[0] = 1;
            for (int point : points) {
                for (int i = halfAmount; i >= point; --i) {
                    if (dp[i - point] == 1) {
                        dp[i] = 1;
                    }
                }
            }
            if (dp[halfAmount] != 1) {
                System.out.println("False");
            } else {
                System.out.println("True");
            }
        }
    }
}
