package leetcode.easy.trees;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfBinaryTree {

    public static void main(String[] args) {
        Integer[] root = {3,9,20,null,null,15,7};

        TreeNode treeRoot = buildTree(root);

        System.out.println(maxDepthRecursively(treeRoot));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int sizeLevel = queue.size();
            while (sizeLevel > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                sizeLevel--;
            }
            depth++;
        }
        return depth;
    }

    public static int maxDepthRecursively(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepthRecursively(root.left);
        int rightDepth = maxDepthRecursively(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
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
