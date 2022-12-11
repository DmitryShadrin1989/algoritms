package yandex.practicum.sprint2;

/*
Алла получила задание, связанное с мониторингом работы различных серверов. Требуется понять, сколько времени обрабатываются определённые запросы на конкретных серверах. Эту информацию нужно хранить в матрице, где номер столбца соответствуют идентификатору запроса, а номер строки — идентификатору сервера. Алла перепутала строки и столбцы местами. С каждым бывает. Помогите ей исправить баг.
Есть матрица размера m × n. Нужно написать функцию, которая её транспонирует.
Транспонированная матрица получается из исходной заменой строк на столбцы.
Например, для матрицы А (слева) транспонированной будет следующая матрица (справа):
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int countLine = Integer.parseInt(reader.readLine());
            int countColumn = Integer.parseInt(reader.readLine());

            int[][] ints = new int[countColumn][countLine];

            for (int i=0; i<countLine; i++) {
                String[] strings = reader.readLine().split(" ");
                for (int j=0; j<countColumn; j++) {
                    ints[j][i] = Integer.parseInt(strings[j]);
                }
            }

            for (int i=0; i<countColumn; i++) {
                StringBuilder builder = new StringBuilder();
                builder.append(ints[i][0]);
                if (countLine>1) {
                    for (int j=1; j<countLine; j++) {
                        builder.append(" ").append(ints[i][j]);
                    }
                }
                System.out.println(builder);
            }
        }
    }
}
