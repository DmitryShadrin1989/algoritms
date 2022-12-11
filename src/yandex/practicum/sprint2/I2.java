package yandex.practicum.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class I2 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int countComm = Integer.parseInt(reader.readLine());
            int maxSize = Integer.parseInt(reader.readLine());

            Q q = new Q(maxSize);

            for (int i=0; i<countComm; i++) {
                String[] commands = reader.readLine().split(" ");
                if (commands[0].equals("peek")) {
                    q.peek();
                } else if (commands[0].equals("pop")) {
                    q.pop();
                } else if (commands[0].equals("push")) {
                    q.push(Integer.parseInt(commands[1]));
                } else if (commands[0].equals("size")) {
                    System.out.println(q.size());
                }
            }
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
                System.out.println("error");
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
                System.out.println("None");
                return;
            }
            System.out.println(array[tail]);
            if (tail==array.length-1) {
                tail =0;
            } else {
                tail++;
            }
            size--;
        }

        public void peek() {
            if (size==0) {
                System.out.println("None");
                return;
            }
            System.out.println(array[tail]);
        }

        public int size() {
            return size;
        }
    }
}
