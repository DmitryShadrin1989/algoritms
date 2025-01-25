package practicum.course_2022.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
K. Достопримечательности

Вы приехали на архипелаг Алгосы (наконец-то!). Здесь есть n достопримечательностей.
Ваша лодка может высадить вас у одной из них, забрать у какой-то другой, возможно, той же самой достопримечательности и увезти на материк.

Чтобы более тщательно спланировать свой маршрут, вы хотите узнать расстояния между каждой парой достопримечательностей Алгосов.
Некоторые из них соединены мостами, по которым вы можете передвигаться в любую сторону. Всего мостов m.

Есть вероятность, что карта архипелага устроена так,
что нельзя добраться от какой-то одной достопримечательности до другой без использования лодки.

Найдите кратчайшие расстояния между всеми парами достопримечательностей.

Формат ввода
В первой строке даны числа n (1 ≤ n ≤ 100) и m (0 ≤ m ≤ n (n-1)/2).
В следующих m строках описаны мосты. Каждый мост задаётся номерами двух достопримечательностей 1 ≤ u, v ≤ n и длиной дороги 1 ≤ L ≤ 103.

Формат вывода
Выведите матрицу n × n кратчайших расстояний.
На пересечении i-й строки и j-го столбца должно стоять расстояние от i-й до j-й достопримечательности.
Если между какой-то парой нет пути, то в соответствующей клетке поставьте -1.

Пример 1
Ввод:
4 4
1 2 1
2 3 3
3 4 5
1 4 2

Вывод:
0 1 4 2
1 0 3 3
4 3 0 5
2 3 5 0

Пример 2
Ввод:
3 2
1 2 1
1 2 2

Вывод:
0 1 -1
1 0 -1
-1 -1 0

Пример 3
Ввод:
2 0

Вывод:
0 -1
-1 0

 */
public class K {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = reader.readLine().split(" ");
            int n = Integer.parseInt(strings[0]);
            int m = Integer.parseInt(strings[1]);

            int[][] graph = new int[n][n];

            for (int i = 0; i < m; i++) {
                strings = reader.readLine().split(" ");
                int vFrom = Integer.parseInt(strings[0]);
                int vDo = Integer.parseInt(strings[1]);
                int weight = Integer.parseInt(strings[1]);
                graph[vFrom - 1][vDo - 1] = weight;
            }

        }
    }

    public static void searchDistance(int[][] graph, int n) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = -1;
                }
            }
        }



    }

    public static void dijkstra(int[][] graph, int s, int n) {
        int[] visited = new int[n];

    }
}
