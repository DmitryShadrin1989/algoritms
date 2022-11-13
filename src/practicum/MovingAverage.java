package practicum;

//C. Скользящее среднее
//        Вам дана статистика по числу запросов в секунду к вашему любимому рекомендательному сервису.
//
//        Измерения велись n секунд.
//
//        В секунду i поступает qi запросов.
//
//        Примените метод скользящего среднего с длиной окна k к этим данным и выведите результат.
//
//        Формат ввода
//        В первой строке передаётся натуральное число n, количество секунд, в течение которых велись измерения. 1 ≤ n ≤ 105
//
//        Во второй строке через пробел записаны n целых неотрицательных чисел qi, каждое лежит в диапазоне от 0 до 103.
//
//        В третьей строке записано натуральное число k (1 ≤ k ≤ n) —– окно сглаживания.
//
//        Примечание для Go:
//
//        Заметьте, что в данной задаче достаточно большой размер ввода. Поэтому необходимо задавать размер буфера для сканнера хотя бы 600 Кб.
//
//        Формат вывода
//        Выведите через пробел результат применения метода скользящего среднего к серии измерений. Должно быть выведено n - k + 1 элементов, каждый элемент -— вещественное (дробное) число.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MovingAverage {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine());
            String[] arr = reader.readLine().split(" ");
            int k = Integer.parseInt(reader.readLine());
            int current_sum = 0;


            for (int i = 0; i < k; i++) {
                current_sum += Integer.parseInt(arr[i]);
            }
            System.out.print((double) current_sum / k);

            for (int i = 0; i < n - k; i++) {
                current_sum -= Integer.parseInt(arr[i]);
                current_sum += Integer.parseInt(arr[i + k]);
                System.out.print(" " + (double) current_sum / k);
            }
        }
    }
}
