package yandex.practicum.sprint6.exam.A;
/*
A. Дорогая сеть

Тимофей решил соединить все компьютеры в своей компании в единую сеть.
Для этого он придумал построить минимальное остовное дерево, чтобы эффективнее использовать ресурсы.

Но от начальства пришла новость о том, что выделенный на сеть бюджет оказался очень большим и его срочно надо израсходовать.
Поэтому Тимофея теперь интересуют не минимальные, а максимальные остовные деревья.

Он поручил вам найти вес такого максимального остовного дерева в неориентированном графе, который задаёт схему офиса.

Формат ввода
В первой строке дано количество вершин n и ребер m графа (1 ≤ n ≤ 1000, 0 ≤ m ≤ 100000).

В каждой из следующих m строк заданы рёбра в виде троек чисел u, v, w. u и v — вершины, которые соединяет это ребро.
w — его вес ( 1 ≤ u, v ≤ n, 0 ≤ w ≤ 10000). В графе могут быть петли и кратные ребра. Граф может оказаться несвязным.

Формат вывода
Если максимальное остовное дерево существует, то выведите его вес.
Иначе (если в графе несколько компонент связности) выведите фразу «Oops! I did it again».

Пример 1
Ввод:
4 4
1 2 5
1 3 6
2 4 8
3 4 3

Вывод:
19

Пример 2
Ввод:
3 3
1 2 1
1 2 2
2 3 1

Вывод:
3

Пример 3
Ввод:
2 0

Вывод:
Oops! I did it again
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class A {
    static TreeSet<Edge> edges = new TreeSet<>();
    static ArrayList<Edge> maxSpanningTree = new ArrayList<>();
    static ArrayList<Integer> added = new ArrayList<>();
    static HashMap<Integer, ArrayList<Edge>> notAdded = new HashMap<>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = reader.readLine().split(" ");
            int countVertex = Integer.parseInt(strings[0]);
            int countEdge = Integer.parseInt(strings[1]);

            HashMap<Integer, ArrayList<Edge>> map = new HashMap<>();
            for (int i = 0; i < countVertex; i++) {
                map.put(i+1, new ArrayList<>());
            }
            for (int i = 0; i < countEdge; i++) {
                String[] s = reader.readLine().split(" ");
                int vertex1 = Integer.parseInt(s[0]);
                int vertex2 = Integer.parseInt(s[1]);
                int weight = Integer.parseInt(s[2]);

                Edge edge1 = new Edge(vertex1, vertex2, weight);
                map.get(vertex1).add(edge1);
                Edge edge2 = new Edge(vertex2, vertex1, weight);
                map.get(vertex2).add(edge2);
            }
            findMaxST(map);
        }
    }

    public static void findMaxST(HashMap<Integer, ArrayList<Edge>> graph) {
        notAdded = graph;
        addVertex(1);

        while (notAdded.size() != 0 && edges.size() != 0) {
            Edge maxEdge = edges.pollFirst();
            if (notAdded.containsKey(maxEdge.end)) {
                maxSpanningTree.add(maxEdge);
                addVertex(maxEdge.end);
            }
        }
        if (notAdded.size() != 0) {
            System.out.println("Oops! I did it again");
        } else {
            int weight = 0;
            for (Edge edge:maxSpanningTree) {
                weight += edge.weight;
            }
            System.out.println(weight);
        }
    }

    public static void addVertex(int v) {
        added.add(v);
        ArrayList<Edge> edgeArrayList = notAdded.remove(v);
        for (Edge edge:edgeArrayList) {
            if (edge.start == v && notAdded.containsKey(edge.end)) {
                edges.add(edge);
            }
        }
    }

    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        public Edge(int vertex1, int vertex2, int weight) {
            this.start = vertex1;
            this.end = vertex2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return other.weight - this.weight;
        }
    }
}
