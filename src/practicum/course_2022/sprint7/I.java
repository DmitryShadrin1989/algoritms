package practicum.course_2022.sprint7;
/*
I. Сложное поле с цветочками

Теперь черепашке Кондратине надо узнать не только, сколько цветочков она может собрать,
но и как ей построить свой маршрут для этого. Помогите ей!

Напомним, что Кондратине надо дойти от левого нижнего до правого верхнего угла,
а передвигаться она умеет только вверх и вправо.

Формат ввода
В первой строке даны размеры поля n и m (через пробел). Оба числа лежат в диапазоне от 1 до 1000.
В следующих n строках задано поле. Каждая строка состоит из m символов 0 или 1 и завершается переводом строки.
Если в клетке записана единица, то в ней растет цветочек.

Формат вывода
Выведите в первой строке максимальное количество цветочков, которое сможет собрать Кондратина.
Во второй строке выведите маршрут в виде последовательности символов «U» и «R»,
где «U» означает передвижение вверх, а «R» – передвижение вправо.

Если возможных оптимальных путей несколько, то выведите любой.

Пример 1
Ввод
2 3
101
110

Вывод
3
URR

Пример 2
Ввод
3 3
100
110
001

Вывод
2
UURR
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class I {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = reader.readLine().split(" ");
            int n = Integer.parseInt(strings[0]);
            int m = Integer.parseInt(strings[1]);
            int[][] points = new int[n][m];

            for (int i = 0; i < n; i++) {
                String[] values = reader.readLine().split("");
                for (int j = 0; j < m; j++) {
                    points[i][j] = Integer.parseInt(values[j]);
                }
            }
            int[][] dp = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int y = n-i-1;
                    int x = j;

                    int down = 0;
                    if ((y+1) != n) {
                        down = dp[y+1][x];
                    }

                    int left = 0;
                    if (x != 0) {
                        left = dp[y][x-1];
                    }
                    dp[y][x] = Math.max(left, down) + points[y][x];
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            int y = 0;
            int x = m - 1;
            while (y != n || x != 0) {
                if (x == 0) {
                    stringBuilder.append("U");
                    y++;
                    continue;
                }

                int down = 0;
                if (y + 1 < n) {
                    down = dp[y+1][x];
                }

                int left = 0;
                if (x > 0) {
                    left = dp[y][x - 1];
                }

                if (down > left) {
                    y++;
                    stringBuilder.append("U");
                } else {
                    x--;
                    stringBuilder.append("R");
                }
            }
            System.out.println(dp[0][m-1]);
            System.out.println(stringBuilder.deleteCharAt (stringBuilder.length()-1).reverse());
        }
    }
}
