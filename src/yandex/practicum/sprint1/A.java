package yandex.practicum.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {

    public static void main(String[] args) throws IOException {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            int a = Integer.parseInt(tokenizer.nextToken());

            int x = Integer.parseInt(tokenizer.nextToken());

            int b = Integer.parseInt(tokenizer.nextToken());

            int c = Integer.parseInt(tokenizer.nextToken());

            System.out.println(a*x*x + b*x + c);
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.length();
    }
}
