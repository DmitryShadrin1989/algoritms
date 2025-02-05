package leetcode.easy.linkedList;

/*
1)
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

2)
Input: head = [1]
Output: [1]

3)
Input: head = [1,2]
Output: [2,1]

4)
Input: head = []
Output: []
 */

public class ReverseLinkedList {

    public static void main(String[] args) {
        int[] head = {1};

        ListNode prevNode = null;
        for (int i = head.length - 1; i >= 0; i--) {
            prevNode = new ListNode(head[i], prevNode);
        }

        ListNode currentNode = reverseListRecursively(prevNode);

        System.out.print("|");
        while (currentNode != null) {
            System.out.print(currentNode.val + "|");
            currentNode = currentNode.next;
        }
    }

    public static ListNode reverseListIteratively(ListNode head) {
        ListNode prevNode = null;
        ListNode currentNode = head;

        while (currentNode != null) {
            ListNode temp = currentNode.next;
            currentNode.next = prevNode;
            prevNode = currentNode;
            currentNode = temp;
        }
        return prevNode;
    }

    public static ListNode reverseListRecursively(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = auxiliaryRecursiveMethod(head);
        head.next = null;
        return newHead;
    }

    public static ListNode auxiliaryRecursiveMethod(ListNode head) {
        if (head.next != null && head.next.next != null) {
            ListNode newHead = auxiliaryRecursiveMethod(head.next);
            head.next.next = head;
            return newHead;
        }
        head.next.next = head;
        return head.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
