package yandex.practicum.sprint3;

/*
C. Подпоследовательность
Гоша любит играть в игру «Подпоследовательность»: даны 2 строки, и нужно понять, является ли первая из них подпоследовательностью второй. Когда строки достаточно длинные, очень трудно получить ответ на этот вопрос, просто посмотрев на них. Помогите Гоше написать функцию, которая решает эту задачу.

Формат ввода
В первой строке записана строка s.

Во второй —- строка t.

Обе строки состоят из маленьких латинских букв, длины строк не превосходят 150000. Строки не могут быть пустыми.

Формат вывода
Выведите True, если s является подпоследовательностью t, иначе —– False.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s1 = reader.readLine();
            String s2 = reader.readLine();

            int i = 0;
            int j = 0;
            int length1 = s1.length();
            int length2 = s2.length();

            while (i < length1 && j < length2) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    i++;
                }
                j++;
            }
            if (i == length1) System.out.println("True");
            else System.out.println("False");
        }
    }
}
