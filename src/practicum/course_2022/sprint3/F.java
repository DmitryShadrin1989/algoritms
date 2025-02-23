package practicum.course_2022.sprint3;

/*
F. Периметр треугольника

Перед сном Рита решила поиграть в игру на телефоне. Дан массив целых чисел, в котором каждый элемент обозначает длину стороны треугольника. Нужно определить максимально возможный периметр треугольника, составленного из сторон с длинами из заданного массива. Помогите Рите скорее закончить игру и пойти спать.

Напомним, что из трёх отрезков с длинами a ≤ b ≤ c можно составить треугольник, если выполнено неравенство треугольника: c < a + b

Разберём пример:
даны длины сторон 6, 3, 3, 2. Попробуем в качестве наибольшей стороны выбрать 6. Неравенство треугольника не может выполниться, так как остались 3, 3, 2 —– максимальная сумма из них равна 6.

Без шестёрки оставшиеся три отрезка уже образуют треугольник со сторонами 3, 3, 2. Неравенство выполняется: 3 < 3 + 2. Периметр равен 3 + 3 + 2 = 8.

Формат ввода
В первой строке записано количество отрезков n, 3≤ n≤ 10000.

Во второй строке записано n неотрицательных чисел, не превосходящих 10 000, –— длины отрезков.

Формат вывода
Нужно вывести одно число —– наибольший периметр треугольника.

Гарантируется, что тройка чисел, которая может образовать треугольник, всегда есть.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class F {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(reader.readLine());
            String[] strings = reader.readLine().split(" ");
            int[] ints = new int[count];
            for (int i = 0; i < count; i++) {
                ints[i] = Integer.parseInt(strings[i]);
            }
            Arrays.sort(ints);

            for (int i = count-3; i >= 0; i--) {
                 int c = ints[i+2];
                 int a = ints[i+1];
                 int b = ints[i];

                 if (c < (a+b)) {
                     System.out.println(a+b+c);
                     return;
                 }
            }

        }
    }
}
