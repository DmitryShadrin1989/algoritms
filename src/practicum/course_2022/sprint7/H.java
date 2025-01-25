package practicum.course_2022.sprint7;
/*
H. Поле с цветочками
Черепаха Кондратина путешествует по клетчатому полю из n строк и m столбцов.
В каждой клетке либо растёт цветочек, либо не растёт.
Кондратине надо добраться из левого нижнего в правый верхний угол и собрать как можно больше цветочков.

Помогите ей с этой сложной задачей и определите, какое наибольшее число цветочков она сможет собрать при условии,
что Кондратина умеет передвигаться только на одну клетку вверх или на одну клетку вправо за ход.

Формат ввода
В первой строке даны размеры поля n и m (через пробел). Оба числа лежат в диапазоне от 1 до 1000.
В следующих n строках задано поле. Каждая строка состоит из m символов 0 или 1, записанных подряд без пробелов,
и завершается переводом строки. Если в клетке записана единица, то в ней растёт цветочек.

Формат вывода
Выведите единственное число — максимальное количество цветочков, которое сможет собрать Кондратина.

Пример 1
Ввод
2 3
101
110

Вывод
3

Пример 2
Ввод
3 3
100
110
001

Вывод
2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H {
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
            System.out.println(dp[0][m-1]);
        }
    }
}
