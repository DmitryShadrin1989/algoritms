package yandex.practicum.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = reader.readLine().split(" ");
            int n = Integer.parseInt(strings[0]);
            int m = Integer.parseInt(strings[1]);

            int[][] ints = new int[n][n];
            for (int i = 0; i < m; i++) {
                strings = reader.readLine().split(" ");
                int x = Integer.parseInt(strings[0])-1;
                int y = Integer.parseInt(strings[1])-1;
                ints[x][y] = 1;
            }

            for (int i = 0; i < n; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(ints[i][0]);
                for (int j = 1; j < n; j++) {
                    stringBuilder.append(" ").append(ints[i][j]);
                }
                System.out.println(stringBuilder);
            }
        }
    }
}
