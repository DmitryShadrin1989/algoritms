package yandex.practicum.sprint7.exam;
/*
A. Расстояние по Левенштейну

Расстоянием Левенштейна между двумя строками s и t называется количество атомарных изменений,
с помощью которых можно одну строку превратить в другую.
Под атомарными изменениями подразумеваются: удаление одного символа, вставка одного символа, замена одного символа на другой.

Найдите расстояние Левенштейна для предложенной пары строк.

Выведите единственное число — расстояние между строками.

Формат ввода
В первой строке дана строка s, во второй — строка t. Длины обеих строк не превосходят 1000.
Строки состоят из маленьких латинских букв.

Пример 1
Ввод
abacaba
abaabc

Вывод
2

Пример 2
Ввод
innokentiy
innnokkentia

Вывод
3

Пример 3
Ввод
r
x

Вывод
1
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s1 = reader.readLine();
            String s2 = reader.readLine();

            int[][] dp = new int[s1.length()+1][s2.length()+1];
            for(int i = 0; i <= s1.length(); i++) {
                dp[i][0] = i;
            }
            for(int i = 0; i <= s2.length(); i++) {
                dp[0][i] = i;
            }
            for (int i = 1; i <= s1.length(); i++ ) {
                for (int j = 1; j <= s2.length(); j++) {
                    int a = dp[i-1][j] + 1;
                    int b = dp[i][j-1] + 1;
                    int c = dp[i-1][j-1];
                    if (s1.charAt(i-1) != s2.charAt(j-1)) {
                        c += 1;
                    }
                    int min = Math.min(a, b);
                    dp[i][j] = Math.min(min, c);
                }
            }
            System.out.println(dp[s1.length()][s2.length()]);
        }
    }
}
