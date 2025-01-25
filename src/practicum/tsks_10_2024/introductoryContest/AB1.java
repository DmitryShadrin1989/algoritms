package practicum.tsks_10_2024.introductoryContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AB1 {

    public static void main(String[] args) throws IOException {
        StringBuilder output_buffer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] str = reader.readLine().split(" ");
        int a = Integer.parseInt(str[0]);
        int b = Integer.parseInt(str[1]);
        System.out.println(a + b);
    }
}
