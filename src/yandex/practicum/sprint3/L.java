package yandex.practicum.sprint3;
/*
L. Два велосипеда
Вася решил накопить денег на два одинаковых велосипеда — себе и сестре. У Васи есть копилка, в которую каждый день он может добавлять деньги (если, конечно, у него есть такая финансовая возможность). В процессе накопления Вася не вынимает деньги из копилки.

У вас есть информация о росте Васиных накоплений — сколько у Васи в копилке было денег в каждый из дней.

Ваша задача — по заданной стоимости велосипеда определить

первый день, в которой Вася смог бы купить один велосипед,
и первый день, в который Вася смог бы купить два велосипеда.
Подсказка: решение должно работать за O(log n).

Формат ввода
В первой строке дано число дней n, по которым велись наблюдения за Васиными накоплениями. 1 ≤ n ≤ 106.

В следующей строке записаны n целых неотрицательных чисел. Числа идут в порядке неубывания. Каждое из чисел не превосходит 106.

В третьей строке записано целое положительное число s — стоимость велосипеда. Это число не превосходит 106.

Формат вывода
Нужно вывести два числа — номера дней по условию задачи.

Если необходимой суммы в копилке не нашлось, нужно вернуть -1 вместо номера дня.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class L {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int countNumber = Integer.parseInt(reader.readLine());
            String[] strings = reader.readLine().split(" ");
            int[] ints = new int[countNumber];
            for (int i=0; i<countNumber; i++ ) {
                ints[i] = Integer.parseInt(strings[i]);
            }
            int price = Integer.parseInt(reader.readLine());
            System.out.println(binarySearch(price, ints) + " " + binarySearch(price*2, ints));
        }
    }

    private static int binarySearch(int price, int[] ints) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        search(price, ints, 0, ints.length-1, stack);
        return stack.pop();
    }

    private static void search(int price, int[] ints, int left, int right, Stack<Integer> stack) {
        if (left >= right) {
            if (ints[left]>= price) stack.push(left+1);
            return;
        }
        int middle = (left+right)/2;
        int valueMiddle = ints[middle];
        if (price<=valueMiddle) {
            stack.push(middle+1);
            search(price, ints, left, middle, stack);
        } else {
            search(price, ints, middle + 1, right, stack);
        }
    }
}
