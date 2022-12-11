package yandex.practicum.sprint2;
/*
Астрологи объявили день очередей ограниченного размера. Тимофею нужно написать класс MyQueueSized, который принимает параметр max_size, означающий максимально допустимое количество элементов в очереди.

Помогите ему —– реализуйте программу, которая будет эмулировать работу такой очереди. Функции, которые надо поддержать, описаны в формате ввода.

Формат ввода
В первой строке записано одно число — количество команд, оно не превосходит 5000.
Во второй строке задан максимально допустимый размер очереди, он не превосходит 5000.
Далее идут команды по одной на строке. Команды могут быть следующих видов:

push(x) — добавить число x в очередь;
pop() — удалить число из очереди и вывести на печать;
peek() — напечатать первое число в очереди;
size() — вернуть размер очереди;
При превышении допустимого размера очереди нужно вывести «error». При вызове операций pop() или peek() для пустой очереди нужно вывести «None».
Формат вывода
Напечатайте результаты выполнения нужных команд, по одному на строке.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class I {
    public static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int countComm = Integer.parseInt(reader.readLine());
            int maxSize = Integer.parseInt(reader.readLine());

            String[] setCommands = new String[countComm];
            for (int i=0; i<countComm; i++) {
                setCommands[i] = reader.readLine();
            }

            Q q = new Q(maxSize);

            for (String s: setCommands) {
                String[] commands = s.split(" ");
                if (commands[0].equals("peek")) {
                    q.peek();
                } else if (commands[0].equals("pop")) {
                    q.pop();
                } else if (commands[0].equals("push")) {
                    q.push(Integer.parseInt(commands[1]));
                } else if (commands[0].equals("size")) {
                    result.append(q.size()).append("\n");
                }
            }
            System.out.println(result);
        }
    }

    static class Q {
        int maxSize;
        int size;
        int[] array;
        int head;
        int tail;

        public Q(int maxSize) {
            this.maxSize = maxSize;
            this.array = new int[maxSize];
            this.size = 0;
            this.head = 0;
            this.tail = 0;
        }

        public void push(int i) {
            if (maxSize == size) {
                //System.out.println("error");
                result.append("error").append("\n");
                return;
            }
            array[head] = i;
            if (head==array.length-1) {
                head =0;
            } else {
                head++;
            }
            size++;
        }

        public void pop() {
            if (size==0) {
                //System.out.println("None");
                result.append("None").append("\n");
                return;
            }
            //System.out.println(array[tail]);
            result.append(array[tail]).append("\n");
            if (tail==array.length-1) {
                tail =0;
            } else {
                tail++;
            }
            size--;
        }

        public void peek() {
            if (size==0) {
                //System.out.println("None");
                result.append("None").append("\n");
                return;
            }
            //System.out.println(array[tail]);
            result.append(array[tail]).append("\n");
        }

        public int size() {
            return size;
        }
    }
}
