package yandex.practicum.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class E {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String string = reader.readLine();
            HashMap<Character, Integer> map = new HashMap<>();
            int maxCount = 0;
            int count = 0;
            int i = 0;
            while (i<string.length()){
                char ch = string.charAt(i);
                if (map.containsKey(ch)) {
                    count = 0;
                    i = map.get(ch)+1;
                    map = new HashMap<>();
                } else {
                    map.put(ch, i);
                    count++;
                    maxCount = Math.max(maxCount, count);
                    i++;
                }
            }
            System.out.println(maxCount);
        }
    }
}
