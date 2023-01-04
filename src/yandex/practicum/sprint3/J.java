package yandex.practicum.sprint3;

/*
J. Пузырёк

Чтобы выбрать самый лучший алгоритм для решения задачи, Гоша продолжил изучать разные сортировки. На очереди сортировка пузырьком — https://ru.wikipedia.org/wiki/Сортировка_пузырьком

Её алгоритм следующий (сортируем по неубыванию):

На каждой итерации проходим по массиву, поочередно сравнивая пары соседних элементов. Если элемент на позиции i больше элемента на позиции i + 1, меняем их местами. После первой итерации самый большой элемент всплывёт в конце массива.
Проходим по массиву, выполняя указанные действия до тех пор, пока на очередной итерации не окажется, что обмены больше не нужны, то есть массив уже отсортирован.
После не более чем n – 1 итераций выполнение алгоритма заканчивается, так как на каждой итерации хотя бы один элемент оказывается на правильной позиции.

Помогите Гоше написать код алгоритма.
Формат ввода
В первой строке на вход подаётся натуральное число n — длина массива, 2 ≤ n ≤ 1000.
Во второй строке через пробел записано n целых чисел.
Каждое из чисел по модулю не превосходит 1000.

Обратите внимание, что считывать нужно только 2 строки: значение n и входной массив.

Формат вывода
После каждого прохода по массиву, на котором какие-то элементы меняются местами, выводите его промежуточное состояние.
Таким образом, если сортировка завершена за k меняющих массив итераций, то надо вывести k строк по n чисел в каждой — элементы массива после каждой из итераций.
Если массив был изначально отсортирован, то просто выведите его.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class J {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(reader.readLine());
            String[] strings = reader.readLine().split(" ");
            int[] ints = new int[count];
            for (int i=0; i<strings.length; i++) {
                ints[i] = Integer.parseInt(strings[i]);
            }

            boolean needSort = true;
            boolean readyStart = true;
            while (needSort) {
                needSort = false;
                StringBuilder stringBuilder = new StringBuilder();
                for (int i=1; i<ints.length; i++) {
                    if (ints[i]<ints[i-1]) {
                        int tmp = ints[i];
                        ints[i] = ints[i-1];
                        ints[i-1] = tmp;

                        needSort = true;
                        readyStart = false;
                    }
                    stringBuilder.append(ints[i-1]).append(" ");
                }
                stringBuilder.append(ints[count-1]);
                if (needSort) System.out.println(stringBuilder);
            }
            if (readyStart) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(ints[0]);
                for (int i=1; i<ints.length; i++) {
                    stringBuilder.append(" ").append(ints[i]);
                }
                System.out.println(stringBuilder);
            }
        }
    }
}
