package yandex.practicum.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class G1 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int number = Integer.parseInt(reader.readLine());
            String[] strings = reader.readLine().split(" ");
            HashMap<Integer, Integer> mapMin = new HashMap<>();
            HashMap<Integer, Integer> mapMax = new HashMap<>();
            int max = 0;
            int sum = 0;
            for (int i=0; i<number; i++) {
                if (strings[i].equals("0")) {
                    --sum;
                } else {
                    ++sum;
                }
                if (sum == 0) {
                    max = Math.max(max, i+1);
                }
                if (mapMin.containsKey(sum)) {
                    mapMin.put(sum, Math.min(mapMin.get(sum), i));
                } else {
                    mapMin.put(sum, i);
                }
                if (mapMax.containsKey(sum)) {
                    mapMax.put(sum, Math.max(mapMax.get(sum), i));
                } else {
                    mapMax.put(sum, i);
                }
                if (sum>number/2) {
                    System.out.println(0);
                    return;
                }
            }
            for (Map.Entry<Integer, Integer> entry : mapMin.entrySet()) {
                int minValue = entry.getValue();
                int maxValue = mapMax.get(entry.getKey());
                max = Math.max(max, (maxValue - minValue));
            }
            System.out.println(max);
        }
    }
}
