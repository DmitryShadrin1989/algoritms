package yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Степень четырех
public class I {
    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = Integer.parseInt(reader.readLine());
            if (a==1) {
                System.out.println("True");
                return;
            }
            while (a>=4) {
                if(a==4) {
                    System.out.println("True");
                    return;
                } else if (a%4 != 0) {
                    System.out.println("False");
                    return;
                }
                a=a/4;
            }
            System.out.println("False");
        }
    }
}
