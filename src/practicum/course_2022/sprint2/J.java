package practicum.course_2022.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class J {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int countComm = Integer.parseInt(reader.readLine());

            Q q = new Q();

            for (int i=0; i<countComm; i++) {
                String[] commands = reader.readLine().split(" ");
                if (commands[0].equals("get")) {
                    q.get();
                } else if (commands[0].equals("put")) {
                    q.put(Integer.parseInt(commands[1]));
                } else if (commands[0].equals("size")) {
                    q.size();
                }
            }
        }
    }

    static class Q {
        int sizeList = 0;
        LinkedList<Integer> linkedList = new LinkedList<>();

        public void get() {
            if (sizeList == 0) {
                System.out.println("error");
                return;
            }
            System.out.println(linkedList.pop());
            sizeList--;
        }

        public void put(int i) {
            linkedList.add(i);
            sizeList++;
        }

        public void size() {
            System.out.println(sizeList);
        }
    }
}
