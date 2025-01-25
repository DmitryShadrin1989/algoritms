package practicum.course_2022.sprint8;
/*
G. Поиск со сдвигом

Гоша измерял температуру воздуха n дней подряд. В результате у него получился некоторый временной ряд.
Теперь он хочет посмотреть, как часто встречается некоторый шаблон в получившейся последовательности.
Однако температура — вещь относительная, поэтому Гоша решил,
что при поиске шаблона длины m (a1, a2, ..., am) стоит также рассматривать сдвинутые на константу вхождения.
Это значит, что если для некоторого числа c в исходной последовательности нашёлся участок вида (a1 + c, a2 + c, ... , am + c),
то он тоже считается вхождением шаблона (a1, a2, ..., am).

По заданной последовательности измерений X и шаблону A=(a1, a2, ..., am) определите все вхождения A в X,
допускающие сдвиг на константу.

Подсказка: если вы пишете на питоне и сталкиваетесь с TL,
то попробуйте заменить какие-то из циклов операциями со срезами.

Формат ввода
В первой строке дано количество сделанных измерений n — натуральное число, не превышающее 104.
Во второй строке через пробел записаны n целых чисел xi, 0 ≤ xi ≤ 103 –— результаты измерений.
В третьей строке дано натуральное число m –— длина искомого шаблона, 1≤ m ≤ n.
В четвёртой строке даны m целых чисел ai — элементы шаблона, 0 ≤ ai ≤ 103.

Формат вывода
Выведите через пробел в порядке возрастания все позиции, на которых начинаются вхождения шаблона A в последовательность X.
Нумерация позиций начинается с единицы.

Пример 1
Ввод
9
3 9 1 2 5 10 9 1 7
2
4 10

Вывод
1 8

Пример 2
Ввод
5
1 2 3 4 5
3
10 11 12

Вывод
1 2 3

 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class G {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            String[] dataStrings = reader.readLine().split(" ");
            ArrayList<Integer> data = new ArrayList<>();
            for (String s : dataStrings) {
                data.add(Integer.parseInt(s));
            }

            int m = Integer.parseInt(reader.readLine());
            String[] templateStrings = reader.readLine().split(" ");
            ArrayList<Integer> template = new ArrayList<>();
            for (String s : templateStrings) {
                template.add(Integer.parseInt(s));
            }

            ArrayList<Integer> result = new ArrayList<>();
            int size = data.size() - template.size();
            for (int i = 0; i <= size; i++) {
                if (search(data, i, template)) {
                    result.add(i + 1);
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (Integer i : result) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(" ");
                }
                stringBuilder.append(i);
            }
            System.out.println(stringBuilder);
        }
    }

    private static boolean search(ArrayList<Integer> data, int start, ArrayList<Integer> template) {
        int shift = template.get(0) - data.get(start);
        for (int j = 0; j < template.size(); j++) {
            if (data.get(start + j) != template.get(j) - shift) {
                return false;
            }
        }
        return true;
    }
}
