package practicum.course_2022.sprint8;

/*
K. Сравнить две строки

Алла придумала новый способ сравнивать две строки: чтобы сравнить строки a и b, в них надо оставить только те буквы,
которые в английском алфавите стоят на четных позициях. Затем полученные строки сравниваются по обычным правилам.
Помогите Алле реализовать новое сравнение строк.

Формат ввода
На вход подаются строки a и b по одной в строке. Обе строки состоят из маленьких латинских букв,
не бывают пустыми и не превосходят 105 символов в длину.

Формат вывода
Выведите -1, если a < b, 0, если a = b, и 1, если a > b.

Пример 1
Ввод
gggggbbb
bbef

Вывод
-1

Пример 2
Ввод
z
aaaaaaa

Вывод
1

Пример 3
Ввод
ccccz
aaaaaz

Вывод
0
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class K {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s1 = reader.readLine();
            String s2 = reader.readLine();

            byte[] s1Bytes = s1.getBytes();
            StringBuilder stringBuilder1 = new StringBuilder();
            for (byte b: s1Bytes) {
                if (b%2 == 0) {
                    stringBuilder1.append((char) b);
                }
            }

            byte[] s2Bytes = s2.getBytes();
            StringBuilder stringBuilder2 = new StringBuilder();
            for (byte b: s2Bytes) {
                if (b%2 == 0) {
                    stringBuilder2.append((char) b);
                }
            }

            if (stringBuilder1.compareTo(stringBuilder2) < 0) {
                System.out.println(-1);
            } else if (stringBuilder1.compareTo(stringBuilder2) > 0) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
