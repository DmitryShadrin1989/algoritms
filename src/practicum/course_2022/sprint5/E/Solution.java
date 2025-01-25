package practicum.course_2022.sprint5.E;
/*
E. Дерево поиска

Гоша понял, что такое дерево поиска, и захотел написать функцию, которая определяет, является ли заданное дерево деревом поиска.
Значения в левом поддереве должны быть строго меньше, в правом —- строго больше значения в узле.
Помогите Гоше с реализацией этого алгоритма.

 */

import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        test4();
    }

    public static boolean treeSolution(Node head) {
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node parent = null;
        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if (node.left != null) {
                if (node.left.value < node.value
                        && (parent == null || node.left.value < parent.value)) {
                    stack.push(node.left);
                } else return false;
            }

            if (node.right != null) {
                if (node.right.value > node.value
                        && (parent == null || node.right.value < parent.value)) {
                    stack.push(node.right);
                } else return false;
            }

            parent = node;
        }
        return true;
    }

    // Comment it before submitting
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    //

    private static void test() {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        assert treeSolution(node5);
        node2.value = 5;
        assert !treeSolution(node5);
    }

    private static void test2() {
        Node node0 = new Node(5);
        Node node1 = new Node(4);
        Node node2 = new Node(6);
        Node node3 = new Node(2);
        Node node4 = new Node(6);
        Node node5 = new Node(4);
        Node node6 = new Node(8);

        node0.left = node1;
        node0.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        System.out.println(treeSolution(node0));

        /* False
             5
        4          6
    2       6
        */
    }

    private static void test3() {
        Node node0 = new Node(2);
        Node node1 = new Node(1);
        Node node2 = new Node(3);

        node0.left = node1;
        node0.right = node2;

        System.out.println(treeSolution(node0));

        /* True
             2
        1          3
        */
    }

    private static void test4() {
        Node node0 = new Node(5);
        Node node1 = new Node(3);
        Node node2 = new Node(8);
        Node node3 = new Node(1);
        Node node4 = new Node(4);
        Node node5 = new Node(6);
        Node node6 = new Node(9);

        node0.left = node1;
        node0.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        System.out.println(treeSolution(node0));

        /* True
                         5
                  3             8
              1       4     6       9
        */
    }
}
