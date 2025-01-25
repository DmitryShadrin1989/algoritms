package practicum.course_2022.sprint2;
/*
H. Скобочная последовательность
Вот какую задачу Тимофей предложил на собеседовании одному из кандидатов. Если вы с ней ещё не сталкивались, то наверняка столкнётесь –— она довольно популярная.

Дана скобочная последовательность. Нужно определить, правильная ли она.

Будем придерживаться такого определения:

пустая строка —– правильная скобочная последовательность;
правильная скобочная последовательность, взятая в скобки одного типа, –— правильная скобочная последовательность;
правильная скобочная последовательность с приписанной слева или справа правильной скобочной последовательностью —– тоже правильная.
На вход подаётся последовательность из скобок трёх видов: [], (), {}.
Напишите функцию is_correct_bracket_seq, которая принимает на вход скобочную последовательность и возвращает True, если последовательность правильная, а иначе False.

Формат ввода
На вход подаётся одна строка, содержащая скобочную последовательность. Скобки записаны подряд, без пробелов.

Формат вывода
Выведите «True» или «False».
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

public class H {
    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            char[] chars = reader.readLine().toCharArray();
            LinkedList<Character> stack = new LinkedList<>();
            HashMap<Character, Character> map = new HashMap<>();
            map.put(')', '(');
            map.put('}', '{');
            map.put(']', '[');

            for (int i=0; i<chars.length; i++) {
                char ch = chars[i];
                if (ch == '(' || ch == '{' || ch == '[') stack.add(ch);
                else {
                    Character chStack = stack.pollLast();
                    if (chStack == null || !chStack.equals(map.get(ch))) {
                        System.out.println("False");
                        return;
                    }
                }
            }
            if (stack.isEmpty()) {
                System.out.println("True");
            } else System.out.println("False");
        }
    }
}
