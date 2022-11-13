package yandex.units;

import java.io.*;

public class ConsecutiveUnitsEx1 {
    private static final String FILE_INPUT = "C:\\Users\\Administrator\\Desktop\\Education Files\\directory file\\input.txt";
    private static final String FILE_OUTPUT = "C:\\Users\\Administrator\\Desktop\\Education Files\\directory file\\output.txt";
    private static BufferedReader reader = null;
    private static BufferedWriter writer = null;


    public static void main(String[] args) throws IOException {
//init
        reader = new BufferedReader(new FileReader(FILE_INPUT));
        writer = new BufferedWriter(new FileWriter(FILE_OUTPUT));


//read and count
        int maxConsecutiveUnits = 0;
        int localConsecutiveUnits = 0;
        int size = read();

        for(int i=0; i<size; ++i) {
            int current = read();
            if(current == 1) {
                ++localConsecutiveUnits;
            } else {
                maxConsecutiveUnits = Math.max(localConsecutiveUnits, maxConsecutiveUnits);
                localConsecutiveUnits = 0;
            }

        }

//write
        writer.write(String.valueOf(maxConsecutiveUnits));
        writer.newLine();

//clothe
        reader.close();
        writer.close();
        System.out.println();

    }

    public static int read() throws IOException {
        return Integer.valueOf(reader.readLine());
    }
    
}
