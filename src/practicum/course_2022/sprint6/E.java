package practicum.course_2022.sprint6;
/*
E. Компоненты связности

Вам дан неориентированный граф. Найдите его компоненты связности.

Формат ввода
В первой строке дано количество вершин n (1≤ n ≤ 105) и рёбер m (0 ≤ m ≤ 2 ⋅ 105).
В каждой из следующих m строк записано по ребру в виде пары вершин 1 ≤ u, v ≤ n.

Гарантируется, что в графе нет петель и кратных рёбер.

Формат вывода
Выведите все компоненты связности в следующем формате: в первой строке выведите общее количество компонент.

Затем на отдельных строках выведите вершины каждой компоненты, отсортированные по возрастанию номеров.
Компоненты между собой упорядочивайте по номеру первой вершины.

Пример 1
Ввод:
6 3
1 2
6 5
2 3

Вывод:
3
1 2 3
4
5 6

Пример 2
Ввод:
2 0

Вывод:
2
1
2

Пример 3
Ввод:
4 3
2 3
2 1
4 3

Вывод:
1
1 2 3 4

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class E {
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
            int countColor = 0;
            for (int i = 0; i < n ; i++) {
                if (color[i] == 0) {
                    ++countColor;
                    dfs(i+1, map, color, countColor);
                }
            }
            System.out.println(countColor);
            for (int i = 1; i <= countColor; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (color[j] == i) {
                        if (stringBuilder.length() != 0) stringBuilder.append(" ");
                        stringBuilder.append(j+1);
                    }
                }
                System.out.println(stringBuilder);
            }
        }
    }

    private static void dfs(int start_vertex, HashMap<Integer, ArrayList<Integer>> map, int[] color, int currentColor) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start_vertex);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (color[v-1] == 0) {
                color[v-1] = -1;
                stack.push(v);

                ArrayList<Integer> arrayList = map.get(v);
                if (arrayList == null) continue;
                Collections.sort(arrayList);
                for (int i = arrayList.size()-1; i >= 0; i--) {
                    if (color[arrayList.get(i)-1] == 0) {
                        stack.push(arrayList.get(i));
                    }
                }
            } else if (color[v-1] == -1) {
                color[v-1] = currentColor;
            }
        }
    }
}
