package practicum.tsks_10_2024.introductoryContest;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AB2 {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("input.txt");
        Scanner scanner = new Scanner(fileReader);
        String line = scanner.nextLine();
        String[] numbers = line.split(" ");
        int a = Integer.parseInt(numbers[0]);
        int b = Integer.parseInt(numbers[1]);
        int result = a + b;
        FileWriter fileWriter = new FileWriter("output.txt");
        fileWriter.write(String.valueOf(result));
        fileWriter.close();
    }
}
