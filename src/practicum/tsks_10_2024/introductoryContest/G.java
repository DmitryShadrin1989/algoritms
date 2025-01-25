package practicum.tsks_10_2024.introductoryContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class G {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String j = reader.readLine();
        String s = reader.readLine();

        Set<Character> set = new HashSet<>();
        int i = 0;
        while (i < j.length()) {
            set.add(j.charAt(i));
            i++;
        }
        i = 0;
        int result = 0;
        while (i < s.length()) {
            if (set.contains(s.charAt(i))) {
                result++;
            }
            i++;
        }
        System.out.println(result);
    }
}
