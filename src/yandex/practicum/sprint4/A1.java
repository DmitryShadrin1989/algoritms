package yandex.practicum.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A1 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            String s = reader.readLine();

            int result = 0;
            for (int i=0, j=s.length()-1; i<s.length(); i++, j--) {
                int x = (int) Math.pow(a, j) % m;
                int y =  (s.charAt(i) * x) % m  ;
                result = result + y;

            }
            System.out.println(result);
        }
    }
}
