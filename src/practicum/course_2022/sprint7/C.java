package practicum.course_2022.sprint7;
/*
C. Золотая лихорадка
Гуляя по одному из островов Алгосского архипелага, Гоша набрёл на пещеру, в которой лежат кучи золотого песка.
К счастью, у Гоши есть с собой рюкзак грузоподъёмностью до M килограмм,
поэтому он может унести с собой какое-то ограниченное количество золота.

Всего золотых куч n штук, и все они разные.
В куче под номером i содержится mi килограммов золотого песка, а стоимость одного килограмма — ci алгосских франков.

Помогите Гоше наполнить рюкзак так, чтобы общая стоимость золотого песка в пересчёте на алгосские франки была максимальной.

Формат ввода
В первой строке задано целое число M — грузоподъёмность рюкзака Гоши (0 ≤ M ≤ 108).
Во второй строке дано количество куч с золотым песком — целое число n (1 ≤ n ≤ 105).
iВ каждой из следующих n строк описаны кучи: i-ая куча задаётся двумя целыми числами ci и mi,
записанными через пробел (1 ≤ ci ≤ 107, 1 ≤ mi ≤ 108).

Формат вывода
Выведите единственное число —– максимальную сумму (в алгосских франках),
которую Гоша сможет вынести из пещеры в своём рюкзаке.

Пример 1
Ввод:
10
3
8 1
2 10
4 5

Вывод:
36

Пример 2
Ввод:
10000
1
4 20

Вывод:
80
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class C {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int maxWeight = Integer.parseInt(reader.readLine());
            int numberHeap = Integer.parseInt(reader.readLine());

            ArrayList<GoldHeap> heaps = new ArrayList<>();
            for (int i = 0; i < numberHeap; i++) {
                String[] s = reader.readLine().split(" ");
                heaps.add(new GoldHeap(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
            }
            Collections.sort(heaps);
            long weight = 0;
            long sumPrice = 0;
            for (int i = 0; i < numberHeap; i++) {
                GoldHeap goldHeap = heaps.get(i);
                for (int j = 0; j < goldHeap.weight; j++) {
                    if (weight + 1 > maxWeight) {
                        break;
                    }
                    weight++;
                    sumPrice += goldHeap.price;
                }
            }
            System.out.println(sumPrice);
        }
    }

    static class GoldHeap implements Comparable<GoldHeap> {
        int price;
        int weight;

        public GoldHeap(int price, int weight) {
            this.price = price;
            this.weight = weight;
        }

        @Override
        public int compareTo(GoldHeap other) {
            return other.price - this.price;
        }
    }
}
