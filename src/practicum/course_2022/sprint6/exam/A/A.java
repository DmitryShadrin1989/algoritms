package practicum.course_2022.sprint6.exam.A;
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

/*
-- ПРИНЦИП РАБОТЫ --
За основу реализации взят алгоритм Прима.
Но по условию задачи необходимо найти максимальное остовное дерево в графе. Т.е. одно из основных условий,
что граф должен быть связным и мы его должны обойти самым "дорогим" путем.
Сам граф храним в HashMap<Integer, ArrayList<Edge>>, где ключ вершина, а значение список ребер - Edge.
Ребро Edge реализовано отдельным классом со свойствами: start, end, weight.
Идея реализации заключается в том, что мы копируем наш граф в аналогичный HashMap - notAdded и когда посещаем вершину,
то удаляем ее из notAdded, т.е. в конце notAdded должна оказаться пустой, иначе граф не связный.
Получаем все ребра удаленной вершины и добавляем ребро в TreeSet<Edge> если его вершина end еще не обработана,
т.е. присутствует в notAdded. Для хранения ребер посещенных вершин выбрал TreeSet<Edge> из за его свойства авто сортировки.
На следующей итерации получаю первое, т.е. самое "дорогое" ребро из TreeSet<Edge>, вершина end этого ребра будет посещена следующей.
Алгоритм повторяется пока либо notAdded либо в TreeSet<Edge> не останется записей.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Основное условие для цикла алгоритма: notAdded.size() != 0 && edges.size() != 0, где notAdded это HashMap<Integer, ArrayList<Edge>>
не посещенные вершины, а edges это TreeSet<Edge> ребра посещенных вершин. Т.е. если ребра посещенных вершин закончатся раньше
чем посещенные вершины - это будет признаком, что данный граф не является связным и мы не можем для него построить остовное дерево.
Для того чтобы возможно было использование TreeSet<Edge> необходимо было имплементировать класса Edge интерфейс Comparable<Edge>
и реализовать метод compareTo. В его реализации сначала делаем сравнение по весу weight, если веса одинаковые то берем ребро с большим end.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
В алгоритме есть 4 последовательных цикла.

1-й цикл заполняет HashMap<Integer, ArrayList<Edge>> map всеми вершинами, в значения добавляются пустыне списки ребер.
Сложность O(V), где V - количество вершин.

2-й цикл считывает входящие данные и заполняет списки ребер для каждой вершины в HashMap<Integer, ArrayList<Edge>>.
Сложность O(E), где E - количество ребер.

Далее идет основная часть алгоритма по нахождению остовного дерева.
Есть внешний цикл по всем вершинам, его сложность O(V), внутри данного цикла сначала извлекается самое тяжелое ребро из TreeSet<Edge>.
TreeSet под капотом использует TreeMap и для хранения использует бинарное красно-черное дерево.
Временная сложность базовых операций O(log(E)), где E - это количество ребер.
И второй цикл внутри внешнего - это вставка в TreeSet<Edge> всех ребер исходящих из текущей вершины.
Временная сложность O(K*log(E)), где K - это количество ребер исходящих из текущей вершины.
Сложность всей части алгоритма O(V)*(O(log(E)) + K*log(E)) ~ O(V*log(E))

Последний цикл используется для подсчета "стоимости" остовного дерева. Обходится ArrayList<Edge> maxSpanningTree.
Сложность O(E)

Общая временная сложность программы O(V) + O(E) + O(V*log(E)) + O(E) = O(E + 2V*log(E)) ~ O(V*log(E))


-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Состоит из:
Отсортированного бинарного дерева ребер TreeSet<Edge> edges.
Сложность O(E), где E - это количество ребер.

Списка максимальных ребер ArrayList<Edge> maxSpanningTree (используется в конце для подсчета "стоимости" остовного дерева).
Сложность O(E)

2х ассоциативных массивов HashMap<Integer, ArrayList<Edge>> notAdded (необработанные вершины) и map (сам изначальный граф).
Сложность O(2*E)

Общая пространственная сложность программы O(E) + O(E) + O(2*E) = O(4*E) ~ O(E)

Успешная посылка - https://contest.yandex.ru/contest/25070/run-report/83161747/
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

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            int result;
            result = other.weight - this.weight;
            if (result == 0) {
                result = other.end;
            }
            return result;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "start=" + start +
                    ", end=" + end +
                    ", weight=" + weight +
                    '}';
        }
    }
}
