package yandex.practicum.sprint8.exam.B;

/*
B. Шпаргалка

Вася готовится к экзамену по алгоритмам и на всякий случай пишет шпаргалки.

Чтобы уместить на них как можно больше информации, он не разделяет слова пробелами.
В итоге получается одна очень длинная строка. Чтобы на самом экзамене из-за нервов не запутаться в прочитанном,
он просит вас написать программу, которая по этой длинной строке и набору допустимых слов определит,
можно ли разбить текст на отдельные слова из набора.

Более формально: дан текст T и набор строк s1, ... ,sn.
Надо определить, представим ли T как sk1sk2...skr, где где ki — индексы строк.
Индексы могут повторяться. Строка si может встречаться в разбиении текста T произвольное число раз.
Можно использовать не все строки для разбиения. Строки могут идти в любом порядке.

Формат ввода
В первой строке дан текст T, который надо разбить на слова. Длина T не превосходит 105.
Текст состоит из строчных букв английского алфавита.

Во второй строке записано число допустимых к использованию слов 1 ≤ n ≤ 100.

В последующих n строках даны сами слова, состоящие из маленьких латинских букв. Длина каждого слова не превосходит 100.

Формат вывода
Выведите «YES», если текст можно разбить на слова из данного словаря, или «NO» в ином случае.

Пример 1
Ввод
examiwillpasstheexam
5
will
pass
the
exam
i

Вывод
YES

Пример 2
Ввод
abacaba
2
abac
caba

Вывод
NO

Пример 3
Ввод
abacaba
3
abac
caba
aba

Вывод
YES

Пример 4
Ввод
abbce
4
a
ab
bce
abb

Вывод
YES

Пример 5
Ввод
abbce
3
a
bce
abb

Вывод
No
*/

/*
-- ПРИНЦИП РАБОТЫ --
Для проверки на возможность разбиения текста на слова мы проходим по всем символам шпаргалки и выполняем поиск в префиксном дереве,
если на текущем элементе встречаем что данный узел префиксного дерева является terminate,
т.е. конечным для шаблона то в массиве динамики dp[] для этого индекса ставим 1 и сбрасываем узел обратно к корню.
Если очередной символ не является продолжением шаблона,
то мы откатываемся к предыдущему законченному слову и снова пытаемся дойти до узла terminate.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Честно не успел заполнить этот пункт.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Для построения префиксного дерева временная сложность будет O(N*K), где N - количество слов, а K - длинная слова.
Для заполнения одномерного массива динамики dp[] временная сложность будет O(M*N*K), где M - длинна шпаргалки,
N - количество слов, а K - длинная слова.
Общая временная сложность будет O(M*N*K + N*K) ~ O(M*N*K)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Для построения префиксного дерева пространственная сложность будет O(N*K), где N - количество слов, а K - длинная слова.
Для динамики dp[] пространственная сложность будет O(M), где M - длинна шпаргалки.
Общая временная сложность будет O(M + N*K)

Успешная посылка - https://contest.yandex.ru/contest/26133/run-report/84588233/
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class B6 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String cheatSheet = reader.readLine();

            int n = Integer.parseInt(reader.readLine());
            String[] words = new String[n];
            for (int i = 0; i < n; i++) {
                words[i] = reader.readLine();
            }

            Trie trie = new Trie();
            for (String word : words) {
                StringBuilder builder = new StringBuilder(word);
                trie.add(builder.reverse().toString());
            }

            int[] dp = new int[cheatSheet.length() + 1];
            dp[0] = 1;
            for (int i = 0; i < cheatSheet.length(); i++) {
                Node node = trie.root;
                int j = i + 1;
                while (node != null && j > 0) {
                    char ch = cheatSheet.charAt(j - 1);
                    node = node.next.get(ch);
                    j--;
                    if (node != null && node.terminate && dp[j] == 1) {
                        dp[i + 1] = 1;
                        break;
                    }

                }
            }

            if (dp[cheatSheet.length()] == 1) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static class Trie {
        Node root = new Node(new HashMap<>());

        public void add(String string) {
            Node node = root;
            for (int i = 0; i < string.length(); i++) {
                char ch = string.charAt(i);

                if (node.next.containsKey(ch)) {
                    node = node.next.get(ch);
                } else {
                    Node newNode = new Node(new HashMap<>());
                    node.next.put(ch, newNode);
                    node = newNode;
                }

                if (i == string.length()-1) {
                    node.terminate = true;
                }
            }
        }
    }

    static class Node {
        boolean terminate = false;
        HashMap<Character, Node> next;

        public Node(HashMap<Character, Node> next) {
            this.next = next;
        }
    }
}
