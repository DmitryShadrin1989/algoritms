package practicum.course_2022.sprint4;
/*
F. Анаграммная группировка
Вася решил избавиться от проблем с произношением и стать певцом. Он обратился за помощью к логопеду. Тот посоветовал Васе выполнять упражнение, которое называется анаграммная группировка. В качестве подготовительного этапа нужно выбрать из множества строк анаграммы.
Анаграммы –— это строки, которые получаются друг из друга перестановкой символов. Например, строки «SILENT» и «LISTEN» являются анаграммами.
Помогите Васе найти анаграммы.

Формат ввода
В первой строке записано число n —– количество строк.
Далее в строку через пробел записаны n строк.
n не превосходит 6000. Длина каждой строки не более 100 символов.

Формат вывода
Нужно вывести в отсортированном порядке индексы строк, которые являются анаграммами.
Каждая группа индексов должна быть выведена в отдельной строке. Индексы внутри одной группы должны быть отсортированы по возрастанию. Группы между собой должны быть отсортированы по возрастанию первого индекса.
Обратите внимание, что группа анаграмм может состоять и из одной строки. Например, если в исходном наборе нет анаграмм, то надо вывести n групп, каждая из которых состоит из одного индекса.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class F {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(reader.readLine());
            String[] strings = reader.readLine().split(" ");

            List<byte[]> bytes = new ArrayList<>();
            for (int i = 0; i<count; i++) {
                byte[] b = strings[i].getBytes();
                Arrays.sort(b);
                bytes.add(b);
            }

            for (int i = 0; i<count; i++) {
                if (bytes.get(i)==null) continue;

                StringBuilder builder = new StringBuilder();
                builder.append(i);

                byte[] bytesI = bytes.get(i);

                for (int j = i+1; j<count; j++) {
                    if (bytes.get(j)==null) continue;
                    boolean isAnagram = true;

                    if (strings[i].length() != strings[j].length()) {
                        continue;
                    } else {
                        byte[] bytesJ = bytes.get(j);
                        for (int f = 0; f < bytesJ.length; f++) {
                            if (bytesI[f] != bytesJ[f]) {
                                isAnagram = false;
                                break;
                            }
                        }
                    }
                    if (isAnagram) {
                        builder.append(" ").append(j);
                        bytes.set(j, null);
                    }
                }
                System.out.println(builder);
            }
        }
    }
}
