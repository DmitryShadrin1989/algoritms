package practicum.course_2022.sprint6;
/*
J. Топологическая сортировка

Дан ациклический ориентированный граф (так называемый DAG, directed acyclic graph). Найдите его топологическую сортировку,
то есть выведите его вершины в таком порядке, что все рёбра графа идут слева направо. У графа может быть несколько подходящих перестановок вершин.
Вам надо найти любую топологическую сортировку.

Формат ввода
В первой строке даны два числа – количество вершин n (1 ≤ n ≤ 105) и количество рёбер m (0 ≤ m ≤ 105).
В каждой из следующих m строк описаны рёбра по одному на строке. Каждое ребро представлено парой вершин (from, to), 1≤ from, to ≤ n,
соответственно номерами вершин начала и конца.

Формат вывода
Выведите номера вершин в требуемом порядке.

Пример 1
Ввод
5 3
3 2
3 4
2 5

Вывод
1 3 2 4 5

Пример 2
Ввод
6 3
6 4
4 1
5 1

Вывод
2 3 5 6 4 1

Пример 3
Ввод
4 0

Вывод
1 2 3 4

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class J {
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
            int[] color = new int[n];
            Stack<Integer> order = new Stack<>();

            for(int i = 0; i < n; i++) {
                if (color[i] == 0) {
                    dfs(i+1, map, n, color, order);
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(order.pop());
            while (!order.isEmpty()) {
                stringBuilder.append(" ").append(order.pop());
            }
            System.out.println(stringBuilder);
        }
    }

    private static void dfs(int start_vertex, HashMap<Integer, ArrayList<Integer>> map, int n, int[] color, Stack<Integer> order) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start_vertex);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (color[v-1] == 0) {
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
                order.push(v);
            }
        }
    }
}
