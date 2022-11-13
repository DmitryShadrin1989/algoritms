package yandex.duplicates;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RemovingDuplicates {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(r.readLine());
        String lastUniqueElement = "";
        for(int i = 0; i < n; i++) {
            String elem = r.readLine();
            if (!elem.equals(lastUniqueElement)){
                lastUniqueElement = elem;
                System.out.println(lastUniqueElement);
            }
        }
    }
}
