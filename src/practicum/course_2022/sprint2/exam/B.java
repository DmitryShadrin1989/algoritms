package practicum.course_2022.sprint2.exam;

/*
B. Калькулятор
 */
/*
-- ПРИНЦИП РАБОТЫ --
Для реализации я использовал в качестве стэка  LinkedList.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Согласно условию на вход могут подаваться операции: +, -, *, / и целые числа разделенные пробелами.
Для всех операций есть условия и если ни одна не подошла то добавляется новое значение в стэк.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Сложность агоритма калькулятора польской нотации находится в прямой зависмости от длинны выражения поэтому O(n)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пямять потребляется в прямой зависимости от длинны выражения поэтому O(n)
Иван Самсонов - Хотелось бы увидеть оценку сколько мы увидим в пике элементов в стеке. (спойлер: не два)
Шадрин Дмитрий - Если возьмом такой краевой пример:
1 3 2 8 17 -1 35 * + - / + то в пике в стэке будет 7 элементов, т.е. получается О(n/2-1)

 Успешная посылка - 78914998
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] polandNotation = reader.readLine().split(" ");
            Stack<Integer> integerStack = new Stack<>();
            for (int i=0; i<polandNotation.length; i++) {
                String s = polandNotation[i];
                if (s.equals("+")) {
                    int a = integerStack.pop();
                    int b = integerStack.pop();
                    integerStack.push(b+a);
                } else if (s.equals("-")) {
                    int a = integerStack.pop();
                    int b = integerStack.pop();
                    integerStack.push(b-a);
                } else if (s.equals("*")) {
                    int a = integerStack.pop();
                    int b = integerStack.pop();
                    integerStack.push(b*a);
                } else if (s.equals("/")) {
                    int a = integerStack.pop();
                    int b = integerStack.pop();
                    // Иван Самсонов - твой алгоритм (кстати откуда он?) как раз реализован во floorDiv
                    // Шадрин Дмитрий - Этот алгоритм я выстрадал при решении задачи) Буду лучше изучать основные бибилиотеки, чтобы не избретить велосипеды
                    integerStack.push(Math.floorDiv(b, a));

                } else {
                    integerStack.push(Integer.parseInt(s));
                }
            }
            System.out.println(integerStack.pop());
        }
    }
}
