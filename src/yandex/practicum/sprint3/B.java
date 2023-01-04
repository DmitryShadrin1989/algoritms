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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Map<String, String> map = new HashMap<>();
            map.put("2", "a b c");
            map.put("3", "d e f");
            map.put("4", "g h i");
            map.put("5", "j k l");
            map.put("6", "m n o");
            map.put("7", "p q r s");
            map.put("8", "t u v");
            map.put("9", "w x y z");

            String[] s = reader.readLine().split("");
            ArrayList<String> arrayList = new ArrayList<>();

            for (String value : s) {
                arrayList.add(map.get(value));
            }

            combinations(arrayList);
        }
    }

    private static void combinations(ArrayList<String> arrayList) {
        if (arrayList.size() == 1) {
            arrayList.forEach(System.out::println);
        } else {
            String[] s1 = arrayList.get(0).split(" ");
            String[] s2 = arrayList.get(1).split(" ");
            StringBuilder result = new StringBuilder();
            for (int i=0; i<s1.length; i++) {
                for (int j=0; j<s2.length; j++) {
                    result.append(s1[i]).append(s2[j]).append(" ");
                }
            }
            arrayList.remove(0);
            arrayList.set(0, result.toString());
            combinations(arrayList);
        }
    }
}
