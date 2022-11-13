package yandex.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int iSize = Integer.parseInt(reader.readLine());
            int jSize = Integer.parseInt(reader.readLine());

            int[][] matrix = new int[iSize][jSize];

            for(int i=0; i<iSize; i++) {

                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                for(int j=0; j<jSize; j++) {
                    matrix[i][j] = Integer.parseInt(tokenizer.nextToken());
                }

            }

            int iSearch = Integer.parseInt(reader.readLine());
            int jSearch = Integer.parseInt(reader.readLine());
            ArrayList<Integer> result = new ArrayList<>();

            if (jSearch < jSize-1){
               result.add(matrix[iSearch][jSearch+1]);
            }

            if (iSearch < iSize-1){
                result.add(matrix[iSearch+1][jSearch]);
            }

            if (iSearch > 0){
                result.add(matrix[iSearch-1][jSearch]);
            }

            if (jSearch > 0){
                result.add(matrix[iSearch][jSearch-1]);
            }
            Collections.sort(result);
            StringBuilder stringBuilder = new StringBuilder();
            result.forEach(e-> {
                if (stringBuilder.length()>0) stringBuilder.append(" ");
                stringBuilder.append(e);
            });
            System.out.println(stringBuilder);

        }
    }
}
