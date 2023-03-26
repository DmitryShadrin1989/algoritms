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
Для реализации использовал Stack<String> (перепробовал массу вариантов и этот оказался самым быстрым).
Обходим переданную строку посимвольно и все кладем в стек пока не встретим закрывающую скобку - ']'.
Тогда мы начинаем доставать элементы из стека и конкатенировать строку пока не встретим открывающую скобку - '['.
За ней должен стоять множитель n, по условию задачи это "однозначное натуральное число".
Повторяем конкатенированную строку n раз и кладем обратно в стек, т.е. мы раскрыли одну скобку и положили получившееся значение обратно.
Так повторяем пока не пройдем всю строку. Далее начинаем вынимать элементы из стека в обратном порядке.

2. Поиск индекса которым заканчивается наибольший префикс для строк findMaxPrefIdx выполняется для пары сравниваемых строк.
Применяется принцип бинарного поиска, т.е. мы делим все время строку пополам, чтобы найти максимальный индекс на котором строки равны.
Для оптимизации добавил, чтобы для правой границы передавался уже вычисленный на предыдущей итерации максимальный индекс totalMaxPrefIdx.
Он подставляется в качестве правой границы.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Честно не успел заполнить этот пункт.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
По распаковке. Процедура выполняется для каждой строки, проходит по всем элементам исходной (запакованной строки) строки и
по всем элементам после ее распаковки. Сложность получается O(N * (M + K)), где N - количество строк, M - длинна запакованной строки,
K - длинная распакованной строки.

По поиску индекса наибольшего префикса. Процедура сравнивает пары распакованных строк т.е. N-1, где N - количество строк.
Для каждой такой пары сравнение выполняется за O(logK), где K - длинная распакованной строки.
Т.е. общая сложность поиска индекса O((N-1)logK).

Общая временная сложность алгоритма O(N*(M + K) + (N-1)*logK) ~ O(N*K)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Т.к. массив String[] пере используется пространственная сложность будет O(N*K), где N - количество строк, K - длинная распакованной строки.

Успешная посылка - https://contest.yandex.ru/contest/26133/run-report/84617614/
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine());
            String[] strings = new String[n];
            for (int i = 0; i < n; i++) {
                strings[i] = reader.readLine();
            }

            if (strings.length == 1) {
                System.out.println(unpackString(strings[0]));
                return;
            }

            int totalMaxPrefIdx = -1;
            for (int i = 0; i < n; i++) {
                strings[i] = unpackString(strings[i]);

                if (i == 0) {
                    continue;
                }
                String currentString = strings[i];
                String prevString = strings[i-1];

                int currentMaxPrefIdx = findMaxPrefIdx(currentString, prevString, totalMaxPrefIdx);

                if (totalMaxPrefIdx == -1) {
                    totalMaxPrefIdx = currentMaxPrefIdx;
                }

                totalMaxPrefIdx = Math.min(totalMaxPrefIdx, currentMaxPrefIdx);
            }
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < totalMaxPrefIdx; i++) {
                result.append(strings[0].charAt(i));
            }
            System.out.println(result);
        }
    }

    private static int findMaxPrefIdx(String currentString, String prevString, int totalMaxPrefIdx) {
        int left = 0;
        int right = totalMaxPrefIdx == -1 ? Math.min(currentString.length(), prevString.length()) : totalMaxPrefIdx;
        int mid;
        while (right - left > 1) {
            mid = (left + right) / 2;

            if (currentString.charAt(mid) == prevString.charAt(mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    static String unpackString(String string) {
        String[] strings = string.split("");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < strings.length; i++) {
            String currenString = strings[i];

            if (currenString.equals("]")) {
                StringBuilder local = new StringBuilder();

                while (!stack.peek().equals("[")) {
                    local.append(stack.pop());
                }
                stack.pop();
                int factorInt = Integer.parseInt(stack.pop());
                String s = local.toString();
                for (int j = 0; j < factorInt-1; j++) {
                    local.append(s);
                }
                stack.push(local.toString());
            } else {
                stack.push(currenString);
            }
        }
        StringBuilder result = new StringBuilder();
        for (String s : stack) {
            for (int j = s.length() - 1; j >= 0; j--) {
                result.append(s.charAt(j));
            }
        }
        return result.toString();
    }

    static String unpackString2(String string) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ']') {
                StringBuilder decodedString = new StringBuilder();

                while (stack.peek() != '[') {
                    decodedString.append(stack.pop());
                }
                stack.pop();
                int factorInt = Integer.parseInt(stack.pop().toString());
                String s = decodedString.toString();
                for (int j = 0; j < factorInt; j++) {
                    for (int k = s.length() - 1; k >= 0; k--) {
                        stack.push(s.charAt(k));
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
