package practicum.course_2022.sprint5.exam.A;

/*
A. Пирамидальная сортировка

В данной задаче необходимо реализовать сортировку кучей. При этом кучу необходимо реализовать самостоятельно, использовать имеющиеся в языке реализации нельзя.
Сначала рекомендуется решить задачи про просеивание вниз и вверх.

Тимофей решил организовать соревнование по спортивному программированию, чтобы найти талантливых стажёров.
Задачи подобраны, участники зарегистрированы, тесты написаны. Осталось придумать, как в конце соревнования будет определяться победитель.

Каждый участник имеет уникальный логин. Когда соревнование закончится, к нему будут привязаны два показателя: количество решённых задач Pi и размер штрафа Fi.
Штраф начисляется за неудачные попытки и время, затраченное на задачу.

Тимофей решил сортировать таблицу результатов следующим образом: при сравнении двух участников выше будет идти тот, у которого решено больше задач.
При равенстве числа решённых задач первым идёт участник с меньшим штрафом.
Если же и штрафы совпадают, то первым будет тот, у которого логин идёт раньше в алфавитном (лексикографическом) порядке.

Тимофей заказал толстовки для победителей и накануне поехал за ними в магазин.
В своё отсутствие он поручил вам реализовать алгоритм сортировки кучей (англ. Heapsort) для таблицы результатов.

Формат ввода
В первой строке задано число участников n, 1 ≤ n ≤ 100 000.
В каждой из следующих n строк задана информация про одного из участников.
i-й участник описывается тремя параметрами:

уникальным логином (строкой из маленьких латинских букв длиной не более 20)
числом решённых задач Pi
штрафом Fi
Fi и Pi — целые числа, лежащие в диапазоне от 0 до 109.
Формат вывода
Для отсортированного списка участников выведите по порядку их логины по одному в строке.
*/

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
Если не брать во внимание циклы которые используются для считывание данных и склевания результата (StringBuilder), то есть две основные операции:
1) Вставка в кучу
Есть основной цикл по всем элементам - это сложность O(n) и внутренний цикл который запускается при просеивании вверх - это сложность O(logn).
Т.е. общая сложность на вставке - это O(n*logn)

2) Получение максимального элемента из кучи
Также есть основной цикл по всем элементам - это сложность O(n) и внутренний цикл который запускается при просеивании вниз - это сложность O(logn).
Т.е. общая сложность на получении - это O(n*logn)

Общая сложность алгоритма O(2*n*logn) ~ O(n*logn)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Если не брать во внимание дополнительный массив в который я сначала собираю входящие данные String[] strings = new String[countParticipant] и StringBuilder в котором я склеваю результат.
То папять потребляется линейно, т.е. O(n)

Успешная посылка - 81979223
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int countParticipant = Integer.parseInt(reader.readLine());

            ArrayList<Participant> heap = new ArrayList<>();
            heap.add(null);
            for (int i = 0; i < countParticipant; i++) {
                String[] commands = reader.readLine().split(" ");
                heapAdd(heap, new Participant(commands[0], Integer.parseInt(commands[1]), Integer.parseInt(commands[2])));
            }

            StringBuilder stringBuilder = new StringBuilder(heapPopMax(heap).toString());
            for (int i = 1; i < countParticipant; i++) {
                stringBuilder.append("\n").append(heapPopMax(heap).toString());
            }

            System.out.println(stringBuilder);
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

        int currentIndex = 1;
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
        return result;
    }

    private static void heapAdd(ArrayList<Participant> heap, Participant newParticipant) {
        heap.add(newParticipant);
        int currentIndex = heap.size()-1;
        while (true) {
            if (currentIndex == 1) {
                return;
            }
            int parentIndex = currentIndex / 2;
            Participant parentParticipant = heap.get(parentIndex);
            if (newParticipant.compareTo(parentParticipant) > 0) {
                heap.set(parentIndex, newParticipant);
                heap.set(currentIndex, parentParticipant);
                currentIndex = parentIndex;
            } else {
                return;
            }
        }
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
