package practicum.course_2022.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int countDay = Integer.parseInt(reader.readLine());
            String days = reader.readLine();

            if (countDay == 1) {
                System.out.println(1);
                return;
            }

            StringTokenizer tokenizer = new StringTokenizer(days);
            int prev = -300;
            int current = Integer.parseInt(tokenizer.nextToken());
            int next = Integer.parseInt(tokenizer.nextToken());
            int chaoticDays = 0;

            for (int i = 1; i <= countDay; i++) {
                if (current > prev && current > next) {
                     chaoticDays += 1;
                }
                if (i==countDay) break;
                prev = current;
                current = next;
                if (i == countDay - 1) {
                    next = -300;
                } else {
                    next = Integer.parseInt(tokenizer.nextToken());
                }

            }
            System.out.println(chaoticDays);
        }
    }
}
