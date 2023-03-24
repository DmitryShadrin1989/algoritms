package yandex.practicum.sprint8;
/*
H. Глобальная замена

Напишите программу, которая будет заменять в тексте все вхождения строки s на строку t.
Гарантируется, что никакие два вхождения шаблона s не пересекаются друг с другом.

Формат ввода
В первой строке дан текст —– это строка из строчных букв английского алфавита, длина которой не превышает 106.

Во второй строке записан шаблон s, вхождения которого будут заменены.

В третьей строке дана строка t, которая будет заменять вхождения.

Обе строки s и t состоят из строчных букв английского алфавита, длина каждой строки не превосходит 105.
Размер итоговой строки не превосходит 2⋅ 106.

Формат вывода
В единственной строке выведите результат всех замен — текст, в котором все вхождения s заменены на t.

Пример 1
Ввод
pingpong
ng
mpi

Вывод
pimpipompi

Пример 2
Ввод
aaa
a
ab

Вывод
ababab

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String string = reader.readLine();


        }
    }
}
