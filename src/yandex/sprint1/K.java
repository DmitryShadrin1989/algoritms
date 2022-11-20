package yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class K {
    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = Integer.parseInt(reader.readLine());
            int b = Integer.parseInt(reader.readLine().replace(" ", ""));
            int c = Integer.parseInt(reader.readLine());
            int d = b+c;
            char[] chars = String.valueOf(d).toCharArray();

            StringBuilder builder = new StringBuilder();
            for (char ch: chars) {
                if (builder.length()==0) {
                    builder.append(ch);
                    continue;
                }
                builder.append(" ").append(ch);
            }

            System.out.println(builder);
        }
    }
}
