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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println(unpackString2(reader.readLine()));


//            int n = Integer.parseInt(reader.readLine());
//            String[] strings = new String[n];
//            for (int i = 0; i < n; i++) {
//                strings[i] = reader.readLine();
//            }
//
//            int totalMaxPrefIdx = -1;
//            for (int i = 0; i < n; i++) {
//                strings[i] = unpackString2(strings[i]);
//
//                if (i == 0) {
//                    continue;
//                }
//                String currentString = strings[i];
//                String prevString = strings[i-1];
//                int j = 0;
//                int currentMaxPrefIdx = 0;
//                while (j < currentString.length() && j < prevString.length()) {
//                    if (currentString.charAt(j) != prevString.charAt(j)) {
//                        break;
//                    }
//                    currentMaxPrefIdx++;
//                    j++;
//                }
//
//                if (totalMaxPrefIdx == -1) {
//                    totalMaxPrefIdx = currentMaxPrefIdx;
//                }
//                totalMaxPrefIdx = Math.min(totalMaxPrefIdx, currentMaxPrefIdx);
//            }
//
//            System.out.println(strings[0].substring(0, totalMaxPrefIdx));
        }
    }

    static String unpackString(String string) {
        byte[] bytes = string.getBytes();
        Stack<Byte> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            byte currentByte = bytes[i];

            if ((char) currentByte == ']') {
                StringBuilder currentString = new StringBuilder();
                while (!stack.isEmpty()) {
                    int countOpenBracket = 0;
                    byte pop = stack.pop();
                    if ((char) pop == '[') {
                        countOpenBracket = countOpenBracket + 1;
                        if (countOpenBracket>1) {
                            break;
                        }
                        continue;
                    }
                    if (pop > 47 && pop < 58) {
                        int currentNumber = Integer.parseInt(String.valueOf((char) pop));
                        String s = currentString.toString();
                        for (int j = 0; j < currentNumber-1; j++) {
                            currentString.append(s);
                        }
                    } else {
                        currentString.append((char) pop);
                    }
                }
                result.append(currentString.reverse());
            } else {
                stack.push(currentByte);
            }
        }
        if (!stack.isEmpty()) {
            for (int i = 0; i < stack.size(); i++) {
                byte b = stack.get(i);
                result.append((char) b);
            }
        }
        return result.toString();
    }

    static String unpackString2(String string) {
        byte[] bytes = string.getBytes();
        Stack<Byte> stack = new Stack<>();
        StringBuilder decodeString = new StringBuilder();
        //3[a]2[r2[t]]
        for (int i = 0; i < bytes.length; i++) {
            byte currentByte = bytes[i];

            if ((char) currentByte == ']') {
                StringBuilder intermediateResult = new StringBuilder();

                while (stack.peek() != '[') {
                    byte pop = stack.pop();
                    intermediateResult.append((char) pop);
                }
                stack.pop();
                byte factor = stack.pop();
                int factorInt = Integer.parseInt(String.valueOf((char) factor));
                String s = intermediateResult.reverse().toString();

                if (s.length() == 0) {
                    s = decodeString.toString();
                }

                for (int j = 0; j < factorInt-1; j++) {
                    intermediateResult.append(s);
                }


                decodeString.append(intermediateResult);

            } else {
                stack.push(currentByte);
            }
        }
        return decodeString.toString();
    }
}
