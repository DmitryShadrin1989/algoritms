package practicum.course_2022.sprint4;
/*
D. Кружки
В компании, где работает Тимофей, заботятся о досуге сотрудников и устраивают различные кружки по интересам. Когда кто-то записывается на занятие, в лог вносится название кружка.

По записям в логе составьте список всех кружков, в которые ходит хотя бы один человек.

Формат ввода
В первой строке даётся натуральное число n, не превосходящее 10 000 –— количество записей в логе.

В следующих n строках —– названия кружков.

Формат вывода
Выведите уникальные названия кружков по одному на строке, в порядке появления во входных данных.

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;

public class D {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(reader.readLine());
            LinkedHashSet<String> strings = new LinkedHashSet<>();

            for (int i = 0; i<count; i++) {
                strings.add(reader.readLine());
            }
            strings.forEach(System.out::println);
        }
    }
}
