package practicum.course_2022.sprint7;

/*
E. Алла на Алгосах

Алла хочет купить дом на Алгосах. Для этого ей надо много наличных, которые она собирается получить в банкомате.
Банкомат приличный, поэтому в нём есть бесконечно много банкнот каждого номинала. Всего номиналов k штук.
Дом мечты Аллы стоит x франков.

Найдите минимальное количество банкнот, которые в сумме дадут x франков.
Если в набор входит несколько банкнот одинакового номинала, то учитывать надо их все.

Например, если необходимо набрать 15 франков, а в банкомате купюры по 5 франков, то минимальное число купюр —- 3.

Формат ввода
В первой строке дана сумма, которую хочет получить Алла –— натуральное число x (1 ≤ x ≤ 104).
Во второй строке дано число различных номиналов k. В третьей строке даны k чисел (1 ≤ k ≤ 1000) —– номиналы купюр.
Все номиналы лежат в диапазоне от 1 до 104. Номиналы купюр могут повторяться.

Формат вывода
Выведите единственное число —– минимальное количество купюр, которыми можно набрать x франков.
Если нельзя набрать в точности x франков, то выведите -1.

Пример 1
Ввод
130
4
10 3 40 1

Вывод
4

Пример 2
Ввод
100
2
7 5

Вывод
16

Пример 3
Ввод
1
1
1

Вывод
1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int amount = Integer.parseInt(reader.readLine());
            int n = Integer.parseInt(reader.readLine());
            String[] strings = reader.readLine().split(" ");
            int nominals[] = new int[n];
            for (int i = 0; i < n; i++) {
                nominals[i] = Integer.parseInt(strings[i]);
            }

            int dp[] = new int[amount +1];
            for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {
                dp[currentAmount] = 9999999;
                for (int nominal : nominals) {
                    if (nominal <= currentAmount) {
                        dp[currentAmount] = Math.min(dp[currentAmount], dp[currentAmount - nominal] + 1);
                    }
                }
            }
            if(dp[amount] == 0 || dp[amount] == 9999999) {
                System.out.println(-1);
            } else {
                System.out.println(dp[amount]);
            }
        }
    }
}
