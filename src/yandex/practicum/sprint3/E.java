package yandex.practicum.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class E {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = reader.readLine().split(" ");
            int countHome = Integer.parseInt(strings[0]);
            int many = Integer.parseInt(strings[1]);
            int[] homes = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(homes);

            int i = 0;
            int result = 0;
            while (i<countHome) {
                many = many - homes[i];
                if (many<0) {
                    break;
                }
                result++;
                i++;
            }
            System.out.println(result);
        }
    }
}
