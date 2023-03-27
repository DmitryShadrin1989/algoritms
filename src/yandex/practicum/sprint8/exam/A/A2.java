package yandex.practicum.sprint8.exam.A;
/*
A. Packed Prefix
Вам даны строки в запакованном виде. Определим запакованную строку (ЗС) рекурсивно.
Строка, состоящая только из строчных букв английского алфавита является ЗС.
Если A и B —– корректные ЗС, то и AB является ЗС. Если A —– ЗС, а n — однозначное натуральное число, то n[A] тоже ЗС.
При этом запись n[A] означает, что при распаковке строка A записывается подряд n раз.
Найдите наибольший общий префикс распакованных строк и выведите его (в распакованном виде).

Иными словами, пусть сложение —– это конкатенация двух строк, а умножение строки на число — повтор строки соответствующее число раз.
Пусть функция f умеет принимать ЗС и распаковывать её. Если ЗС D имеет вид D=AB, где A и B тоже ЗС,
то f(D) = f(A) + f(B). Если D=n[A], то f(D) = f(A) × n.

Формат ввода
В первой строке записано число n (1 ≤ n ≤ 1000) –— число строк.

Далее в n строках записаны запакованные строки. Гарантируется, что эти строки корректны,
то есть удовлетворяют указанному рекурсивному определению. Длина строк после распаковки не превосходит 105.

Формат вывода
Выведите наибольший общий префикс распакованных строк.

Пример 1
Ввод
3
2[a]2[ab]
3[a]2[r2[t]]
a2[aa3[b]]

Вывод
aaa

Пример 2
Ввод
3
abacabaca
2[abac]a
3[aba]

Вывод
aba
 */

/*
-- ПРИНЦИП РАБОТЫ --
Программа использует два метода:
1. Распаковка строки unpackString.
2. Поиск индекса которым заканчивается наибольший префикс для строк findMaxPrefIdx выполняется для пары сравниваемых строк.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Для реализации распаковки использовал один Stack<Character>.
Обходим переданную строку посимвольно и все кладем в стек пока не встретим закрывающую скобку - ']'.
Тогда мы начинаем доставать элементы из стека и класть значения в ArrayList пока не встретим символ - '['.
За ней должен стоять множитель n, по условию задачи это "однозначное натуральное число".
Обходим ArrayList в обратном порядке n раз и кладем обратно в стек символы, т.е. мы раскрыли одну скобку и положили получившееся значение обратно.
Так повторяем пока не пройдем всю строку. Далее начинаем вынимать элементы из стека в обратном порядке и склеиваем все в результат.

Для оптимизации поиска добавил максимальный индекс уже полученного на предыдущих этапах общего префикса totalMaxPrefIdx
(для первого сравнения возьмем минимальную длину сравниваемых строк).
Т.е. нет смысла итерироваться по строкам больше этого значения. Само сравнение выполняется по символьно, если при сравнении получили false,
то завершаем цикл и возвращаем счетчик (сколько прошли символов).

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
По распаковке. Процедура выполняется для каждой строки, проходит по всем элементам исходной (запакованной) строки и
по всем элементам после ее распаковки. Сложность получается O(N * (M + K)), где N - количество строк, M - максимальная длинна запакованной строки,
K - максимальная длинна распакованной строки.

По поиску индекса наибольшего префикса. Процедура сравнивает пары распакованных строк т.е. N-1, где N - количество строк.
Для каждой такой пары сравнение выполняется за O(logK), где K - максимальная длинна распакованной строки.
Т.е. общая сложность поиска индекса O((N-1)logK).

Общая временная сложность алгоритма O(N*(M + K) + (N-1)*logK) ~ O(N*K)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Размер массива String[] = 2, т.к. мы в момент времени храним максимум две строки для сравнения (текущую и предыдущую) и
вычисления максимального общего префикса и потом пере используем его. Пространственная сложность будет O(2*K),
K - максимальная длинная распакованной строки.

Успешная посылка - https://contest.yandex.ru/contest/26133/run-report/84686179/
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class A2 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine());
            if (n == 1) {
                System.out.println(unpackString(reader.readLine()));
                return;
            }

            String[] strings = new String[2];
            int previousString = 0;
            int currentString = 1;
            int totalMaxPrefIdx = -1;
            for (int i = 0; i < n; i++) {
                strings[currentString] = unpackString(reader.readLine());

                if (i == 0) {
                    previousString = 1 - previousString;
                    currentString = 1 - currentString;
                    continue;
                }

                int currentMaxPrefIdx = findMaxPrefIdx(strings, totalMaxPrefIdx);
                if (totalMaxPrefIdx == -1) {
                    totalMaxPrefIdx = currentMaxPrefIdx;
                }

                totalMaxPrefIdx = Math.min(totalMaxPrefIdx, currentMaxPrefIdx);
                previousString = 1 - previousString;
                currentString = 1 - currentString;
            }
            System.out.println(strings[currentString].substring(0, totalMaxPrefIdx));
        }
    }

    private static int findMaxPrefIdx(String[] strings, int totalMaxPrefIdx) {
        int n = totalMaxPrefIdx == -1 ? Math.min(strings[0].length(), strings[1].length()) : totalMaxPrefIdx;
        int len = 0;
        for (int i = 0; i < n; i++) {
            if (strings[0].charAt(i) != strings[1].charAt(i)) {
                break;
            }
            len++;
        }
        return len;
    }

    static String unpackString(String string) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ']') {
                ArrayList<Character> decodedString = new ArrayList<>();

                while (stack.peek() != '[') {
                    decodedString.add(stack.pop());
                }
                stack.pop();
                int factorInt = Integer.parseInt(stack.pop().toString());
                for (int j = 0; j < factorInt; j++) {
                    for (int k = decodedString.size() - 1; k >= 0; k--) {
                        stack.push(decodedString.get(k));
                    }
                }
            } else {
                stack.push(string.charAt(i));
            }
        }

        StringBuilder result = new StringBuilder();
        for (char ch : stack) {
            result.append(ch);
        }
        return result.toString();
    }
}
