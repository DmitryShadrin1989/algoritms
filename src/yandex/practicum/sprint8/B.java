package yandex.practicum.sprint8;

/*
B. Пограничный контроль

Представьте, что вы работаете пограничником и постоянно проверяете документы людей по записи из базы.
При этом допустима ситуация, когда имя человека в базе отличается от имени в паспорте на одну замену,
одно удаление или одну вставку символа. Если один вариант имени может быть получен из другого удалением одного символа,
то человека пропустят через границу. А вот если есть какое-либо второе изменение, то человек грустно поедет домой или в посольство.

Например, если первый вариант —– это «Лена», а второй — «Лера», то девушку пропустят.
Также человека пропустят, если в базе записано «Коля», а в паспорте — «оля».

Однако вариант, когда в базе числится «Иннокентий», а в паспорте написано «ннакентий», уже не сработает.
Не пропустят также человека, у которого в паспорте записан «Иинннокентий», а вот «Инннокентий» спокойно пересечёт границу.

Напишите программу, которая сравнивает имя в базе с именем в паспорте и решает, пропускать человека или нет.
В случае равенства двух строк — путешественника, естественно, пропускают.

Формат ввода
В первой строке дано имя из паспорта.

Во второй строке —- имя из базы.

Обе строки состоят из строчных букв английского алфавита. Размер каждой строки не превосходит 100 000 символов.

Формат вывода
Выведите «OK», если человека пропустят, или «FAIL» в противном случае.

Пример 1
Ввод
abcdefg
abdefg

Вывод
OK

Пример 2
Ввод
helo
hello

Вывод
OK

Пример 3
Ввод
dog
fog

Вывод
OK

Пример 4
Ввод
mama
papa

Вывод
FAIL
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s1 = reader.readLine();
            String s2 = reader.readLine();

            if (Math.abs(s1.length() - s2.length())>1) {
                System.out.println("FAIL");
                return;
            }

            char[] chars1 = s1.toCharArray();
            char[] chars2 = s2.toCharArray();

            int cursorS1 = 0;
            int cursorS2 = 0;
            int countWrong = 0;
            while (cursorS1 < s1.length() && cursorS2 < s2.length()) {
                if (chars1[cursorS1] != chars2[cursorS2]) {
                    ++countWrong;
                    if (countWrong<2) {
                        if (s1.length()>s2.length()) {
                            cursorS1++;
                        } else if (s1.length()<s2.length()) {
                            cursorS2++;
                        } else {
                            cursorS1++;
                            cursorS2++;
                        }
                        continue;
                    } else {
                        System.out.println("FAIL");
                        return;
                    }
                }
                cursorS1++;
                cursorS2++;
            }
            System.out.println("OK");
        }
    }
}
