package preparingForAnInterview.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Вам дан массив натуральных чисел ai. Найдите число таких пар элементов (ai,aj), где ∣∣ai−aj∣∣%200==0 и i<j.
Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
Формат ввода
В первой строке дан размер массива n (1≤n≤200000).
Во второй строке через пробел записаны элементы массива ai (1≤ai≤109).

Формат вывода
Выведите единственное число — количество пар, подходящих под указанное выше условие.

Пример 1
Ввод
5
203 404 204 200 403

Вывод
2

Пример 2
Ввод
1
1000000

Вывод
0

Пример 3
Ввод
3
2022 2020 2021

Вывод
0
 */
public class Status200 {
    private static long getNumberOfGoodPairs(int n, List<Integer> numbers) {
        // your code goes here
        return 0;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> numbers = readList(reader);
            System.out.println(getNumberOfGoodPairs(n, numbers));
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" "))
                .stream()
                .map(token -> Integer.parseInt(token))
                .collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws NumberFormatException, IOException {
        return Integer.parseInt(reader.readLine());
    }
}
