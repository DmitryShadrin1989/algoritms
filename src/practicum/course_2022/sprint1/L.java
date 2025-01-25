package practicum.course_2022.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class L {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s1 = reader.readLine();
            String s2 = reader.readLine();

            char[] charsS1 = s1.toCharArray();
            char[] charsS2 = s2.toCharArray();
            Arrays.sort(charsS1);
            Arrays.sort(charsS2);

            for (int i = 0; i<charsS1.length; i++) {
                if (charsS1[i] != charsS2[i]){
                    System.out.println(charsS2[i]);
                    return;
                }
            }
            System.out.println(charsS2[charsS2.length-1]);
        }
    }
}
