package yandex.practicum.sprint5.exam.B.v1;
/*
B. Удали узел

Дано бинарное дерево поиска, в котором хранятся ключи. Ключи — уникальные целые числа. Найдите вершину с заданным ключом и удалите её из дерева так, чтобы дерево осталось корректным бинарным деревом поиска. Если ключа в дереве нет, то изменять дерево не надо.
На вход вашей функции подаётся корень дерева и ключ, который надо удалить. Функция должна вернуть корень изменённого дерева. Сложность удаления узла должна составлять
O(h), где h –— высота дерева.
Создавать новые вершины (вдруг очень захочется) нельзя.
 */

public class Solution {

    public static void main(String[] args) {
        test();
    }

    public static Node remove(Node root, int key) {
        Node[] deletedNodes = searchDeletedNode(root, key);
        Node deletedNode = deletedNodes[0];
        Node parentDeletedNode = deletedNodes[1];

        if (deletedNode != null) {

            if (deletedNode.getLeft() == null && deletedNode.getRight() == null && parentDeletedNode != null) {
                if (parentDeletedNode.getLeft().getValue() == key) {
                    parentDeletedNode.setLeft(null);
                } else {
                    parentDeletedNode.setRight(null);
                }
                return root;
            }

            Node[] replacementNodes = searchReplacementNode(deletedNodes);
            Node replacementNode = replacementNodes[0];
            Node parentReplacementNode = replacementNodes[1];

            if (parentReplacementNode != null) {
                if (parentReplacementNode.getLeft() != null && parentReplacementNode.getLeft().getValue() == replacementNode.getValue()) {
                    parentReplacementNode.setLeft(replacementNode.getRight());
                } else {
                    parentReplacementNode.setRight(replacementNode.getLeft());
                }
            }

            if (parentDeletedNode != null) {
                if (parentDeletedNode.getLeft() != null && parentDeletedNode.getLeft().getValue() == deletedNode.getValue()) {
                    parentDeletedNode.setLeft(replacementNode);
                } else {
                    parentDeletedNode.setRight(replacementNode);
                }
            }

            replacementNode.setLeft(deletedNode.getLeft());
            replacementNode.setRight(deletedNode.getRight());
        }

        return root;
    }

    private static Node[] searchReplacementNode(Node[] deletedNodes) {
        Node[] result = new Node[2];
        Node deletedNode = deletedNodes[0];
        Node parentReplacementNode = null;
        Node replacementNode = null;
        if (deletedNode.getLeft() != null) {
            replacementNode = deletedNode.getLeft();
            while (true) {
                if (replacementNode.getRight() == null) {
                    result[0] = replacementNode;
                    result[1] = parentReplacementNode;
                    return result;
                } else {
                    parentReplacementNode = replacementNode;
                    replacementNode = replacementNode.getRight();

                }
            }
        } else {
            replacementNode = deletedNode.getRight();
            while (true) {
                if (replacementNode.getLeft() == null) {
                    result[0] = replacementNode;
                    result[1] = parentReplacementNode;
                    return result;
                } else {
                    parentReplacementNode = replacementNode;
                    replacementNode = replacementNode.getLeft();
                }
            }
        }

    }

    public static Node[] searchDeletedNode(Node root, int key) {
        Node[] nodes = new Node[2];
        Node parentCurrentNode = null;
        Node currentNode = root;
        while (true) {
            if (currentNode.getValue() == key) {
                nodes[0] = currentNode;
                nodes[1] = parentCurrentNode;
                return nodes;
            } else if (key < currentNode.getValue() && currentNode.getLeft() != null) {
                parentCurrentNode = currentNode;
                currentNode = currentNode.getLeft();
            } else if (key >= currentNode.getValue() && currentNode.getRight() != null) {
                parentCurrentNode = currentNode;
                currentNode = currentNode.getRight();
            } else {
                return nodes;
            }
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
