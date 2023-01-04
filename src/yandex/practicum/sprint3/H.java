package yandex.practicum.sprint3;

/*
H. Большое число

Вечером ребята решили поиграть в игру «Большое число».
Даны числа. Нужно определить, какое самое большое число можно из них составить.

Формат ввода
В первой строке записано n — количество чисел. Оно не превосходит 100.
Во второй строке через пробел записаны n неотрицательных чисел, каждое из которых не превосходит 1000.

Формат вывода
Нужно вывести самое большое число, которое можно составить из данных чисел.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(reader.readLine());
            String[] strings = reader.readLine().split(" ");

            boolean needSort = true;
            while (needSort) {
                needSort = false;
                for (int i=1; i<count; i++) {
                    String int1 = strings[i-1];
                    String int2 = strings[i];
                    if (less2(int1, int2)) {
                        String tmp = strings[i-1];
                        strings[i-1] = strings[i];
                        strings[i] = tmp;
                        needSort = true;
                    }
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0; i<count; i++) {
                stringBuilder.append(strings[i]);
            }
            System.out.println(stringBuilder);
        }
    }

    public static boolean less2(String int1, String int2) {
        int length1 = int1.length();
        int length2 = int2.length();
        if (length1 == length2) {
           return Integer.parseInt(int1) < Integer.parseInt(int2);
        }
        return Integer.parseInt(int1 + int2) < Integer.parseInt(int2 + int1);
    }
}

