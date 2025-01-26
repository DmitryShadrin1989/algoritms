package leetcode.easy.linkedList;

/*
1) Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
 */

public class DeleteNodeInLinkedList {

    static ListNode headListNode;

    public static void main(String[] args) {
        int[] head = {4,5,1,9};
        int valueDeleteNode = 1;
        ListNode deleteNode = null;
        ListNode prevNode = null;
        for (int i = 0; i < head.length; i++) {
            ListNode newNode = new ListNode(head[i]);
            if (prevNode == null) {
                headListNode = newNode;
            } else {
                prevNode.next = newNode;
            }
            prevNode = newNode;
            if (head[i] == valueDeleteNode) {
                deleteNode = newNode;
            }
        }
        deleteNode(deleteNode);
        ListNode currentNode = headListNode;
        System.out.print("|");
        while (currentNode != null) {
            System.out.print(currentNode.val + "|");
            currentNode = currentNode.next;
        }
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
   }
}
