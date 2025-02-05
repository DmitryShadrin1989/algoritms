package leetcode.easy.linkedList;

public class LinkedListCycle {

    public static void main(String[] args) {
        int[] head = {1,1,1,1,1};
        int pos = -1;

        ListNode currentNode = null;
        for (int i = head.length - 1; i >= 0; i--) {
            currentNode = new ListNode(head[i], currentNode);
        }

        ListNode headNode = currentNode;

        int idx = 0;
        ListNode cycleNode = null;
        while (currentNode.next != null) {
            if (idx == pos) {
                cycleNode = currentNode;
            }
            currentNode = currentNode.next;
            idx++;
        }
        currentNode.next = cycleNode;

//        System.out.print("|");
//        while (headNode != null) {
//            System.out.print(headNode.val + "|");
//            headNode = headNode.next;
//        }

        System.out.println(hasCycle(headNode));
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        head.val = 1000001;

        ListNode current = head;
        ListNode prev = null;
        ListNode next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return head.val == prev.val;
    }

    public static boolean hasCycleSlowFast(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;


            if(slow == fast){
                return true;
            }
        }

        return false;
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
