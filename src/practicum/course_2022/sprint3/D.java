package practicum.course_2022.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class D {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int childCount = Integer.parseInt(reader.readLine());
            int[] factors = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(factors);

            int cookieCount = Integer.parseInt(reader.readLine());
            int[] cookies = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(cookies);

            int i = 0;
            int j = 0;
            int smile = 0;
            while (i<childCount && j<cookieCount) {
                int f = factors[i];
                int c = cookies[j];
                if (f<=c) {
                    smile++;
                    i++;
                }
                j++;
            }
            System.out.println(smile);
        }
    }
}
