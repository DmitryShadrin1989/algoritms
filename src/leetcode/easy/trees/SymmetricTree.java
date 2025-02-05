package leetcode.easy.trees;

import java.util.Stack;

/*
1)
Input: root = [1,2,2,3,4,4,3]
Output: true

2)
Input: root = [1,2,2,null,3,null,3]
Output: false

 */

public class SymmetricTree {

    public static void main(String[] args) {
        Integer[] root = {1,2,2,null,3,null,3};

        TreeNode treeRoot = buildTree(root);

        System.out.println(isSymmetric(treeRoot));
    }

    public static boolean isSymmetric(TreeNode root) {

        if (root.left == null && root.right == null) {
            return true;
        } else if (root.left == null || root.right == null) {
            return false;
        }

        Stack<TreeNode> leftStack = new Stack<>();
        leftStack.push(root.left);
        Stack<TreeNode> rightStack = new Stack<>();
        rightStack.push(root.right);

        while (!leftStack.empty()) {
            TreeNode leftNode = leftStack.pop();
            TreeNode rightNode = rightStack.pop();

            if (leftNode.val != rightNode.val) {
                return false;
            }

            if ((leftNode.left == null && rightNode.right != null) || (leftNode.left != null && rightNode.right == null)) {
                return false;
            } else if (leftNode.left != null) {
                leftStack.push(leftNode.left);
                rightStack.push(rightNode.right);
            }

            if ((leftNode.right == null && rightNode.left != null) || (leftNode.right != null && rightNode.left == null)) {
                return false;
            } else if (leftNode.right != null) {
                leftStack.push(leftNode.right);
                rightStack.push(rightNode.left);
            }
        }
        return true;
    }

    public static boolean isSymmetricRecursively(TreeNode root) {
        return isMirrored(root.left, root.right);
    }

    private static boolean isMirrored(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            return isMirrored(left.right, right.left) && isMirrored(left.left, right.right);
        } else {
            return false;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode buildTree(Integer[] root) {
        if (root == null || root.length == 0) {
            return null;
        }

        TreeNode[] nodes = new TreeNode[root.length];

        // Создаем все узлы, пропуская null значения
        for (int i = 0; i < root.length; i++) {
            if (root[i] != null) {
                nodes[i] = new TreeNode(root[i]);
            }
        }

        // Связываем узлы
        for (int i = 0; i < root.length; i++) {
            if (nodes[i] != null) {
                // Левый потомок
                int leftIndex = 2 * i + 1;
                if (leftIndex < root.length && nodes[leftIndex] != null) {
                    nodes[i].left = nodes[leftIndex];
                }

                // Правый потомок
                int rightIndex = 2 * i + 2;
                if (rightIndex < root.length && nodes[rightIndex] != null) {
                    nodes[i].right = nodes[rightIndex];
                }
            }
        }

        return nodes[0]; // Возвращаем корень дерева
    }
}
