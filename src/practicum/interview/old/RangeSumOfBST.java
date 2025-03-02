package practicum.interview.old;

import java.util.Stack;

public class RangeSumOfBST {

    public int rangeSumBSTUseStack(TreeNode root, int low, int high) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int sum = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val >= low && node.val <= high) {
                sum += node.val;
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return sum;
    }

    public int rangeSumBSTUseRecudcive(TreeNode root, int low, int high) {
        return dsl(root, low, high);
    }

    private int dsl(TreeNode node, int low, int high){
        if(node == null){
            return 0;
        }

        int left = dsl(node.left, low, high);
        int right = dsl(node.right, low, high);
        int current = (node.val >= low && node.val <= high) ? node.val : 0;

        return current + left + right;
    }

    public class TreeNode {
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
}
