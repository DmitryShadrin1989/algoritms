package practicum.interview.old;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ParenthesesGeneratorUsedQueue {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(""); // Начинаем с пустой строки

        while (!queue.isEmpty()) {
            String current = queue.poll();

            // Если длина строки равна 2*n, то добавляем её в результат
            if (current.length() == 2 * n) {
                result.add(current);
            } else {
                // Если можем добавить открывающую скобку
                if (countChar(current, '(') < n) {
                    queue.offer(current + "(");
                }
                // Если можем добавить закрывающую скобку
                if (countChar(current, ')') < countChar(current, '(')) {
                    queue.offer(current + ")");
                }
            }
        }

        return result;
    }

    // Считаем количество вхождений символа в строке
    private int countChar(String str, char c) {
        int count = 0;
        for (char ch : str.toCharArray()) {
            if (ch == c) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        ParenthesesGeneratorUsedQueue generator = new ParenthesesGeneratorUsedQueue();
        int n = 4; // Например, 3 пары скобок
        List<String> result = generator.generateParenthesis(n);
        System.out.println(result);
    }
}
