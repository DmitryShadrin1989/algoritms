package yandex.sprint1.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int k = Integer.parseInt(reader.readLine());
            Map<Integer, Integer> map = new HashMap<>();
            char[] chars;
            for (int i=0; i<4; i++) {
                chars = reader.readLine().toCharArray();
                for (int j = 0; j < 4; j++) {
                    char ch = chars[j];
                    if (ch != '.') {
                        int value = Integer.parseInt(String.valueOf(ch));
                        int count = map.get(value) != null ? map.get(value) : 0;
                        map.put(value, ++count);
                    }
                }
            }

            int result = 0;
            for (int t=0; t<=9; t++) {
                if (map.containsKey(t) && k*2>=map.get(t)) {
                     result++;
                }
            }
            System.out.println(result);
        }
    }
}
