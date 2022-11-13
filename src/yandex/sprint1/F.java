package yandex.sprint1;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.Locale;

// Палиндром
public class F {
    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine().replaceAll("\\p{Punct}|\\p{Space}", "").toLowerCase(Locale.ROOT);

            int i = 0;
            int j = s.length()-1;

            while (i<j) {
                if(s.charAt(i) != s.charAt(j)) {
                    System.out.println("False");
                    return;
                }
                i++;
                j--;
            }
            System.out.println("True");
        }
    }
}
