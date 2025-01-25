package practicum.course_2022.sprint8;

/*
A. Разворот строки

В некоторых языках предложения пишутся и читаются не слева направо, а справа налево.

Вам под руку попался странный текст –— в нём обычный (слева направо) порядок букв в словах.
А вот сами слова идут в противоположном направлении.
Вам надо преобразовать текст так, чтобы слова в нём были написаны слева направо.

Формат ввода
На ввод подаётся строка, состоящая из слов, разделённых пробелами (один пробел между соседними словами).
Всего слов не более 1000, длина каждого из них —– от 1 до 100 символов. Слова состоят из строчных букв английского алфавита.

Формат вывода
Выведите строку с обратным порядком слов в ней.

Пример 1
Ввод
one two three

Вывод
three two one

Пример 2
Ввод
hello

Вывод
hello

Пример 3
Ввод
may the force be with you

Вывод
you with be force the may
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = reader.readLine().split(" ");
            int i = 0;
            int j = strings.length - 1;
            while(i<j) {
                String tmp = strings[i];
                strings[i] = strings[j];
                strings[j] = tmp;
                i++;
                j--;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int k = 0; k < strings.length; k++) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.append(" ");
                }
                stringBuilder.append(strings[k]);
            }
            System.out.println(stringBuilder);
        }
    }
}
