package practicum.course_2022.sprint3;

/*
G. Гардероб

Рита решила оставить у себя одежду только трёх цветов: розового, жёлтого и малинового.
После того как вещи других расцветок были убраны, Рита захотела отсортировать свой новый гардероб по цветам.
Сначала должны идти вещи розового цвета, потом —– жёлтого, и в конце —– малинового. Помогите Рите справиться с этой задачей.

Примечание: попробуйте решить задачу за один проход по массиву!

Формат ввода
В первой строке задано количество предметов в гардеробе: n –— оно не превосходит 1000000.
Во второй строке даётся массив, в котором указан цвет для каждого предмета.
Розовый цвет обозначен 0, жёлтый —– 1, малиновый –— 2.

Формат вывода
Нужно вывести в строку через пробел цвета предметов в правильном порядке.

Пример 1
Ввод
7
0 2 1 2 0 0 1

Вывод
0 0 0 1 1 2 2


Пример 2
Ввод
5
2 1 2 0 1

Вывод
0 1 1 2 2


Пример 3
Ввод
6
2 1 1 2 0 2

Вывод
0 1 1 2 2 2

Пример 4
Ввод
6
2 0 2 0 1 1

Вывод
0 0 1 1 2 2


 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(reader.readLine());
            String[] strings = reader.readLine().split(" ");

            int indexZero = 0;
            int indexSecond = count-1;
            for (int i = 0; i <= indexSecond; i++) {
                if (strings[i].equals("0")) {
                    String tmp = strings[i];
                    strings[i] = strings[indexZero];
                    strings[indexZero] = tmp;
                    indexZero++;
                }

                while(true) {
                    if (strings[indexSecond].equals("2")) {
                        indexSecond--;
                    } else {
                        break;
                    }
                }

                if (strings[i].equals("2")) {
                    String tmp = strings[i];
                    strings[i] = strings[indexSecond];
                    strings[indexSecond] = tmp;
                    indexSecond--;

                    if (strings[i].equals("0")) {
                        String tmp2 = strings[i];
                        strings[i] = strings[indexZero];
                        strings[indexZero] = tmp2;
                        indexZero++;
                    }
                }
            }

            StringBuilder builder = new StringBuilder();
            builder.append(strings[0]);
            for (int i = 1; i < count; i++) {
                builder.append(" ").append(strings[i]);
            }
            System.out.println(builder);
        }
    }
}
