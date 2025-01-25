package practicum.course_2022.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class H {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            byte[] s1 = reader.readLine().getBytes();
            byte[] s2 = reader.readLine().getBytes();

            if (s1.length != s2.length) {
                System.out.println("NO");
                return;
            }
            HashMap<Byte, Byte> map1 = new HashMap<>();
            HashMap<Byte, Byte> map2 = new HashMap<>();
            for (int i=0; i<s1.length; i++) {
                byte ch1 = s1[i];
                byte ch2 = s2[i];
                if (map1.containsKey(ch1)) {
                    if (!map1.get(ch1).equals(ch2)) {
                        System.out.println("NO");
                        return;
                    }
                } else {
                    map1.put(ch1, ch2);
                }
                if (map2.containsKey(ch2)) {
                    if (!map2.get(ch2).equals(ch1)) {
                        System.out.println("NO");
                        return;
                    }
                } else {
                    map2.put(ch2, ch1);
                }
            }
            System.out.println("YES");
        }
    }
}
