package yandex.anagrams;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AnagramsEx2 {

    private static final String FILE_INPUT = "C:\\Users\\Administrator\\Desktop\\Education Files\\directory file\\input.txt";
    private static final String FILE_OUTPUT = "C:\\Users\\Administrator\\Desktop\\Education Files\\directory file\\output.txt";
    private static BufferedReader bufferedReader = null;
    private static BufferedWriter bufferedWriter = null;
    public static void main(String[] args) throws Exception {
        init();
        run();
        close();
    }

    private static void init() throws IOException {
        bufferedReader = new BufferedReader(new FileReader(FILE_INPUT));
        bufferedWriter = new BufferedWriter(new FileWriter(FILE_OUTPUT));
    }

    private static void close() throws IOException {
        bufferedWriter.close();
        bufferedReader.close();
    }

    private static String readLine() throws IOException {
        return bufferedReader.readLine();
    }

    private static void writeLine(char ch) throws IOException {
        bufferedWriter.write(ch);
        bufferedWriter.newLine();
    }

    private static void run() throws IOException {

        String s = readLine();
        String s1 = readLine();
        if (s1.equals(s)) {
            writeLine('1');
            return;
        }
        if (s1.length() != s.length()) {
            writeLine('0');
            return;
        }

        byte [] bytes1 = s. getBytes(StandardCharsets.UTF_8);
        Arrays.sort(bytes1);

        byte [] bytes2 = s1. getBytes(StandardCharsets.UTF_8);
        Arrays.sort(bytes2);

        for (int i=0; i<bytes1.length; i++) {
            if (bytes1[i] != bytes2[i]) {
                writeLine('0');
                return;
            }
        }
        writeLine('1');
    }
}
