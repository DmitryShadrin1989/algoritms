package yandex.tree;

import yandex.stack.SimpleQueue;
import yandex.stack.SimpleStack;
import yandex.stack.Stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class TreeEx1 {
    public static void main(String[] args) {
        Tree root = new Tree(20,
                new Tree(7,
                        new Tree(4, null, new Tree(6)), new Tree(9)),
                new Tree(35,
                        new Tree(31, new Tree(28), null),
                        new Tree(40, new Tree(38), new Tree(52))));

        System.out.println(root.sumRecursive());
        System.out.println(Tree.sumDeep(root));
        System.out.println(Tree.sumDeepEx2(root));
        System.out.println(Tree.sumWideEx2(root));
    }

    static class Tree {
        int value;
        Tree left;
        Tree right;


        public Tree(int value, Tree left, Tree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Tree(int value) {
            this.value = value;
        }

        public int sumRecursive() {
            int summ = value;

            if (left != null) {
                summ += left.sumRecursive();
            }

            if (right != null) {
                summ += right.sumRecursive();
            }
            return summ;
        }

        public static int sumDeep(Tree root) {
            SimpleStack<Tree> stack = new SimpleStack<>();
            stack.push(root);
            int sum = 0;
            while(!stack.isEmpty()) {
                Tree node = stack.pop();

                sum += node.value;

                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }

            return sum;
        }

        public static int sumWide(Tree root) {
            SimpleQueue<Tree> queue = new SimpleQueue<>();
            queue.add(root);
            int sum = 0;
            while(!queue.isEmpty()) {
                Tree node = queue.remove();

                sum += node.value;

                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
            return sum;
        }

        public static int sumDeepEx2(Tree root) {
            int sum = 0;
            Stack<Tree> stack = new Stack<Tree>() {
                ArrayList<Tree> list = new ArrayList<>();
                @Override
                public void push(Tree item) {
                    list.add(0, item);
                }

                @Override
                public Tree pop() {
                    return list.remove(0);
                }

                @Override
                public boolean isEmpty() {
                    return list.isEmpty();
                }
            };

            stack.push(root);

            while (!stack.isEmpty()) {
                Tree node = stack.pop();
                sum += node.value;
                if (node.left != null) {
                    stack.push(node.left);
                }
                if (node.right != null) {
                    stack.push(node.right);
                }
            }
            return sum;
        }

        public static int sumWideEx2(Tree root) {
            int sum = 0;
            Queue<Tree> queue = new ArrayDeque<>();
            queue.add(root);
            while(!queue.isEmpty()) {
                Tree node = queue.poll();
                sum += node.value;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            return sum;
        }
    }
}
