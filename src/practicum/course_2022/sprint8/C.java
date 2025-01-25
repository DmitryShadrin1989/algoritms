package practicum.course_2022.sprint8;
/*
C. Самый длинный палиндром 2

Палиндром —– это строка, которая одинаково читается как слева направо, так и справа налево.

Из данной строки s путём удаления и перестановки букв надо получить палиндром максимальной длины.
Среди всех таких палиндромов надо получить лексикографически минимальный. Количество удалений и перестановок символов может быть любым.

Формат ввода
В единственной строке дана строка s. Длина строки |s| ≤ 105. Строка состоит из строчных букв английского алфавита.

Формат вывода
Выведите полученный палиндром. Заметьте, что ответ определяется однозначно.

Пример 1
Ввод
aaaabb

Вывод
aabbaa

Пример 2
Ввод
pabcd

Вывод
a

Пример 3
Ввод
aaabbb

Вывод
ababa
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class C {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = reader.readLine().split("");
            TreeMap<String, Integer> counter = new TreeMap<>();
            for (String string : strings) {
                int count = 0;
                if (counter.containsKey(string)) {
                    count = counter.get(string);
                }
                counter.put(string, count + 1);
            }

            StringBuilder result = new StringBuilder();
            char center = '%';
            for (Map.Entry<String, Integer> entry: counter.entrySet()) {
                int count = entry.getValue();
                if (count%2 == 0) {
                    result.append(String.valueOf(entry.getKey()).repeat(Math.max(0, count / 2)));
                } else {
                    if (center == '%') {
                        center = entry.getKey().charAt(0);
                    }
                    if (count > 1) {
                        result.append(String.valueOf(entry.getKey()).repeat(count / 2));
                    }
                }
            }
            String revers = result.reverse().toString();
            result.reverse();
            if (center != '%') {
                result.append(center).append(revers);
            } else {
                result.append(revers);
            }
            System.out.println(result);
        }
    }
}
