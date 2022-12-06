package yandex.sprint2;

/*
F. Стек - Max
Нужно реализовать класс StackMax, который поддерживает операцию определения максимума среди всех элементов в стеке. Класс должен поддерживать операции push(x), где x – целое число, pop() и get_max().

Формат ввода
В первой строке записано одно число n — количество команд, которое не превосходит 10000. В следующих n строках идут команды. Команды могут быть следующих видов:

push(x) — добавить число x в стек;
pop() — удалить число с вершины стека;
get_max() — напечатать максимальное число в стеке;
Если стек пуст, при вызове команды get_max() нужно напечатать «None», для команды pop() — «error».

Формат вывода
Для каждой команды get_max() напечатайте результат её выполнения. Если стек пустой, для команды get_max() напечатайте «None». Если происходит удаление из пустого стека — напечатайте «error».
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class F2 {
    public static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = Integer.parseInt(reader.readLine());

            String[] setCommands = new String[a];
            for (int i=0; i<a; i++) {
                setCommands[i] = reader.readLine();
            }

            StackMax stackMax = new StackMax();

            for (String s: setCommands) {
                String[] commands = s.split(" ");
                if (commands[0].equals("get_max")) {
                    result.append(stackMax.get_max() == null ? "None" : stackMax.get_max()).append("\n");
                } else if (commands[0].equals("pop")) {
                    stackMax.pop();
                } else if (commands[0].equals("push")) {
                    stackMax.push(Integer.parseInt(commands[1]));
                }
            }
            System.out.println(result);
        }
    }

    static class StackMax {
        Node headStack;
        Node maxValue;

        public StackMax() {
        }

        public Integer get_max() {
            return isEmpty()? null: maxValue.value;
        }

        public void pop() {
            if (isEmpty()) {
                result.append("error\n");
            } else {
                if (headStack.value == maxValue.value) {
                    maxValue = maxValue.next;
                } else {
                    Node connection = headStack.connection;
                    connection.prev.next = connection.next;
                    if (connection.next != null) connection.next.prev = connection.prev;
                }
                headStack = headStack.next;
            }
        }

        public void push(int i) {
            Integer currentMax = get_max();
            Node currentNode = maxValue;
            if (currentMax == null) {
                maxValue = new Node(i, null, null, null);
            } else if (i>=currentMax) {
                maxValue = new Node(i, maxValue, null, null);
            } else {
                while(currentNode.next != null && currentNode.next.value>i) {
                    currentNode = currentNode.next;
                }
                currentNode.next = new Node(i, currentNode.next, currentNode, null);
            }
            headStack = new Node(i, headStack, null, currentNode == null? maxValue: currentNode.next);
        }

        public boolean isEmpty() {
            return maxValue == null;
        }

        class Node {
            int value;
            Node prev;
            Node next;
            Node connection;

            public Node(int value, Node next, Node prev, Node connection) {
                this.value = value;
                this.prev = prev;
                this.next = next;
                this.connection = connection;
            }
        }
    }
}
