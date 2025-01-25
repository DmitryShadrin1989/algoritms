package practicum.tsks_10_2024.exam22102024;
/*
Количество наборов
На вход подается список из целых чисел. Нужно найти в этом списке наборы из четырех чисел, сумма которых равна целевому
значению и вывести количество таких уникальных наборов.
Набор представляет собой 4 числа (необязательно различных), которые расположены на разных позициях в заданном списке.
Два набора считается одинаковыми, если без учета порядка совпадают значения чисел, входящих в этот набор, например,
следующие наборы являются одинаковыми: [1,1,2,2] и [2,1,2,1], а следующие наборы разными: [1,2,2,3] и [1,1,2,3].

Формат ввода
В первой строке
n
n (
4
≤
n
≤
300
4≤n≤300) - количество чисел в исходном наборе и
t
t (
−
1
0
9
≤
t
≤
1
0
9
−10
9
 ≤t≤10
9
 ) - целевое значение

Во второй строке через пробел указаны числа в исходном наборе (
−
1
0
9
≤
a
i
≤
1
0
9
−10
9
 ≤a
i
​
 ≤10
9
 )

Формат вывода
Количество наборов с заданной суммой

Пример 1:
Ввод:
6 0
1 0 -1 0 -2 2

Вывод:
3

Пример 2
Ввод:
5 8
2 2 2 2 2

Вывод:
1

Пример 3
Ввод:
8 -11
1 -2 -5 -4 -3 3 3 5

Вывод:
1
 */


import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfSets {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] arguments = reader.readLine().split(" ");
        int n = Integer.parseInt(arguments[0]);
        int t = Integer.parseInt(arguments[1]);

        String[] valuesStr = reader.readLine().split(" ");

        int[] values = new int[n];
        for (int i = 0; i <n; i++) {
            values[i] = Integer.parseInt(valuesStr[i]);
        }

        Arrays.sort(values);

        int count = getNumberOfUniqueSets(n, values, t);
        writer.write(String.valueOf(count));

        reader.close();
        writer.close();
    }

    private static int getNumberOfUniqueSets(int n, int[] values, int t) {
        Set<List<Integer>> uniqueSets = new HashSet<>();
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                int leftIndex = j + 1;
                int rightIndex = n - 1;

                while (leftIndex < rightIndex) {
                    int sum = values[i] + values[j] + values[leftIndex] + values[rightIndex];
                    if (sum == t) {
                        List<Integer> setOfElements = List.of(values[i], values[j], values[leftIndex], values[rightIndex]);
                        uniqueSets.add(setOfElements);

                        leftIndex++;
                        rightIndex--;
                    } else if (sum < t) {
                        leftIndex++;
                    } else {
                        rightIndex--;
                    }
                }
            }
        }
        return uniqueSets.size();
    }
}
