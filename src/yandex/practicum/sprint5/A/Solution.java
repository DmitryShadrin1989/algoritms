package yandex.practicum.sprint5.A;

/*
A. Лампочки

Гоша повесил на стену гирлянду в виде бинарного дерева, в узлах которого находятся лампочки.
У каждой лампочки есть своя яркость. Уровень яркости лампочки соответствует числу, расположенному в узле дерева.
Помогите Гоше найти самую яркую лампочку в гирлянде, то есть такую, у которой яркость наибольшая.

Формат ввода
На вход подается корень дерева.
Замечания про отправку решений
По умолчанию выбран компилятор make. Решение нужно отправлять в виде файла с расширением, которое соответствует вашему языку программирования.
Если вы пишете на Java, имя файла должно быть Solution.java, для C# – Solution.cs.
Для остальных языков назовите файл my_solution.ext, заменив ext на необходимое расширение.

 */

import java.util.Stack;

public class Solution {
    public static int treeSolution(Node head) {
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(head);
        int maxValue = 0;
        while (!nodeStack.isEmpty()) {
            Node node = nodeStack.pop();
            maxValue = Math.max(maxValue, node.value);

            if (node.left != null) {
                nodeStack.push(node.left);
            }
            if (node.right != null) {
                nodeStack.push(node.right);
            }
        }
        return maxValue;
    }


    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }


    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        assert treeSolution(node4) == 3;
    }
}
