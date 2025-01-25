package practicum.bracket;

import java.io.*;

public class GeneratingBracketSequences {
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

    private static int readLine() throws IOException {
        return Integer.parseInt(bufferedReader.readLine());
    }

    private static void writeLine(char[] IntToFile) throws IOException {
        bufferedWriter.write(IntToFile);
        bufferedWriter.newLine();
    }

    private static void run() throws IOException {
        int n = readLine() * 2;
        if (n < 2) return;
        char[] chrs = new char[n];
        for (int i = 0; i < n/2; i++) {
            chrs[i] = '(';
        }
        for (int i = n/2; i < n; i++) {
            chrs[i] = ')';
        }
        writeLine(chrs);
        do {
            // Идем с конца, ищем первую открывающую скобку
            int i = n - 1;
            // Понять, что такое
            int c = 0;
            while (i >= 0) {
                c += chrs[i] == ')' ? -1 : 1;
                if ((c < 0) && (chrs[i] == '(')) break;
                --i;
            }
            if (i < 0) break;

            // Когда нашли первую открывающую скобку с конца, мы ее меняем на закрывающую
            chrs[i++] = ')';
            // Теперь опять идем в конец массива, чтобы
            int ind = i;
            for (; i < n; i++) {
                //chrs[i] = (i<= (n-ind+c)/2+ind) ? '(' : ')';
                if (i<= (n-ind+c)/2+ind) {
                    chrs[i] = '(';
                } else {
                    chrs[i] = ')';
                }

            }
            writeLine(chrs);
        }while (true);
    }
}
