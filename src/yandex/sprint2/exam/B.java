package yandex.sprint2.exam;

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
Сложность механизма находится в прямой зависмости от длинны выражения поэтому O(n)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пямять потребляется в прямой зависимости от длинны выражения поэтому O(n)

 Успешная посылка - 78827433
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] polandNotation = reader.readLine().split(" ");

            LinkedList<Integer> ints = new LinkedList<>();

            for (int i=0; i<polandNotation.length; i++) {
                String s = polandNotation[i];
                if (s.equals("+")) {
                    int a = ints.pollFirst();
                    int b = ints.pollFirst();
                    ints.addFirst(b+a);
                } else if (s.equals("-")) {
                    int a = ints.pollFirst();
                    int b = ints.pollFirst();
                    ints.addFirst(b-a);
                } else if (s.equals("*")) {
                    int a = ints.pollFirst();
                    int b = ints.pollFirst();
                    ints.addFirst(b*a);
                } else if (s.equals("/")) {
                    int a = ints.pollFirst();
                    int b = ints.pollFirst();
                    if(b<0) {
                        int divisionResult;
                        if(Math.abs(b)<a) {
                            divisionResult = -1;
                        } else {
                            divisionResult = b/a - (b%a==0?0:1);
                        }
                        ints.addFirst(divisionResult);
                    } else {
                        ints.addFirst(b/a);
                    }
                } else {
                    ints.addFirst(Integer.parseInt(s));
                }
            }
            System.out.println(ints.pollFirst());
        }
    }
}
