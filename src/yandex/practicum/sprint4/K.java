package yandex.practicum.sprint4;

/*
K. Ближайшая остановка

Гоша едет в гости к друзьям.
Ему придётся сначала ехать на метро, а потом пересаживаться на автобус.
Гоша не любит долго ждать, поэтому хочет выбрать такую станцию метро, рядом с которой расположено как можно больше остановок автобуса.
Гоша считает, что остановка находится рядом с метро, если расстояние между ними не превосходит 20 метров.
Гоше известны все координаты автобусных остановок и координаты выходов из метро.
Помогите ему найти выход из метро, рядом с которым расположено больше всего остановок.

Напомним, что расстояние между двумя точками с координатами x1, y1 и x2, y2 вычисляется по формуле - корень квадратный из (x1-x2)^2 + (y1-y2)^2.

Пояснение к примеру 1:
Рядом с 1-м выходом (-1, 0) находится только остановка с координатами (10, 0).
Рядом со 2-м выходом (1, 0) находятся уже две остановки —– (10, 0) и (20, 0).
Рядом с 3-м выходом (2, 5) расположены все три остановки –— (22, 5), (20, 0) и (10, 0)

Пояснение к примеру 2:
Третий выход теперь находится в точке (0, 5). Он рядом только с двумя остановками — (20, 5) и (10, 0).
Рядом с 2-м и 3-м выходом одинаковое число остановок, поэтому в ответ идет 2-й выход, так как он раньше во входных данных.

Формат ввода
В первой строке дано количество выходов из метро –— натуральное число n (1 ≤ n ≤ 104).
В следующих n строках даны координаты выходов из метро.
Каждый выход описывается двумя координатами x и y, записанными через пробел.
В следующей строке дано количество автобусных остановок —– натуральное число m (1 ≤ m ≤ 106).
В следующих m строках заданы координаты остановок. Каждая остановка описывается двумя числами —– своими x и y координатами, записанными через пробел.
Все координаты —– целые числа, не превосходящие 109 по модулю. Единицы измерения —– метры.

Формат вывода
Выведите единственное число –— номер того выхода из метро, рядом с которым расположено больше всего остановок.
Номер выхода –— его порядковый номер во входных данных (нумерация с единицы).
Если подходящих выходов из метро несколько, выведите тот, который встречается раньше во входных данных.

Пример 1
Ввод
3
-1 0
1 0
2 5
3
10 0
20 0
22 5

Вывод
3

Пример 2
Ввод
3
-1 0
1 0
0 5
3
10 0
20 0
20 5

Вывод
2

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class K {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            ArrayList<MetroStation> stationsMetro = new ArrayList<>();
            Integer n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                String[] strings = reader.readLine().split(" ");
                stationsMetro.add(new MetroStation(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), i+1));
            }

            Integer m = Integer.parseInt(reader.readLine());
            int maxCountBusStations = 0;
            int maxNumberStation = 0;
            for (int j = 0; j < m; j++) {
                String[] busStrings = reader.readLine().split(" ");
                int xBus = Integer.parseInt(busStrings[0]);
                int yBus = Integer.parseInt(busStrings[1]);
                for (MetroStation metro : stationsMetro) {
                    double size = Math.pow((metro.x - xBus), 2) + Math.pow((metro.y - yBus), 2);
                    if (size<= 400d) {
                        ++metro.countBusStations;
                    }
                    if (maxCountBusStations < metro.countBusStations) {
                        maxCountBusStations = metro.countBusStations;
                        maxNumberStation = metro.numberStation;
                    }
                }
            }
            System.out.println(maxNumberStation);
        }
    }

    public static class MetroStation {
        int numberStation;
        int countBusStations;
        int x;
        int y;

        public MetroStation(int x, int y, int numberStation) {
            this.numberStation = numberStation;
            this.x = x;
            this.y = y;
            this.countBusStations = 0;
        }
    }
}
