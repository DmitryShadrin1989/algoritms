package yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// Факторизация
public class J {
    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = Integer.parseInt(reader.readLine());
            ArrayList<Integer> result = new ArrayList<>();
            while (a>1) {
                int divider;
                if(a%2==0) {
                    divider = 2;
                }
                else {
                    divider = 3;
                    while(a%divider!=0){
                        divider+=2;
                    }
                }
                result.add(divider);
                a = a/divider;
            }
            Collections.sort(result);
            System.out.print(result.remove(0));
            result.forEach(e-> System.out.print(" "+e));
        }
    }
}
