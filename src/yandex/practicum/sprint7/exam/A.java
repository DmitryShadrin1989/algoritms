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

/*
-- ПРИНЦИП РАБОТЫ --
Для вычисления расстояния Левенштейна взят алгоритм Вагнера - Фишера.
В основе лежит заполнение матрицы DP значениями редакционного расстояния рассматриваемых подстрок по рекуррентной формуле.
Размер матрицы N на M, где N и M длинны строк.
Для заполнения обходим матрицу слева на право, сверху вниз используя прием "уголка", т.е. смотрим какое значение сверху,
слева и по диагонали (сверху-слева). Есть две ветки в зависимости от того равны ли текущие символы в строках или нет.
Если не равны, то значение для значения редакционного расстояния берется значение по диагонали dp[i-1][j-1] и увеличивается на 1.
Т.е. к уже накопленной разнице по рассмотренной подстроке добавляем + 1.
Если равны, то берется минимальное значение "уголка" -  min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]).
Ответ будет находится в правом нижнем углу матрицы dp[N][M].

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Для того чтобы не выходить за пределы двумерного массива добавлена и проинициализирована "каемка" слева и сверху.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность для инициализации каемки будет O(N) + 0(M), где N и M длинны строк.
Временна сложность для заполнения двумерного массива dp[][] будет O(N*M), т.к. мы должны заполнить каждую ячейку массива.
Общая временная сложность будет O(N+M+N*M) ~ O(N*M)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность будет O((N+1)*(M+1)) ~ O(N*M)

Успешная посылка - https://contest.yandex.ru/contest/25597/run-report/84259128/
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
            for(int i = 0; i <= Math.max(s1.length(), s2.length()); i++) {
                if (i <= s1.length()) {
                    dp[i][0] = i;
                }
                if (i <= s2.length()) {
                    dp[0][i] = i;
                }
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