package yandex.practicum.sprint6;
/*
G. Максимальное расстояние

Под расстоянием между двумя вершинами в графе будем понимать длину кратчайшего пути между ними в рёбрах.
Для данной вершины s определите максимальное расстояние от неё до другой вершины неориентированного графа.

Формат ввода
В первой строке дано количество вершин n (1 ≤ n ≤ 105) и рёбер m (0 ≤ m ≤ 105).
Далее в m строках описаны рёбра графа. Каждое ребро описывается номерами двух вершин u и v (1 ≤ u, v ≤ n).
В последней строке дан номер вершины s (1 ≤ s ≤ n). Гарантируется, что граф связный и что в нём нет петель и кратных рёбер.

Формат вывода
Выведите длину наибольшего пути от s до одной из вершин графа.

Пример 1
Ввод:
5 4
2 1
4 5
4 3
3 2
2

Вывод:
3

Пример 2
Ввод:
3 3
3 1
1 2
2 3
1

Вывод:
1

Пример 3
Ввод:
6 8
6 1
1 3
5 1
3 5
3 4
6 5
5 2
6 2
4

Вывод:
3
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = reader.readLine().split(" ");
            int n = Integer.parseInt(strings[0]);
            int m = Integer.parseInt(strings[1]);

            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                strings = reader.readLine().split(" ");
                int nodeV = Integer.parseInt(strings[0]);
                int nodeW = Integer.parseInt(strings[1]);

                if (map.containsKey(nodeV)) {
                    map.get(nodeV).add(nodeW);
                } else {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(nodeW);
                    map.put(nodeV, arrayList);
                }

                if (map.containsKey(nodeW)) {
                    map.get(nodeW).add(nodeV);
                } else {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(nodeV);
                    map.put(nodeW, arrayList);
                }
            }
            int[] color = new int[n];
            int[] distance = new int[n];
            bfs(Integer.parseInt(reader.readLine()), map, color, distance);
        }
    }

    private static void bfs(int start, HashMap<Integer, ArrayList<Integer>> map, int[] color, int[] distance) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start - 1] = 1;
        distance[start - 1] = 0;
        int maxDistance = 0;
        while (queue.size() != 0) {
            int u = queue.poll();
            ArrayList<Integer> arrayList = map.get(u);
            if (arrayList == null) continue;
            Collections.sort(arrayList);
            for (int i = 0; i < arrayList.size(); i++) {
                int v = arrayList.get(i);
                if (color[v - 1] == 0) {
                    queue.add(arrayList.get(i));
                    color[v - 1] = 1;
                    distance[v - 1] = distance[u - 1] + 1;
                    maxDistance = Math.max(maxDistance, distance[v - 1]);
                }
            }
            color[u - 1] = 2;
        }
        System.out.println(maxDistance);
    }
}
