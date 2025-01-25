package practicum.course_2022.sprint6;
/*
H. Время выходить

Вам дан ориентированный граф. Известно, что все его вершины достижимы из вершины s=1.
Найдите время входа и выхода при обходе в глубину, производя первый запуск из вершины s.
Считайте, что время входа в стартовую вершину равно 0. Соседей каждой вершины обходите в порядке увеличения номеров.

Формат ввода
В первой строке дано число вершин n (1 ≤ n ≤ 2⋅ 105) и рёбер (0 ≤ m ≤ 2 ⋅ 105).
В каждой из следующих m строк записаны рёбра графа в виде пар (from, to), 1 ≤ from ≤ n — начало ребра, 1 ≤ to ≤ n — его конец.
Гарантируется, что в графе нет петель и кратных рёбер.

Формат вывода
Выведите n строк, в каждой из которых записана пара чисел tini, touti — время входа и выхода для вершины i.

Пример 1
Ввод
6 8
2 6
1 6
3 1
2 5
4 3
3 2
1 2
1 4

Вывод
0 11
1 6
8 9
7 10
2 3
4 5

Пример 2
Ввод
3 2
1 2
2 3

Вывод
0 5
1 4
2 3
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class H {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = reader.readLine().split(" ");
            int n = Integer.parseInt(strings[0]);
            int m = Integer.parseInt(strings[1]);

            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                strings = reader.readLine().split(" ");
                int vertexFrom = Integer.parseInt(strings[0]);
                int vertexTo = Integer.parseInt(strings[1]);

                if (map.containsKey(vertexFrom)) {
                    map.get(vertexFrom).add(vertexTo);
                } else {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(vertexTo);
                    map.put(vertexFrom, arrayList);
                }
            }
            dfs(1, map, n);
        }
    }

    private static void dfs(int start_vertex, HashMap<Integer, ArrayList<Integer>> map, int n) {
        int[] color = new int[n];
        int time = 0;
        int[] entry = new int[n];
        int[] leave = new int[n];

        Stack<Integer> stack = new Stack<>();
        stack.push(start_vertex);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (color[v-1] == 0) {
                color[v-1] = 1;
                entry[v-1] = time;
                time++;
                stack.push(v);

                ArrayList<Integer> arrayList = map.get(v);
                if (arrayList == null) continue;
                Collections.sort(arrayList);
                for (int i = arrayList.size()-1; i >= 0; i--) {
                    if (color[arrayList.get(i)-1] == 0) {
                        stack.push(arrayList.get(i));
                    }
                }
            } else if (color[v-1] == 1) {
                color[v-1] = 2;
                leave[v-1] = time;
                time++;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(entry[i]).append(" ").append(leave[i]).append("\n");
        }
        System.out.println(stringBuilder);
    }
}
