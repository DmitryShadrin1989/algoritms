package yandex.practicum.sprint8;
/*
J. Случай верблюда

В некоторых IDE поддерживается навигация по файлам через их сокращённые названия.
Если в языке принято называть классы CamelCase'ом (как в Java, например),
то по заглавным буквам названия можно быстро найти нужный класс.
Например, если название класса «MyFavouriteConfigurableScannerFactory», то его можно найти по строке «MFCSF».
Но если в проекте есть класс «theMultiFunctionalCommaSeparatedFile», то он тоже будет подходить под этот паттерн,
и при поиске надо будет выбрать между этими двумя вариантами.

Вам дан набор строк в CamelCase. Далее будут поступать запросы в виде строк-паттернов из прописных букв английского алфавита.
Вам надо находить такие строки среди исходных, которые удовлетворяют заданному шаблону, и выводить их в лексикографическом порядке.

Также в паттерне может быть только несколько первых заглавных букв. Например,
если бы в указанном выше примере был бы паттерн «MFCS», то существующие две строки походили бы под него,
а также подходил бы, например, «MamaFicusCodingSouthWestNorth». А вот «MamaCodingSouthWestNorth» –— уже нет.

Формат ввода
В первой строке записано число — количество названий классов в исходном наборе n (1 ≤ n ≤ 105).
Все названия состоят из строчных и прописных букв английского алфавита.
В следующих n строках даны сами названия по одному в строке. Суммарная длина этих строк не превосходит 107.
Затем дано количество запросов m (1 ≤ m ≤ 100).
В следующих *m* строках даны сами запросы. Каждый запрос –— это шаблон, строка из прописных букв английского алфавита,
в длину не превышающая 105. Шаблон может быть пустым. Заметьте: шаблону из нуля прописных букв удовлетворяет любое название.

Формат вывода
Для каждого отдельного запроса (в порядке их поступления) выведите в лексикографическом порядке все строки,
которые подходят под данный шаблон. Если какие-то строки одинаковые, то выведите все экземпляры.
Если ни одна из строк не подходит под шаблон, то выведите для данного запроса пустую строку.

Пример 1
Ввод
3
MamaMilaRamu
MamaMia
MonAmi
2
MM
MA

Вывод
MamaMia
MamaMilaRamu
MonAmi

Пример 2
Ввод
2
AlphaBetaGgamma
AbcdBcdGggg
2
ABGG
ABG

Вывод
AbcdBcdGggg
AlphaBetaGgamma

Пример 3
Ввод
5
WudHnagkbhfwrbci
WCUkvoxboxufsdap
jdrxomezzrpuhbgi
ZcGHdrPplfoldemu
cylbtqwuxhiveznc
3
WGHV
NKVDT
ZGHU

Вывод

 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class J {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());

            Trie trie = new Trie();

            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String string = reader.readLine();
                strings.add(string);
            }

            for (int i = 0; i < strings.size(); i++) {
                trie.add(strings.get(i), i);
            }

            int m = Integer.parseInt(reader.readLine());
            ArrayList<String> pattens = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                String pattern = reader.readLine();
                pattens.add(pattern);
            }


            StringBuilder builder = new StringBuilder();
            for (String pattern : pattens) {
                ArrayList<Integer> res = trie.Search(pattern);
                if (res == null) {
                    continue;
                }

                TreeSet<String> treeSet = new TreeSet<>();
                for (Integer integer : res) {
                    treeSet.add(strings.get(integer));
                }
                while (!treeSet.isEmpty()) {
                    if (builder.length() != 0) {
                        builder.append("\n");
                    }
                    builder.append(treeSet.pollFirst());
                }
            }
            System.out.println(builder);
        }
    }

    static class Trie {
        Node root = new Node(new ArrayList<>(), new HashMap<>());

        public void add(String string, int idx) {
            Node node = root;
            root.terms.add(idx);
            for (int i = 0; i < string.length(); i++) {
                char ch = string.charAt(i);
                if (Character.isUpperCase(ch)) {
                    if (node.next.containsKey(ch)) {
                        node = node.next.get(ch);
                    } else {
                        Node newNode = new Node(new ArrayList<>(), new HashMap<>());
                        node.next.put(ch, newNode);
                        node = newNode;
                    }
                    node.terms.add(idx);
                }
            }
        }

        public ArrayList<Integer> Search(String pattern) {
            Node node = root;
            for (int i = 0; i < pattern.length(); i++) {
                if (node.next.containsKey(pattern.charAt(i))) {
                    node = node.next.get(pattern.charAt(i));
                } else {
                    return null;
                }
            }
            return node.terms;
        }
    }

    static class Node {
        ArrayList<Integer> terms;
        HashMap<Character, Node> next;

        public Node(ArrayList<Integer> terms, HashMap<Character, Node> next) {
            this.terms = terms;
            this.next = next;
        }
    }
}
