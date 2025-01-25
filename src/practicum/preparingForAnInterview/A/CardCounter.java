package preparingForAnInterview.A;
/*
На стол в ряд выложены карточки, на каждой карточке написано натуральное число.
За один ход разрешается взять карточку либо с левого, либо с правого конца ряда. Всего можно сделать k ходов.
Итоговый счет равен сумме чисел на выбранных карточках. Определите, какой максимальный счет можно получить по итогам игры.
Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса

Формат ввода
В первой строке записано число карточек
n(1≤n≤105).
Во второй строке записано число ходов
k(1≤k≤n).
В третьей строке через пробел даны числа, записанные на карточках.
i-е по счету число записано на i-й слева карточке. Все числа натуральные и не превосходят 104.

Формат вывода
Выведите единственное число —- максимальную сумму очков, которую можно набрать, сделав k ходов.
Пример 1
Ввод
7
3
5 8 2 1 3 4 11

Вывод
24

Пример 2
Ввод
5
5
1 2 3 4 5

Вывод
15

Пример 2
Ввод
7
4
1 1 9 2 2 2 6

Вывод
17

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardCounter {
    private static long getCardCount(int n, int k, List<Long> cards) {
        // your code goes here
        return 0;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            int k = readInt(reader);
            List<Long> cards = readList(reader);

            System.out.println(getCardCount(n, k, cards));
        }
    }

    private static List<Long> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" "))
                .stream()
                .map(token -> Long.parseLong(token))
                .collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws NumberFormatException, IOException {
        return Integer.parseInt(reader.readLine());
    }
}
