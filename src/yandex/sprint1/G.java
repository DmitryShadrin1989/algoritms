package yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// Перевод в двоичную систему
public class G {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int a = Integer.parseInt(reader.readLine());
            if(a==0) {
                System.out.println(0);
                return;
            }
            ArrayList<Integer> result = new ArrayList<>();
            do {
                result.add(0, a % 2);
                a = a / 2;

            } while (a != 1);
            result.add(0, 1);

            result.forEach(i -> System.out.print(i));
        }
    }
}
