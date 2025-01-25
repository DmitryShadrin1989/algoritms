package practicum.course_2022.sprint8.E;

/*
E. Вставка строк

У Риты была строка s, Гоша подарил ей на 8 марта ещё n других строк ti, 1≤ i≤ n.
Теперь Рита думает, куда их лучше поставить. Один из вариантов —– расположить подаренные строки внутри имеющейся строки s,
поставив строку ti сразу после символа строки s с номером ki (в частности, если ki=0, то строка вставляется в самое начало s).

Помогите Рите и определите, какая строка получится после вставки в s всех подаренных Гошей строк.

Формат ввода
В первой строке дана строка s. Строка состоит из строчных букв английского алфавита,
не бывает пустой и её длина не превышает 105 символов.

Во второй строке записано количество подаренных строк — натуральное число n, 1 ≤ n ≤ 105.

В каждой из следующих n строк через пробел записаны пары ti и ki.
Строка ti состоит из маленьких латинских букв и не бывает пустой.
ki — целое число, лежащее в диапазоне от 0 до |s|. Все числа ki уникальны.
Гарантируется, что суммарная длина всех строк ti не превосходит 105.

Формат вывода
Выведите получившуюся в результате вставок строку.

Пример 1
Ввод
abacaba
3
queue 2
deque 0
stack 7

Вывод
dequeabqueueacabastack

Пример 2
Ввод
kukareku
2
p 1
q 2

Вывод
kpuqkareku

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class E1 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] string = reader.readLine().split("");
            int n = Integer.parseInt(reader.readLine());
            TreeSet<ABC> abcTreeSet = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                String[] s = reader.readLine().split(" ");
                abcTreeSet.add(new ABC(s[0], Integer.parseInt(s[1])));
            }

            ArrayList<String> result = new ArrayList<>();
            int current = 0;
            for (ABC abc : abcTreeSet) {
                while (current != abc.n) {
                    result.add(string[current]);
                    current++;
                }
                result.add(abc.string);
            }
            for (int i = current; i < string.length; i++) {
                result.add(string[i]);
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : result) {
                stringBuilder.append(s);
            }
            System.out.println(stringBuilder);
        }
    }

    static class ABC implements Comparable<ABC>{
        String string;
        int n;

        public ABC(String string, int n) {
            this.string = string;
            this.n = n;
        }

        @Override
        public int compareTo(ABC other) {
            return this.n - other.n;
        }
    }
}
