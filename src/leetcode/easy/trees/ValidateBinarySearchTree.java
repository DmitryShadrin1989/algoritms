package leetcode.easy.trees;

/*
[2,2,2]
false

[45,42,null,null,44,43,null,41]
false

[5,1,4,null,null,3,6]
false

[-2147483648,null,2147483647]
true

 */

import java.util.Stack;

public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        Integer[] root = {45,42,null,null,44,43,null,41};

        TreeNode treeRoot = buildTree(root);

        System.out.println(isValidBSTRecursively(treeRoot));
    }

    public static boolean isValidBST(TreeNode root) {
        Stack<Object[]> stack = new Stack<>();
        Object[] rootObj = {root, (long) Integer.MIN_VALUE - 1, (long) Integer.MAX_VALUE + 1};
        stack.push(rootObj);

        while (!stack.isEmpty()){
            Object[] currentObj = stack.pop();
            TreeNode currentNode = (TreeNode) currentObj[0];
            long min = (long) currentObj[1];
            long max = (long) currentObj[2];
            if (currentNode.left != null) {
                if (currentNode.left.val >= currentNode.val || currentNode.left.val <= min || currentNode.left.val >= max) {
                    return false;
                }
                Object[] newObj = {currentNode.left, min, (long) currentNode.val};
                stack.push(newObj);
            }
            if (currentNode.right != null) {
                if (currentNode.right.val <= currentNode.val || currentNode.right.val <= min || currentNode.right.val >= max) {
                    return false;
                }
                Object[] newObject = {currentNode.right, (long) currentNode.val, max};
                stack.push(newObject);
            }
        }
        return true;
    }

    public static boolean isValidBSTRecursively(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean isValid(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }

        if (root.val <= min || root.val >= max) {
            return false;
        }
        boolean isValidLeft = isValid(root.left, min, root.val);
        boolean isValidRight = isValid(root.right, root.val, max);
        return isValidLeft && isValidRight;
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
