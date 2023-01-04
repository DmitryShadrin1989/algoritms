package yandex.practicum.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int number = Integer.parseInt(reader.readLine());
            String[] strings = reader.readLine().split(" ");

            int max = 0;
            for (int i= 0; i<number && (number-i+1)>max; i++) {
                int score = 0;
                int count = 0;
                for (int j=i; j<number; j++) {
                    ++count;
                    if (strings[j].equals("0")) {
                        --score;
                    } else {
                        ++score;
                    }

                    if (score == 0) {
                        max = Math.max(count, max);
                    }
                }
            }
            System.out.println(max);
        }
    }
}
