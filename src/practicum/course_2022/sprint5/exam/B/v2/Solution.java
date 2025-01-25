package practicum.course_2022.sprint5.exam.B.v2;
/*
B. Удали узел

Дано бинарное дерево поиска, в котором хранятся ключи. Ключи — уникальные целые числа. Найдите вершину с заданным ключом и удалите её из дерева так, чтобы дерево осталось корректным бинарным деревом поиска. Если ключа в дереве нет, то изменять дерево не надо.
На вход вашей функции подаётся корень дерева и ключ, который надо удалить. Функция должна вернуть корень изменённого дерева. Сложность удаления узла должна составлять
O(h), где h –— высота дерева.
Создавать новые вершины (вдруг очень захочется) нельзя.
 */

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
Для реализации данного алгоритма используется два цикла:
1) Поиск удаляемого узла по ключу. Это бинарный поиск и он имеет сложность O(logn), где n - это количество элементов или O(h), где h - высота дерева.
2) Поиск узла для замены. Это также бинарный поиск. Сложность O(logn), где n - это количество элементов или O(h), где h - высота дерева.
Вывод: сложность всей программы O(2*h) ~ O(h), где h - высота дерева.
P.S. Можно было бы избавиться от второго цикла, т.е. найти и удаляемый узел и узел для замены и их родителей за один проход, но считаю, что это даже менее экономно,
т.к. в текущей реализации второй цикл по сути продолжается с того места где завершился первый, а между ними есть проверка нескольких краевых случаев.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Память выделяется для узлов дерева линейно O(n).

Успешная посылка - 81863199
 */

public class Solution {
    public static void main(String[] args) {
        test();
    }

    public static Node remove(Node root, int key) {
        if (root == null) {
            return null;
        }

        Node deleteNode = null;
        Node parentDeleteNode = null;

        Node currentNode = root;
        Node parentCurrentNode = null;
        while (true) {
            if (currentNode.getValue() == key) {
                deleteNode = currentNode;
                parentDeleteNode = parentCurrentNode;
                break;
            }
            parentCurrentNode = currentNode;
            if (currentNode.getLeft() != null && key < currentNode.getValue()) {
                currentNode = currentNode.getLeft();
            } else if (currentNode.getRight() != null && key > currentNode.getValue()) {
                currentNode = currentNode.getRight();
            } else {
                break;
            }
        }

        if (deleteNode == null) {
            return root;
        }

        if (deleteNode.getLeft() == null && deleteNode.getRight() == null) {
            if (parentDeleteNode != null) {
                replaceNode(null, parentDeleteNode, key);
                return root;
            } else {
                return null;
            }
        }

        if (deleteNode.getLeft() == null) {
            if (parentDeleteNode != null) {
                replaceNode(deleteNode.getRight(), parentDeleteNode, key);
                return root;
            } else {
                return deleteNode.getRight();
            }

        } else if (deleteNode.getRight() == null) {
            if (parentDeleteNode != null) {
                replaceNode(deleteNode.getLeft(), parentDeleteNode, key);
                return root;
            } else {
                return deleteNode.getLeft();
            }
        }

        Node replacementNode = deleteNode.getLeft();
        Node parentReplacementNode = null;
        while (true) {
            if (replacementNode.getRight() != null) {
                parentReplacementNode = replacementNode;
                replacementNode = replacementNode.getRight();
            } else {
                break;
            }
        }

        if (parentReplacementNode == null) {
            replacementNode.setRight(deleteNode.getRight());
            if (parentDeleteNode != null) {
                replaceNode(replacementNode, parentDeleteNode, key);
                return root;
            } else {
                replacementNode.setRight(deleteNode.getRight());
                return replacementNode;
            }
        } else {
            if (replacementNode.getLeft() != null) {
                parentReplacementNode.setRight(replacementNode.getLeft());
                replacementNode.setLeft(null);
            } else {
                parentReplacementNode.setRight(null);
            }

            replacementNode.setLeft(deleteNode.getLeft());
            replacementNode.setRight(deleteNode.getRight());

            if (parentDeleteNode != null) {
                replaceNode(replacementNode, parentDeleteNode, key);
                return root;
            } else {
                replacementNode.setLeft(deleteNode.getLeft());
                replacementNode.setRight(deleteNode.getRight());
                return replacementNode;
            }
        }
    }

    public static void replaceNode(Node replacementNode, Node parentDeleteNode, int key) {
        if (parentDeleteNode.getLeft() != null && parentDeleteNode.getLeft().getValue() == key) {
            parentDeleteNode.setLeft(replacementNode);
        } else {
            parentDeleteNode.setRight(replacementNode);
        }
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
}
