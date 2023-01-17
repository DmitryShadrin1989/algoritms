package yandex.practicum.sprint3;

/*
I. Любители конференций

На IT-конференции присутствовали студенты из разных вузов со всей страны. Для каждого студента известен ID университета, в котором он учится.
Тимофей предложил Рите выяснить, из каких k вузов на конференцию пришло больше всего учащихся.

Формат ввода
В первой строке дано количество студентов в списке —– n (1 ≤ n ≤ 15 000).

Во второй строке через пробел записаны n целых чисел —– ID вуза каждого студента. Каждое из чисел находится в диапазоне от 0 до 10 000.

В третьей строке записано одно число k.

Формат вывода
Выведите через пробел k ID вузов с максимальным числом участников.
Они должны быть отсортированы по убыванию популярности (по количеству гостей от конкретного вуза).
Если более одного вуза имеет одно и то же количество учащихся, то выводить их ID нужно в порядке возрастания.

Пример 1
Ввод
7
1 2 3 1 2 3 4
3

Вывод
1 2 3

Пример 2
Ввод
6
1 1 1 2 2 3
1

Вывод
1

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class I {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int countStudent = Integer.parseInt(reader.readLine());
            String[] students = reader.readLine().split(" ");
            int k = Integer.parseInt(reader.readLine());

            HashMap<Integer, Integer> map = new HashMap<>();
            for (String s: students) {
                Integer key = Integer.parseInt(s);
                if (map.containsKey(key)) {
                    Integer value = map.get(key);
                    map.put(key, ++value);
                } else {
                    map.put(key, 1);
                }
            }

            List<Integer> arrayList = map.entrySet().stream()
                    .sorted((x, y) -> {
                        int result = y.getValue().compareTo(x.getValue());
                        if (result == 0) {
                            result = x.getKey().compareTo(y.getKey());
                        }
                        return result;

                    })
                    .map(e -> e.getKey())
                    .collect(Collectors.toList());

            StringBuilder builder = new StringBuilder();
            builder.append(arrayList.get(0));
            for (int i = 1; i < k; i++) {
                builder.append(" ").append(arrayList.get(i));
            }
            System.out.println(builder);
        }
    }
}
