package yandex.practicum.sprint3.exam;

/*
B. Эффективная быстрая сортировка
 */

/*
-- ПРИНЦИП РАБОТЫ --
Мой алгоритм быстрой сортировки использует рекурсию. На вход подается массив и индесы откуда и до куда сортировать (подмассив сортировки).
На первой итерации передается начало и конец всего массива, вычисляется опорный элемент (в центре данного подмассива).
Создаются два указателя в начале и конце подмассива и с помощью цикла указатели начинают "шагать" друг другу, левый указатель ищет значения которые больше значения опорного,
а правый меньше опроного, когда слева и справа находятся такие элементы они меняются местами.
Дальше происходить рекурсивный вызов уже для левого и правого подмассивов и все повторяется.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Чтобы выполнять сравнение по составному ключу - участников я выделил в отдельный класс с соотвествующими свойствами. Реализовал интерфейс Comparable и
в методе compareTo выполняю сравнение сначала по колличеству решенных задач, если результат сравнения = 0, то выполняется проверка на количество штрафов,
если результат сравнения = 0, то лексиграфически сравниваются строки.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
В худшем случае данный алгоритм будет работать O(n*n). В лучшем и среднем O(n*log(n))

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Данный алгоритм не использует дополнительную память, поэтому O(1)

Иван Самсонов - А как же рекурсия?

Дмитрий Шадрин - Хм... Рекурсивный механизм расходует память Стэка, на каждом витке выделяется место под примитивные значения и ссылки (данные по которым лежат в heap).
Как завершается работа метода место освобождается.
Получается, что пространственная сложность алгоритма будет аналогична временной, с той поправкой, что на пике в StackTrace вызовов должно быть в два раза меньше, т.е.
В худшем случае будет работать O(n*n/2). В лучшем и среднем O(n*log(n)/2).

Иван самсонов - Тут пока неточно, к сожалению.
Во-первых я не очень понял почему в два раза меньше? Во-вторых почему n^2 и nlogn если мы на каждом шаге рекурсии выделяем O(1) памяти,
то есть нас интересует только глубина стека в среднем и худшем случае

Шадрин Дмитрий - Мои размышления привели меня аот к этому:) Мы погружаемся в рекурсиях и делим подмассивы пока не остается один элемент, т.е. нам нужно добраться до каждого элемента.
И поэтому получается глубина рекурсии будет O(n) причем - это будет и в худшем и в среднем случае.

 Успешная посылка - 79515740
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(reader.readLine());
            Participant[] participants = new Participant[count];
            for (int i=0; i<count; i++) {
                String[] strings = reader.readLine().split(" ");
                participants[i] = new Participant(strings[0], Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
            }
            quickSorted(participants, 0, count-1);
            for (int i=0; i<count; i++) {
                System.out.println(participants[i]);
            }
        }
    }

    public static void quickSorted(Participant[] participants, int from, int to) {
        if(from < to) {
            int leftMarker = from;
            int rightMarker = to;
            Participant pilot = participants[(from + to)/2];
            while(leftMarker <= rightMarker) {
                while(participants[leftMarker].compareTo(pilot)>0) {
                    leftMarker++;
                }
                while(participants[rightMarker].compareTo(pilot)<0) {
                    rightMarker--;
                }
                if (leftMarker <= rightMarker) {
                    Participant tmp = participants[leftMarker];
                    participants[leftMarker] = participants[rightMarker];
                    participants[rightMarker] = tmp;

                    leftMarker++;
                    rightMarker--;
                }
            }
            quickSorted(participants, from, leftMarker-1);
            quickSorted(participants, leftMarker, to);
        }
    }

    public static class Participant implements Comparable<Participant> {
        String name;
        int numberSolvedTasks;
        int numberFines;

        public Participant(String name, int numberSolvedTasks, int numberFines) {
            this.name = name;
            this.numberSolvedTasks = numberSolvedTasks;
            this.numberFines = numberFines;
        }

        @Override
        public int compareTo(Participant p) {
            int result = this.numberSolvedTasks - p.numberSolvedTasks;
            if (result == 0) {
                result = p.numberFines - this.numberFines;
            }
            if (result == 0) {
                result = p.name.compareTo(this.name);
            }
            return result;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
