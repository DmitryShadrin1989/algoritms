package yandex.practicum.sprint7.exam;
/*
A. Расстояние по Левенштейну

Расстоянием Левенштейна между двумя строками s и t называется количество атомарных изменений,
с помощью которых можно одну строку превратить в другую.
Под атомарными изменениями подразумеваются: удаление одного символа, вставка одного символа, замена одного символа на другой.

Найдите расстояние Левенштейна для предложенной пары строк.

Выведите единственное число — расстояние между строками.

Формат ввода
В первой строке дана строка s, во второй — строка t. Длины обеих строк не превосходят 1000.
Строки состоят из маленьких латинских букв.

Пример 1
Ввод
abacaba
abaabc

Вывод
2

Пример 2
Ввод
innokentiy
innnokkentia

Вывод
3

Пример 3
Ввод
r
x

Вывод
1
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s1 = reader.readLine();
            String s2 = reader.readLine();

            int[][] dp = new int[s1.length()+1][s2.length()+1];


        }
    }
}
