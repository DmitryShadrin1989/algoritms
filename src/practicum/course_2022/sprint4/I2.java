package practicum.course_2022.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class I2 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int number1 = Integer.parseInt(reader.readLine());
            String s1 = reader.readLine();

            int number2 = Integer.parseInt(reader.readLine());
            String s2 = reader.readLine();

            String[] longArray;
            String[] shortArray;
            if(number1>number2) {
                longArray = s1.split(" ");
                shortArray = s2.split(" ");
            } else {
                longArray = s2.split(" ");
                shortArray = s1.split(" ");
            }

            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            for (int i=0; i<longArray.length; i++) {
                Integer key = Integer.parseInt(longArray[i]);
                if (map.containsKey(key)) {
                    ArrayList<Integer> integers = map.get(key);
                    integers.add(i);
                } else {
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(i);
                    map.put(key, integers);
                }
            }

            int maxCount = 0;
            for (int i=0; i < shortArray.length; i++) {
                Integer key = Integer.parseInt(shortArray[i]);
                if(map.containsKey(key)) {
                    ArrayList<Integer> integers = map.get(key);
                    for (int s=0; s<integers.size(); s++) {
                        int localCount = 0;
                        for (int j = i, k = integers.get(s); j < shortArray.length && k < longArray.length; j++, k++) {
                            if (shortArray[j].equals(longArray[k])) {
                                ++localCount;
                            } else {
                                break;
                            }
                            if (maxCount > shortArray.length-j) {
                                System.out.println(maxCount);
                                return;
                            }
                        }
                        maxCount = Math.max(localCount, maxCount);
                    }
                }
            }
            System.out.println(maxCount);
        }
    }
}
