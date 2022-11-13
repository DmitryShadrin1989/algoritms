package practicum;

//B. Застёжка-молния
//        Даны два массива чисел длины n. Составьте из них один массив длины 2n, в котором числа из входных массивов чередуются (первый — второй — первый — второй — ...). При этом относительный порядок следования чисел из одного массива должен быть сохранён.
//
//        Формат ввода
//        В первой строке записано целое число n –— длина каждого из массивов, 1 ≤ n ≤ 1000.
//
//        Во второй строке записано n чисел из первого массива, через пробел.
//
//        В третьей строке –— n чисел из второго массива.
//
//        Значения всех чисел –— натуральные и не превосходят 1000.
//
//        Формат вывода
//        Выведите 2n чисел из объединённого массива через пробел.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZipperClosure {
    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int[] ints = new int[Integer.parseInt(reader.readLine()) * 2];
            String[] s1 = reader.readLine().split(" ");
            String[] s2 = reader.readLine().split(" ");

            int iS1 = 0;
            int iS2 = 0;

            for (int i = 0; i < ints.length; i++) {

                if (i % 2 == 0) {
                    ints[i] = Integer.parseInt(s1[iS1]);
                    iS1++;
                } else {
                    ints[i] = Integer.parseInt(s2[iS2]);
                    iS2++;
                }

                System.out.print(((i!=0)?" ": "") + ints[i]);
            }
        }
    }
}