package yandex.practicum.sprint6;
/*
C. DFS
Задан неориентированный граф. Обойдите с помощью DFS все вершины, достижимые из заданной вершины s, и выведите их в порядке обхода, если начинать обход из s.

Формат ввода
В первой строке дано количество вершин n (1 ≤ n ≤ 105) и рёбер m (0 ≤ m ≤ 105). Далее в m строках описаны рёбра графа.
Каждое ребро описывается номерами двух вершин u и v (1 ≤ u, v ≤ n). В последней строке дан номер стартовой вершины s (1 ≤ s ≤ n). В графе нет петель и кратных рёбер.

Формат вывода
Выведите вершины в порядке обхода, считая что при запуске от каждой конкретной вершины её соседи будут рассматриваться в
порядке возрастания (то есть если вершина 2 соединена с 1 и 3, то сначала обход пойдёт в 1, а уже потом в 3).

Пример 1
Ввод
4 4
3 2
4 3
1 4
1 2
3

Вывод
3 2 1 4

Пример 2
Ввод
2 1
1 2
1


Вывод
1 2
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class C {
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
            dfs(Integer.parseInt(reader.readLine()), map, n);
        }
    }

    private static void dfs(int start_vertex, HashMap<Integer, ArrayList<Integer>> map, int n) {
        int[] color = new int[n];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(start_vertex);
        Stack<Integer> stack = new Stack<>();
        stack.push(start_vertex);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (color[v-1] == 0) {
                if (v != start_vertex) stringBuilder.append(" ").append(v);
                color[v-1] = 1;
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
            }
        }
        System.out.println(stringBuilder);
    }
}
