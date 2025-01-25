package practicum.course_2022.sprint5.exam.B.v3;

/*
-- ПРИНЦИП РАБОТЫ --
Для того, чтобы после удаления узла дерево было сбалансировано, выполняется попытка заменить удаляемый узел на более подходящий среди его потомков.
Наиболее подходящим будет считаться:
1) При наличии двух потомков, либо самый правый узел в левом поддереве, либо самый левый узел в правом поддереве.
2) При наличии только одного потомка можно заменить удаляемый узел на этот единственный потомок.
3) Если потомков нет то в родителе просто удаляется ссылка на удаляемый узел.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Сначала обрабатываются краевые случаи, потом более базовые:
1) Если корень = null то возвращае null.
2) Далее выполняется поиск узла который необходимо удалить. Если такого узла нет, то возвращаем корень дерева.
3) Проверяем у удаляемого узла наличие 2х потомков, если их нет то проверяем наличие родителя, если родителя нет то возвращаем null, т.к. это был последний элемент в дереве.
Если родитель есть то возвращаем корень
4) Далее проверяем, что у удаляемого узла есть только один потомок, если это так то проверим есть ли родитель,
если нет то заменяемый узел теперь это корень - вернем его, если родитель есть то выполняем замену удалемого узла на него и возвращаем корень.
5) Краевые случаи обработаны, далее выполняется поиск узла для замены и его родителя.
Если нет родителя у узла для замены - это значит, что его родитель - это удаляемый узел. Сделаем замену на него.
6) Базовый случай когда есть родители и у удаляемого и у узла для замены. Выполняем замену.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Для реализации данного алгоритма используется рекурсия для поиска удаляемого узла и вложенный цикл для поиска узла для замены.
Глубина рекурсии + количество итераций цикла не будет превышать h, где h - это высота дерева.
в худшем случае h = n, где n - количество элементов, тогда можно сказать, что временная сложность O(n) ~ O(h)
в лучшем случае (когда дерево сбалансировано) временная сложность будет O(logn) ~ O(h)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Память выделяется для узлов дерева линейно O(n).

Успешная посылка - 82183725
 */

public class Solution {

    public static void main(String[] args) {
        test();
        test2();
    }

    public static Node remove(Node root, int key) {
        if (root == null) {
            return null;
        }else if (key < root.getValue() && root.getLeft() != null) {
            root.setLeft(remove(root.getLeft(), key));
        } else if (key > root.getValue() && root.getRight() != null) {
            root.setRight(remove(root.getRight(), key));
        } else {
            if (root.getValue() != key) {
                return root;
            }else if (root.getLeft() == null && root.getRight() == null) {
                return null;
            }else if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            } else {
                Node replacementNode = root.getLeft();
                Node parentReplacementNode = null;

                while (true) {
                    if (replacementNode.getRight() != null) {
                        parentReplacementNode = replacementNode;
                        replacementNode = replacementNode.getRight();
                    } else {
                        break;
                    }
                }

                if (parentReplacementNode != null) {
                    if (replacementNode.getLeft() != null) {
                        parentReplacementNode.setRight(replacementNode.getLeft());
                        replacementNode.setLeft(null);
                    } else {
                        parentReplacementNode.setRight(null);
                    }

                    replacementNode.setLeft(root.getLeft());
                }
                replacementNode.setRight(root.getRight());
                return replacementNode;
            }
        }
        return root;
    }

    // Comment it before submitting
    private static class Node {
        private int value;
        private Node left;
        private Node right;

        Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setValue(int value) {
            this.value = value;
        }

    }
    //

    private static void test() {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(node1, null, 3);
        Node node3 = new Node(null, node2, 1);
        Node node4 = new Node(null, null, 6);
        Node node5 = new Node(node4, null, 8);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node3, node6, 5);
        Node newHead = remove(node7, 10);
        assert newHead.getValue() == 5;
        assert newHead.getRight() == node5;
        assert newHead.getRight().getValue() == 8;
    }

    private static void test2() {
        Node node7 = new Node(null, null, 7);
        Node node6 = new Node(null, null, 5);
        Node node5 = new Node(null, null, 3);
        Node node4 = new Node(null, null, 1);
        Node node3 = new Node(node6, node7, 6);
        Node node2 = new Node(node4, node5, 2);
        Node node1 = new Node(node2, node3, 4);
        Node newHead = remove(node1, 2);
        assert newHead.getValue() == 4;
    }
}
