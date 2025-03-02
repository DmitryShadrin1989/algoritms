package practicum.interview.old;

/*
1)
Input: head = [1,2,2,1]
Output: true

2)
Input: head = [1,2]
Output: false

3)
Input: head = [2,2]
Output: true

4)
Input: head = [1]
Output: true

 */

public class PalindromeLinkedList {

    public static void main(String[] args) {
        int[] head = {1};

        ListNode currentNode = null;
        for (int i = head.length - 1; i >= 0; i--) {
            currentNode = new ListNode(head[i], currentNode);
        }
        System.out.println(isPalindrome(currentNode));
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode curr = slow.next;
        ListNode prev = null;
        slow.next = null;
        while (curr != null) {
            ListNode tempNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNode;
        }

        while (prev != null) {
            if (prev.val != head.val) {
                return false;
            }
            prev = prev.next;
            head = head.next;
        }
        return true;
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
