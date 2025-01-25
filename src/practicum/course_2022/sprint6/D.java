package practicum.course_2022.sprint6;
/*
D. BFS

Задан неориентированный граф. Обойдите поиском в ширину все вершины, достижимые из заданной вершины s,
и выведите их в порядке обхода, если начинать обход из s.

Формат ввода
В первой строке дано количество вершин n (1 ≤ n ≤ 105) и рёбер m (0 ≤ m ≤ 105).
Далее в m строках описаны рёбра графа. Каждое ребро описывается номерами двух вершин u и v (1 ≤ u, v ≤ n).
В последней строке дан номер стартовой вершины s (1 ≤ s ≤ n).

Гарантируется, что в графе нет петель и кратных рёбер.

Формат вывода
Выведите вершины в порядке обхода,
считая что при запуске от каждой конкретной вершины её соседи будут рассматриваться в порядке возрастания (то есть если вершина 2 соединена с 1 и 3,
то сначала обход пойдёт в 1, а уже потом в 3).

Пример 1
Ввод:
4 4
1 2
2 3
3 4
1 4
3

Вывод:
3 2 4 1

Пример 2
Ввод:
2 1
2 1
1

Вывод:
1 2

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class D {
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
            bfs(Integer.parseInt(reader.readLine()), map, color);
        }
    }

    private static void bfs(int start, HashMap<Integer, ArrayList<Integer>> map, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        queue.add(start);
        color[start - 1] = 1;
        stringBuilder.append(start);
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
                    stringBuilder.append(" ").append(v);
                }
            }
            color[u - 1] = 2;
        }
        System.out.println(stringBuilder);
    }
}
