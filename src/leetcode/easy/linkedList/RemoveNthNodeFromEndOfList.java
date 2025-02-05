package leetcode.easy.linkedList;

/*
1)
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

2)
Input: head = [1], n = 1
Output: []

3)
Input: head = [1,2], n = 1
Output: [1]
 */

public class RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        int[] head = {1,2};
        int n = 2;

        ListNode prevNode = null;
        for (int i = head.length - 1; i >= 0; i--) {
            ListNode newNode = new ListNode(head[i], prevNode);
            prevNode = newNode;
        }
        ListNode currentNode = removeNthFromEnd(prevNode, n);
        System.out.print("|");
        while (currentNode != null) {
            System.out.print(currentNode.val + "|");
            currentNode = currentNode.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode currentNode = head;
        ListNode prevTargetNode = head;
        int currentCount = 1;
        while (currentNode != null) {
            currentNode = currentNode.next;
            if (currentCount - n > 1) {
                prevTargetNode = prevTargetNode.next;
            }
            currentCount++;
        }
        if (currentCount == n + 1) {
            return head.next;
        }
        prevTargetNode.next = prevTargetNode.next.next;
        return head;
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
