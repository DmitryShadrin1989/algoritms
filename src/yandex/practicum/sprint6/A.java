package yandex.practicum.sprint6;

/*
A. Построить список смежности
Алла пошла на стажировку в студию графического дизайна, где ей дали такое задание: для очень большого числа ориентированных графов преобразовать их список рёбер в список смежности. Чтобы побыстрее решить эту задачу, она решила автоматизировать процесс.

Помогите Алле написать программу, которая по списку рёбер графа будет строить его список смежности.

Формат ввода
В первой строке дано число вершин n (1 ≤ n ≤ 100) и число ребер m (1 ≤ m ≤ n(n-1)). В следующих m строках заданы ребра в виде пар вершин (u,v), если ребро ведет от u к v.

Формат вывода
Выведите информацию о рёбрах, исходящих из каждой вершины.

В строке i надо написать число рёбер, исходящих из вершины i, а затем перечислить вершины, в которые ведут эти рёбра –— в порядке возрастания их номеров.

Пример:
Ввод
5 3
1 3
2 3
5 2

Вывод:
1 3
1 3
0
0
1 2

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = reader.readLine().split(" ");
            int n = Integer.parseInt(strings[0]);
            int m = Integer.parseInt(strings[1]);

            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                String[] s = reader.readLine().split(" ");
                int vertex = Integer.parseInt(s[0]);
                map.computeIfAbsent(vertex, k -> new ArrayList<>());
                map.get(vertex).add(Integer.parseInt(s[1]));
            }

            for (int i = 1; i <= n; i++) {
                if (map.containsKey(i)) {
                    ArrayList<Integer> arrayList = map.get(i);
                    Collections.sort(arrayList);
                    StringBuilder stringBuilder = new StringBuilder(String.valueOf(arrayList.size()));
                    for (Integer integer : arrayList) {
                        stringBuilder.append(" ").append(integer);
                    }
                    System.out.println(stringBuilder);
                } else {
                    System.out.println(0);
                }
            }
        }
    }
}
