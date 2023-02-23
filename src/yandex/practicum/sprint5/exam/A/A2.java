package yandex.practicum.sprint5.exam.A;

/*
-- ПРИНЦИП РАБОТЫ --
Для реализации сортировки кучей использовал динамический массив ArrayList<Participant>, где Participant - это отдельный класс для участников.
Алгоритм состоит из двух частей:
1) Вставка в кучу с просеиванием в вверх, метод - heapAdd. Т.е. новый элемент вставляется в конец и поднимается вверх по кучи если больше своего родителя.
2) Получение из кучи максимального элемента с просеиванием вниз - heapPopMax.
После того как с вершины забрали максимальный элемент, на его место встает последний элемент в массиве и "падает" вниз если он меньше одного из своих потомков.
Для сравнения Участников использую compareTo в созданном классе.
Для удобства тестирования результат собираю в StringBuilder

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Две основые операции - это вставка и получение:
1) При вставке выполняется просеивание вверх и основная проверка нужна, чтобы не уйти в бесконечный цикл и не выйти за границы массива если добавленный элемент дойдет до вершины кучи.
if (currentIndex == 1) return;
2) При получении максимального элемента выполняется просеивание вниз, чтобы  не выйти за границы индекса добавлена проверка -
if (heap.size() > 1) {
    heap.set(1, replacement);
} else {
    return result;
}
Далее с помощью условия - rightIndex <= heap.size() -1 && heap.get(rightIndex).compareTo(heap.get(leftIndex))>0,
определяется с каким (правым или левым) элементом сделать сравнение. И на следующем шаге делаем сравнение и при необходимости меняем элементыми местами.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Есть две основные операции: построение кучи и получение максимального элемента из кучи:
1) Построение кучи
Цикл по всем элементам, делаем вставку в дерево не заботясь о соблюдении основного свойства кучи  - сложность O(n), где это n это количество элементов.
После вставки запускается процедура для востановления основного свойства кучи - Сложность O(n/2*logn).
Т.е. построение кучи - это O(n + n/2*logn) ~ O(n),

2) Получение максимального элемента и востановление свойства кучи
Максимальный элемент лежит в корне поэтому получение выполняется за константное время O(1)
Востановление свойства кучи: есть основной цикл по всем элементам - это сложность O(n), где это n это количество элементов.
и внутренний цикл который запускается при просеивании вниз - это сложность O(logn).
Сложность на востановлении свойства кучи - это O(n*logn),

Общая сложность алгоритма:
O(n) + O(n*logn) ~ O(n*logn)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Основная папять используется для хранения самого дерева ArrayList<Participant> heap, т.е. O(n), где это n это количество элементов.
Дополнительная память используется для склеивания результата перед выводом StringBuilder, и также потребляется линейно, т.е. O(n).

Общая пространственная сложность O(2*n) ~ O(n).

Успешная посылка - 82176147
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class A2 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int countParticipant = Integer.parseInt(reader.readLine());

            ArrayList<Participant> heap = new ArrayList<>();
            heap.add(null);
            for (int i = 0; i < countParticipant; i++) {
                String[] commands = reader.readLine().split(" ");
                heap.add(new Participant(commands[0], Integer.parseInt(commands[1]), Integer.parseInt(commands[2])));
            }
            buildHeap(heap);

            StringBuilder stringBuilder = new StringBuilder(heapPopMax(heap).toString());
            for (int i = 1; i < countParticipant; i++) {
                stringBuilder.append("\n").append(heapPopMax(heap).toString());
            }

            System.out.println(stringBuilder);
        }
    }

    private static void buildHeap(ArrayList<Participant> heap) {
        for (int i = heap.size() / 2; i >= 1; i--)
        {
            heapify(heap, i);
        }
    }

    private static void heapify(ArrayList<Participant> heap, int index) {
        int currentIndex = index;
        while (true) {
            int leftIndex = currentIndex * 2;
            int rightIndex = currentIndex * 2 + 1;
            if (heap.size()-1 < leftIndex) {
                break;
            }

            int indexLargest;
            if (rightIndex <= heap.size() -1 && heap.get(rightIndex).compareTo(heap.get(leftIndex))>0) {
                indexLargest = rightIndex;
            } else {
                indexLargest = leftIndex;
            }

            if (heap.get(indexLargest).compareTo(heap.get(currentIndex))>0) {
                Participant tmp = heap.get(currentIndex);
                heap.set(currentIndex, heap.get(indexLargest));
                heap.set(indexLargest, tmp);
                currentIndex = indexLargest;
            } else {
                break;
            }
        }
    }

    private static Participant heapPopMax(ArrayList<Participant> heap) {
        Participant result = heap.get(1);
        Participant replacement = heap.remove(heap.size()-1);
        if (heap.size() > 1) {
            heap.set(1, replacement);
        } else {
            return result;
        }
        heapify(heap, 1);
        return result;
    }

    static class Participant implements Comparable<Participant> {
        String login;
        int countTasks;
        int fine;

        public Participant(String login, int countTasks, int fine) {
            this.login = login;
            this.countTasks = countTasks;
            this.fine = fine;
        }

        @Override
        public int compareTo(Participant o) {
            int result;
            result = this.countTasks - o.countTasks;
            if (result == 0) {
                result = o.fine - this.fine;
            }
            if (result == 0) {
                result = o.login.compareTo(this.login);
            }
            return result;
        }

        @Override
        public String toString() {
            return login;
        }
    }
}
