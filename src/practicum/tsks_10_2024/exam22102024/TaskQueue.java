package practicum.tsks_10_2024.exam22102024;

/*
Очередь задач

Есть план выполнения задач некоторой известной продолжительности. В этом плане имеется информация о том сколько задач выполняется в каждый момент времени. Моменты времени равнозначны и для простоты описания можем считать что один момент времени - это одна секунда.

Надо найти в этой очереди интервал заданного размера, в который очередь наименее загружена.

Формат ввода
В первой строке вводится натуральное число
N
N (
1
≤
N
≤
1
0
5
1≤N≤10
5
 ) - количество моментов времени, и натуральное число
T
T (
1
≤
T
≤
N
1≤T≤N) - размер искомого временного интервала.

В следующих
N
N строках подаются числа
a
i
a
i
​
  (
1
≤
a
i
≤
1
0
5
1≤a
i
​
 ≤10
5
 ) - число задач в секунду
i
i.

Формат вывода
Выведите номер секунды, в которую начинается искомый интервал - интервал размера
T
T, в который очередь была наименее загружена. Если есть несколько подходящих ответов - выведите наименьший.

Т1
Ввод:
7 3
3
5
2
8
4
3
2

Вывод:
5

Т2
Ввод:
7 7
3
5
2
8
4
3
2

Вывод:
5
 */

import java.io.*;

public class TaskQueue {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] arguments = reader.readLine().split(" ");
        int n = Integer.parseInt(arguments[0]);
        int t = Integer.parseInt(arguments[1]);

        if (n == t || t == 1) {
            writer.newLine();
            writer.write("1");

            reader.close();
            writer.close();
            return;
        }

        int[][] dp = new int[n][2];
        int minimumQueueLoad = 0;
        int startOfPeriod = 1;
        int currentQueueLoad = 0;
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(reader.readLine());
            dp[i][0] = value;
            currentQueueLoad += value;

            int tailIndex = i-(t-1);
            if (tailIndex < 0 ) {
                continue;
            }
            if (tailIndex == 0) {
                minimumQueueLoad = currentQueueLoad;
            } else {
                minimumQueueLoad = Math.min(minimumQueueLoad, currentQueueLoad);
                if (minimumQueueLoad == currentQueueLoad) {
                    startOfPeriod = tailIndex + 1;
                }
            }

            dp[tailIndex][1] = currentQueueLoad;
            currentQueueLoad -= dp[tailIndex][0];
        }

        writer.write(String.valueOf(startOfPeriod));

        reader.close();
        writer.close();
    }
}
