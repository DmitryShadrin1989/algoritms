package yandex.practicum.sprint8.exam.B;
/*
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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class B4 {
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
                trie.add(word);
            }

            int dp[] = new int[cheatSheet.length() + 1];
            int j = 1;
            Node node = trie.root;
            while (j <= cheatSheet.length()) {
                char ch = cheatSheet.charAt(j-1);
                dp[j] = dp[j-1];

                if (node.next.containsKey(ch)) {
                    node = node.next.get(ch);
                    if (node.terminate) {
                        dp[j] = j;
                    }
                    j++;
                } else {
                    int prevTerminate = dp[j];
                    node = trie.root;
                    while (prevTerminate != 0) {
                        ch = cheatSheet.charAt(dp[prevTerminate]);
                        if (node.next.containsKey(ch)) {
                            j = dp[prevTerminate] + 1;
                            dp[prevTerminate] = dp[prevTerminate-1];
                            break;
                        }
                        prevTerminate = dp[prevTerminate-1];
                    }
                    if (prevTerminate == 0) {
                        System.out.println("NO");
                        return;
                    }
                }
            }
            if (dp[j-1] == cheatSheet.length()) {
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
