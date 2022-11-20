package yandex.sprint1.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = Integer.parseInt(reader.readLine());
            String[]  strings = reader.readLine().split(" ");
            int indexZero = -1;
            int distance = 0;
            for (int i=0; i< strings.length; i++) {
                if(strings[i].equals("0")) {
                    int wayBack = 1;
                    int depthUpTo = indexZero == -1? 0: i-distance/2;
                    indexZero = i;
                    for(int j=indexZero-1; j>=depthUpTo; j--) {
                        strings[j] = String.valueOf(wayBack);
                        wayBack++;
                    }
                    distance = 0;
                } else if (indexZero != -1){
                    distance++;
                    strings[i] = String.valueOf(distance);
                }
            }
            StringBuilder builder = new StringBuilder(strings[0]);

            for (int i=1; i< strings.length; i++) {
                builder.append(" ").append(strings[i]);
            }
            System.out.println(builder);
        }
    }
}
